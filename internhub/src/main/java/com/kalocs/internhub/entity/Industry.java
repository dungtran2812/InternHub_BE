package com.kalocs.internhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Industry {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "industries", fetch = FetchType.LAZY)
    private List<Company> companies;

    @OneToMany(mappedBy = "industry", fetch = FetchType.LAZY)
    private List<Job> jobs;
}
