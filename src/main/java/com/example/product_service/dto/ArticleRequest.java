package com.example.digid.digid.productservice.dto;

import com.example.digid.digid.productservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    private Long id;
    private String designation;
    private String description;
    private BigDecimal prix_unitaire  ;

    private String photo ;
    private String unite ;
    private int  stock_min ;
    private int  seuil_alert ;
    private int  stock_initial  ;
    private Category category ;
}
