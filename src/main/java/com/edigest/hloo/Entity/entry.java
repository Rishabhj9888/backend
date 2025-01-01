package com.edigest.hloo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LOGING")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class entry {
     @Id
     @Column(name="id")
    private String id;
    @Column(name="password")
    private String password;




}


