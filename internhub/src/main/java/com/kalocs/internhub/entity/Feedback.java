package com.kalocs.internhub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "Feedback")
public class Feedback {
    @Id
    UUID id;
    String description;
}
