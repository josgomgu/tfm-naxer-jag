package com.tfm.finalmaster.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Category_Attribute")
public class CategoryAttribute {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryattributeid", nullable = false)
    private Long categoryattributeid;

    @ManyToOne
    @JoinColumn(name = "categoryid",referencedColumnName = "categoryid")
    @Getter @Setter
    private Category category;

    @ManyToOne
    @JoinColumn(name = "attributeid",referencedColumnName = "attributeid")
    @Getter @Setter
    private Attribute attribute;

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
