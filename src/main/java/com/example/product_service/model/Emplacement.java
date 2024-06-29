package com.example.digid.digid.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "emplacements")
public class Emplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nom ;
    private String ref ;
    @Column(name = "`range`")
    private int range ;
    private int rayon ;
    private int niveau ;
    private int position ;
    @ManyToOne
    // @JoinColumn(name = "entrepot_id")
    public  Entrepot entrepot;

    @OneToMany(mappedBy = "emplacement")
    @JsonIgnore
    private List<Mouvement> mouvements;
    @OneToMany(mappedBy = "emplacement")
    @JsonIgnore
    private List<Activite> activites;


   /* private String name;
    private int xCoordinate;
    private int yCoordinate;
    @ManyToOne//(fetch = FetchType.LAZY )
    @JoinColumn(name = "map_id")
     @JsonIgnore
    public Map map;

    @OneToOne//(mappedBy = "emplacement") //, cascade = CascadeType.ALL
    @JsonIgnore
    private Category  category;*/



}
