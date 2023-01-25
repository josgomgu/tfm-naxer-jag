package com.tfm.finalmaster.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Comparision_Detail")
public class ComparisionDetail {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comparisiondetailid", nullable = false)
    private Long comparisiondetailid;

    @ManyToOne
    @JoinColumn(name = "comparisionid",referencedColumnName = "comparisionid")
    @Getter @Setter
    private Comparision comparision;

    @ManyToOne
    @JoinColumn(name = "recordproductdetailid",referencedColumnName = "recordproductdetailid")
    @Getter @Setter
    private RecordProductDetail recordProductDetail;

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
