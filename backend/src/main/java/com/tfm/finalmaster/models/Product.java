package com.tfm.finalmaster.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Product")
public class Product {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid", nullable = false)
    private Long productid;

    @Column(name = "categoryid", nullable = false)
    private Long categoryid;

    @Getter @Setter @Column(name="name", nullable = false)
    private String name;

    @Getter @Setter @Column(name="photo")
    private String photo;

    @Getter @Setter @Column(name="comment")
    private Date comment;

    @Getter @Setter @Column(name="creation_date")
    private String creation_date;

    @Getter @Setter @Column(name="create_user")
    private Long create_user;

    @Getter @Setter @Column(name="update_date")
    private Date update_date;

    @Getter @Setter @Column(name="update_user")
    private Long update_user;

    @Getter @Setter @Column(name="status")
    private Character status;

}
