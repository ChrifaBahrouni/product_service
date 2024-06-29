package com.example.digid.digid.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entrepots")
public class Entrepot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int  nb_range ;
    private int nb_rayon ;
    private int nb_position ;
    private int nb_niveau ;
    private String nom ;
    private String description ;
    private String adresse ;
    private double   longitude ;
    private double latitude ;
    private String statut ;

    @OneToMany(mappedBy = "entrepot")
    @JsonIgnore
    private List<Emplacement> emplacements;

}
