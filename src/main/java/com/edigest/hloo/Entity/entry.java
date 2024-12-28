package com.edigest.hloo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "LOGING")
@Data

public class entry {
     @Id
     @Column(name="id")
    private String id;
    @Column(name="password")
    private String password;




}


