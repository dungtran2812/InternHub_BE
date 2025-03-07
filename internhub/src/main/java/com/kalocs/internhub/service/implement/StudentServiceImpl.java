package com.kalocs.internhub.service.implement;

import com.cloudinary.utils.ObjectUtils;
import com.kalocs.internhub.business.StudentBusiness;
import com.kalocs.internhub.common.StudentStatus;
import com.kalocs.internhub.common.UserRole;
import com.kalocs.internhub.config.handler.AppException;
import com.kalocs.internhub.entity.Student;
import com.kalocs.internhub.model.StudentDTO;
import com.kalocs.internhub.payload.request.StudentRequest;
import com.kalocs.internhub.service.StudentService;
import com.kalocs.internhub.utils.AuthUtils;
import com.kalocs.internhub.utils.CloudinaryUtils;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Log4j2
public class StudentServiceImpl implements StudentService {

    private final StudentBusiness studentBusiness;
    private final ModelMapper modelMapper;
    private final CloudinaryUtils cloudinaryUtils;

    @Autowired
    public StudentServiceImpl(StudentBusiness studentBusiness, ModelMapper modelMapper, CloudinaryUtils cloudinaryUtils) {
        this.studentBusiness = studentBusiness;
        this.modelMapper = modelMapper;
        this.cloudinaryUtils = cloudinaryUtils;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        try {
            log.info("getAllStudents() StudentServiceImpl start");
            List<StudentDTO> result = studentBusiness.getAll().stream().map(student -> modelMapper.map(student, StudentDTO.class)).toList();
            log.info("getAllStudents() StudentServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("getAllStudents() StudentServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public StudentDTO getStudentById(UUID id) {
        try {
            log.info("getStudentById() StudentServiceImpl start | id: {}", id);
            StudentDTO result = modelMapper.map(studentBusiness.getById(id).orElseThrow(() -> new AppException(404,"Không tìm thấy sinh viên")), StudentDTO.class);
            log.info("getStudentById() StudentServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("getStudentById() StudentServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public StudentDTO createStudent(StudentRequest student) {
        try {
            log.info("createStudent() StudentServiceImpl start | studentDTO: {}", student);
            Student newStudent = modelMapper.map(student, Student.class);
            newStudent.setId(UUID.randomUUID());
            newStudent.setUsername(student.getEmail());
            newStudent.setStatus(StudentStatus.ACTIVE);
            newStudent.setAvtUrl(student.getAvtrUrl());
            newStudent.setFullName(student.getFullName());
            newStudent.setPhone(student.getPhone());
            newStudent.setGender(student.isGender());
            newStudent.setMajor(student.getMajor());
            newStudent.setGpa(student.getGpa());
            StudentDTO result = modelMapper.map(studentBusiness.create(newStudent), StudentDTO.class);
            log.info("createStudent() StudentServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("createStudent() StudentServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public StudentDTO updateStudent(UUID id, StudentRequest student) {
        try {
            log.info("updateStudent() StudentServiceImpl start | id: {} | studentDTO: {}", id, student);
            if (!studentBusiness.existsById(id)) {
                throw new AppException(404, "Không tìm thấy sinh viên");
            }
            Student updateStudent = studentBusiness.getById(id).orElseThrow(() -> new AppException(404, "Không tìm thấy sinh viên"));
            updateStudent.setAvtUrl(student.getAvtrUrl());
            updateStudent.setFullName(student.getFullName());
            updateStudent.setPhone(student.getPhone());
            updateStudent.setGender(student.isGender());
            updateStudent.setMajor(student.getMajor());
            updateStudent.setGpa(student.getGpa());
            updateStudent.setEmail(student.getEmail());
            updateStudent.setUsername(student.getEmail());
            StudentDTO result = modelMapper.map(studentBusiness.update(updateStudent), StudentDTO.class);
            log.info("updateStudent() StudentServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("updateStudent() StudentServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteStudent(UUID id) {
        try {
            log.info("deleteStudent() StudentServiceImpl start | id: {}", id);
            if (!studentBusiness.existsById(id)) {
                throw new AppException(404, "Không tìm thấy sinh viên");
            }
            boolean result = studentBusiness.delete(id);
            log.info("deleteStudent() StudentServiceImpl end");
            return result;
        } catch (Exception e) {
            log.error("deleteStudent() StudentServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public StudentDTO uploadCV(MultipartFile file) {
        try {
            log.info("uploadCV() StudentServiceImpl start |");
            String fileExtension = file.getContentType();
            if (!fileExtension.equals("application/pdf")) {
                throw new AppException(415, "File không đúng định dạng");
            }
            Student student = studentBusiness.getById(AuthUtils.getCurrentUserId()).orElseThrow(() -> new AppException(404, "Không tìm thấy sinh viên"));
            Map<String,Object> options = ObjectUtils.asMap(
                    "folder", "cv",
                    "public_id", student.getId().toString(),
                    "overwrite", true
            );
            String url = cloudinaryUtils.uploadFile(file, options);
            student.setResume(url);
            if (student.getResume() == null) {
                throw new AppException(400, "Lỗi khi upload file");
            }
            StudentDTO result = modelMapper.map(studentBusiness.update(student), StudentDTO.class);
            log.info("uploadCV() StudentServiceImpl end | {}", result);
            return result;
        } catch (Exception e) {
            log.error("uploadCV() StudentServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
