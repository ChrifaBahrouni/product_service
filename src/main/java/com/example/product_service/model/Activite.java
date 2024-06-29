package com.example.digid.digid.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private int quantity;
    private TypeMouvement type ;

    @ManyToOne
    @JoinColumn(name = "emplacement_id")
    public  Emplacement emplacement;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @JsonIgnore
    public Article article;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public  User user ;

}

