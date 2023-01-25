package com.tfm.finalmaster.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Role_Option")
public class RoleOption {

    @Id @Getter @Setter
    @Column(name = "roleid", nullable = false)
    private Long roleid;

    @Id @Getter @Setter
    @Column(name = "optionmenuid", nullable = false)
    private Long optionmenuid;

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
