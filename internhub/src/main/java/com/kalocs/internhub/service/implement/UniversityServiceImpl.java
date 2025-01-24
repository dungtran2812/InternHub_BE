package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.UniversityBusiness;
import com.kalocs.internhub.entity.University;
import com.kalocs.internhub.model.UniversityDTO;
import com.kalocs.internhub.payload.request.UniversityRequest;
import com.kalocs.internhub.service.UniversityService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class UniversityServiceImpl implements UniversityService {

    private final UniversityBusiness universityBusiness;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UniversityServiceImpl(UniversityBusiness universityBusiness) {
        this.universityBusiness = universityBusiness;
    }

    @Override
    public University getUniversity(UUID id) {
        return universityBusiness.getUniversity(id);
    }

    @Override
    public List<UniversityDTO> getAllUniversities() {
        log.debug("getAllUniversities UniversityServiceImpl start");
        List<UniversityDTO> universities = universityBusiness.getAllUniversities().stream()
                .map( university -> modelMapper.map(university, UniversityDTO.class)).toList();
        log.debug("getAllUniversities UniversityServiceImpl end");
        return universities;
    }

    @Override
    public UniversityDTO createUniversity(UniversityRequest university) {
        try{
            log.debug("createUniversity UniversityServiceImpl start | {}", university);
            University universityToCreate = modelMapper.map(university, University.class);
            universityToCreate.setId(UUID.randomUUID());
            UniversityDTO createdUniversity = modelMapper.map(universityBusiness.createUniversity(universityToCreate), UniversityDTO.class);
            log.debug("createUniversity UniversityServiceImpl end | {}", createdUniversity);
            return createdUniversity;
        } catch (Exception e) {
            log.error("createUniversity UniversityServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public UniversityDTO updateUniversity(UUID id, UniversityRequest university) {
        try{
            log.debug("updateUniversity UniversityServiceImpl start | {} | {}", id, university);
            University universityToUpdate = modelMapper.map(university, University.class);
            UniversityDTO updatedUniversity = modelMapper.map(universityBusiness.updateUniversity(id, universityToUpdate), UniversityDTO.class);
            log.debug("updateUniversity UniversityServiceImpl end | {}", updatedUniversity);
            return updatedUniversity;
        } catch (Exception e) {
            log.error("updateUniversity UniversityServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteUniversity(UUID id) {
        try{
            log.debug("deleteUniversity UniversityServiceImpl start | {}", id);
            universityBusiness.deleteUniversity(id);
            log.debug("deleteUniversity UniversityServiceImpl end | {}", id);
            return true;
        } catch (Exception e) {
            log.error("deleteUniversity UniversityServiceImpl error | {}", e.getMessage());
            throw e;
        }
    }
}
