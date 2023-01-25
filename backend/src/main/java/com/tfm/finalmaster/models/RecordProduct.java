package com.tfm.finalmaster.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Record_Product")
public class RecordProduct {


    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recordproductid", nullable = false)
    private Long recordproductid;

    @ManyToOne
    @JoinColumn(name = "productid",referencedColumnName = "productid")
    @Getter @Setter
    private Product product;

    @ManyToOne
    @JoinColumn(name = "storeid",referencedColumnName = "storeid")
    @Getter @Setter
    private Store store;


    @Getter @Setter @Column(name="photo")
    private String photo;

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
