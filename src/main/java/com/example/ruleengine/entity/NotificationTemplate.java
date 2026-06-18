package com.example.ruleengine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "cp_ntftmpl")
@Data
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String type;
    private String description;
    @Length(max =1500)
    @Column(columnDefinition = "TEXT")
    @Lob
    private String templateString;
}

