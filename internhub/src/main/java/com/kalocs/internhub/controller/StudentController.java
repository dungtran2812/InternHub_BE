package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.model.StudentDTO;
import com.kalocs.internhub.payload.request.StudentRequest;
import com.kalocs.internhub.payload.response.ResponseMessage;
import com.kalocs.internhub.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(URLConstant.STUDENT)
@Log4j2
@CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        log.info("getAllStudents() StudentController start");
        List<StudentDTO> result = studentService.getAllStudents();
        log.info("getAllStudents() StudentController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable UUID id) {
        log.info("getStudentById() StudentController start | id: {}", id);
        StudentDTO result = studentService.getStudentById(id);
        log.info("getStudentById() StudentController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentRequest student) {
        log.info("createStudent() StudentController start | studentDTO: {}", student);
        StudentDTO result = studentService.createStudent(student);
        log.info("createStudent() StudentController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable UUID id, @RequestBody StudentRequest student) {
        log.info("updateStudent() StudentController start | id: {} | studentDTO: {}", id, student);
        StudentDTO result = studentService.updateStudent(id, student);
        log.info("updateStudent() StudentController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteStudent(@PathVariable UUID id) {
        log.info("deleteStudent() StudentController start | id: {}", id);
        boolean check = studentService.deleteStudent(id);
        log.info("deleteStudent() StudentController end");
        return check ? ResponseEntity.ok().body(ResponseMessage.builder().message("Xoá thành công").success(true).build())
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseMessage.builder().message("Xoá thất bại").success(false).build());
    }
}
