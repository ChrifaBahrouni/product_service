package com.example.digid.digid.productservice.repository;

import com.example.digid.digid.productservice.model.Activite;
import com.example.digid.digid.productservice.model.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActiviteRespository extends JpaRepository <Activite, Integer>  {
    @Query("SELECT o FROM Activite o WHERE o.article.id = :id_article and o.emplacement.id  = :id_emplacement  ") //and o.emplacement.entrepot= :id_entrepot
    Mouvement findByArticleEmplacement(String  id_article , String  id_emplacement ); //, String  id_entrepot
}
