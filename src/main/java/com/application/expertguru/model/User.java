package com.application.expertguru.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column
    private String DOB;

    @Column(unique = true)
    private String emailId;

    @Column
    private Boolean isDeleted;
}
