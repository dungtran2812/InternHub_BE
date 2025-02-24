package com.kalocs.internhub.service;

import com.kalocs.internhub.model.StudentDTO;
import com.kalocs.internhub.payload.request.StudentRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(UUID id);

    StudentDTO createStudent(StudentRequest student);

    StudentDTO updateStudent(UUID id, StudentRequest student);

    boolean deleteStudent(UUID id);

    StudentDTO uploadCV(MultipartFile file);
}
