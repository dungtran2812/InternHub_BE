package com.kalocs.internhub.entity;

import com.kalocs.internhub.common.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "applications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Application {
    @Id
    private UUID id;
    private long date;
    private ApplicationStatus status;
    private String resume;
    private String coverLetter;

    @Column(nullable = true)
    @CreatedDate
    private long createdDate;
    @Column(nullable = true)
    private long updatedDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @PrePersist
    protected void onCreate() {
        this.createdDate = Instant.now().toEpochMilli();
        this.updatedDate = Instant.now().toEpochMilli();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = Instant.now().toEpochMilli();
    }
}
