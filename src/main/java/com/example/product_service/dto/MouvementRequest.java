package com.example.digid.digid.productservice.dto;

import com.example.digid.digid.productservice.model.Article;
import com.example.digid.digid.productservice.model.Emplacement;
import com.example.digid.digid.productservice.model.TypeMouvement;
import com.example.digid.digid.productservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MouvementRequest {

    int quantity  ;
    TypeMouvement typeMouvement  ;
    Emplacement emplacement ;
    Emplacement emplacement1 ;
    Article article ;
    User user ;
}
