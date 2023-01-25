package com.tfm.finalmaster.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Specification")
public class Specification {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specificationid", nullable = false)
    private Long specificationid;

    @ManyToOne
    @JoinColumn(name = "attributeid",referencedColumnName = "attributeid")
    @Getter @Setter
    private Attribute attribute;

    @Getter @Setter @Column(name="name", nullable = false)
    private String name;

    @Getter @Setter @Column(name="weight", nullable = false )
    private Long weight;

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
