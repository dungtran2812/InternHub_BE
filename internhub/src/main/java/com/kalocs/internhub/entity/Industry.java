package com.kalocs.internhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

    @ManyToMany(mappedBy = "industries")
    private List<Company> companies;

    @OneToMany(mappedBy = "industry")
    private List<Job> jobs;
}
