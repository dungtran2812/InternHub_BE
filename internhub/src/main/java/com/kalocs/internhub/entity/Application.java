package com.kalocs.internhub.entity;

import com.kalocs.internhub.common.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "applications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    private UUID id;
    private long date;
    private ApplicationStatus status;
    private String resume;
    private String coverLetter;

    @Column(nullable = true)
    private long createdDate;
    @Column(nullable = true)
    private long updatedDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

}
