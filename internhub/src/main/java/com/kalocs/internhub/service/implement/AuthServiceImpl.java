package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.CompanyBusiness;
import com.kalocs.internhub.business.UserBusiness;
import com.kalocs.internhub.common.StudentStatus;
import com.kalocs.internhub.common.UserRole;
import com.kalocs.internhub.config.handler.AppException;
import com.kalocs.internhub.entity.Recruiter;
import com.kalocs.internhub.entity.Student;
import com.kalocs.internhub.entity.User;
import com.kalocs.internhub.model.RecruiterDTO;
import com.kalocs.internhub.model.StudentDTO;
import com.kalocs.internhub.model.UserDTO;
import com.kalocs.internhub.payload.request.LoginRequest;
import com.kalocs.internhub.payload.request.signup.RecruiterSignupRequest;
import com.kalocs.internhub.payload.request.signup.StudentSignupRequest;
import com.kalocs.internhub.payload.response.JwtResponseModel;
import com.kalocs.internhub.repository.UserRepository;
import com.kalocs.internhub.config.security.jwt.JwtUtils;
import com.kalocs.internhub.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@Service
@Log4j2
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserBusiness userBusiness;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private ModelMapper modelMapper;
    private CompanyBusiness companyBusiness;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserBusiness userBusiness,
                           JwtUtils jwtUtils, AuthenticationManager authenticationManager, ModelMapper modelMapper, CompanyBusiness companyBusiness) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userBusiness = userBusiness;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.companyBusiness = companyBusiness;
    }

    @Override
    public JwtResponseModel login(LoginRequest loginRequest) {
        try {
            log.debug("login() AuthServiceImpl Start | {}", loginRequest);
            User user = userBusiness.getUserByEmail(loginRequest.getEmail());
            if (user == null) {
                throw new AppException(401, "Email chưa được đăng ký");
            }
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                throw new AppException(401, "Mật khẩu không đúng");
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            log.debug("login() AuthServiceImpl End |");
            return new JwtResponseModel(jwt, "Bearer",userDTO);
        } catch (Exception ex) {
            log.error("login() AuthServiceImpl Error | {}: {}", loginRequest.getEmail(), ex.getMessage());
            throw ex;
        }

    }

    @Override
    public StudentDTO studentSignup(StudentSignupRequest studentSignupRequest) {
        try {
            log.debug("studentSignup() AuthServiceImpl Start | {}", studentSignupRequest);
            if (userBusiness.existsByEmail(studentSignupRequest.getEmail())) {
                throw new AppException(406,"Email này đã được sử dụng");
            }
            Student student = modelMapper.map(studentSignupRequest, Student.class);
            student.setId(UUID.randomUUID());
            student.setRole(UserRole.STUDENT);
            student.setPassword(passwordEncoder.encode(studentSignupRequest.getPassword()));
            student.setUsername(studentSignupRequest.getEmail());
            student.setStatus(StudentStatus.ACTIVE);
            StudentDTO result = modelMapper.map(userRepository.save(student), StudentDTO.class);
            log.debug("studentSignup() AuthServiceImpl End | {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error during create student {}: {}", studentSignupRequest.getFullName(), e);
            throw e;
        }
    }

    @Override
    public RecruiterDTO recruiterSignup(RecruiterSignupRequest recruiterSignupRequest) {
        try {
            log.debug("recruiterSignup() AuthServiceImpl Start | {}", recruiterSignupRequest);
            if (userBusiness.existsByEmail(recruiterSignupRequest.getEmail())) {
                throw new AppException(406,"Email này đã được sử dụng");
            }
            Recruiter recruiter = modelMapper.map(recruiterSignupRequest, Recruiter.class);
            recruiter.setId(UUID.randomUUID());
            recruiter.setRole(UserRole.RECRUITER);
            recruiter.setPassword(passwordEncoder.encode(recruiterSignupRequest.getPassword()));
            recruiter.setUsername(recruiterSignupRequest.getEmail());
            recruiter.setCompany(companyBusiness.getById(recruiterSignupRequest.getCompanyId()).orElseThrow(() -> {
                log.error("Company not found");
                return new AppException(404, "Company not found");
            }));
            RecruiterDTO result = modelMapper.map(userRepository.save(recruiter), RecruiterDTO.class);
            log.debug("recruiterSignup() AuthServiceImpl End | {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error during create recruiter {}: {}", recruiterSignupRequest.getFullName(), e);
            throw e;
        }
    }
}

