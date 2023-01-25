package com.tfm.finalmaster.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Record_Product_Detail")
public class RecordProductDetail {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recordproductdetailid", nullable = false)
    private Long recordproductdetailid;

    @ManyToOne
    @JoinColumn(name = "recordproductid",referencedColumnName = "recordproductid")
    @Getter @Setter
    private RecordProduct recordProduct;

    @ManyToOne
    @JoinColumn(name = "specificationid",referencedColumnName = "specificationid")
    @Getter @Setter
    private Specification specification;

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
