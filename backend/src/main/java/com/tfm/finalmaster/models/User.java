package com.tfm.finalmaster.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="User")
public class User {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false)
    private Long userid;

    @Getter @Setter @Column(name="login", nullable = false)
    private String login;

    @Getter @Setter @Column(name="password", nullable = false)
    private String password;

    @Getter @Setter @Column(name="name", nullable = false)
    private String name;

    @Getter @Setter @Column(name="last_name")
    private String last_name;

    @Getter @Setter @Column(name="last_login")
    private Date last_login;

    @Getter @Setter @Column(name="creation_date")
    private Date creation_date;

    @Getter @Setter @Column(name="create_user")
    private Long create_user;

    @Getter @Setter @Column(name="update_date")
    private Date update_date;

    @Getter @Setter @Column(name="update_user")
    private Long update_user;

    @Getter @Setter @Column(name="status")
    private Character status;


}
