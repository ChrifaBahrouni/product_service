package com.example.digid.digid.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Article  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private String description;
    private BigDecimal prix_unitaire  ;

    private String photo ;
    private String unite ;
     private int  stock_min ;
     private int  seuil_alert ;
     private int  stock_initial  ;

    @OneToMany(mappedBy = "article")
    //@JsonIgnore
    private List<Mouvement>  mouvements;
    @OneToMany(mappedBy = "article")
    //@JsonIgnore
    private List<Activite>  activites;
    @ManyToOne
    public Category category;

}

