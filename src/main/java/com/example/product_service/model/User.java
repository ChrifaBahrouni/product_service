package com.example.digid.digid.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String  phone  ;



    private boolean active;
    private String otp;
    private LocalDateTime otpGeneratedTime;
    private  Boolean complete  ;
    private Date BirthDate  ;
     private   String   avatar ;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Activite> activites;



}

