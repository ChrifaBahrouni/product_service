package com.example.digid.digid.productservice.service;

import com.example.digid.digid.productservice.dto.ArticleRequest;
import com.example.digid.digid.productservice.dto.MouvementRequest;

import com.example.digid.digid.productservice.dto.ProductResponse;
import com.example.digid.digid.productservice.model.Activite;
import com.example.digid.digid.productservice.model.Article;

import com.example.digid.digid.productservice.model.Mouvement;
import com.example.digid.digid.productservice.repository.ActiviteRespository;
import com.example.digid.digid.productservice.repository.ArticleRepository;
import com.example.digid.digid.productservice.repository.MouvementRespository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

   /* @Autowired
    private  ArticleRepository repository;
    @Autowired*/
    private final  ArticleRepository repository ;
    private  final ActiviteRespository respository_activity ;
    private final  MouvementRespository mouvementRepository;



    @Transactional(readOnly = true)
    @SneakyThrows
    public List<ProductResponse> isInStock(List<String> designation) {
        // log.info("Checking Inventory");
        log.info("Wait started");
        Thread.sleep(10000);
        log.info("Wait Ended");
        return repository.findByDesignationIn(designation).stream()
                .map(article ->
                        ProductResponse.builder()
                                .designation(article.getDesignation())
                                .isInStock(article.getStock_initial() > 0)
                                //check quantity  poduct in this emplacement
                                .build()
                ).toList();

    }

    public Article  createProduct(ArticleRequest articleRequest) {
        Article product = Article.builder()
                .designation(articleRequest.getDesignation())
                .description(articleRequest.getDescription())
                .prix_unitaire(articleRequest.getPrix_unitaire())
                .stock_initial(articleRequest.getStock_initial())
                .stock_min(articleRequest.getStock_min())
                .category(articleRequest.getCategory())
                .unite(articleRequest.getUnite())
                .photo(articleRequest.getPhoto())
                .seuil_alert(articleRequest.getSeuil_alert())
                .build();
        log.info("Product {} is saved", product.getId());
       return repository.save(product);

    }
    public Mouvement gererMouvementArticle(MouvementRequest mouvementRequest) {

        Activite activite = Activite.builder()
                .date(LocalDateTime.now())
                .quantity(mouvementRequest.getQuantity())
                .emplacement(mouvementRequest.getEmplacement())

                .article(mouvementRequest.getArticle())
                .user(mouvementRequest.getUser())
                .type(mouvementRequest.getTypeMouvement())

                .build();

        Mouvement mouvement = mouvementRepository.findByArticleEmplacement(
                mouvementRequest.getArticle().getId() + "",
                mouvementRequest.getEmplacement().getId() + ""
        );

        Mouvement mouvement1 = null; // Initialize mouvement1

        if (mouvementRequest.getEmplacement1() != null) {
            mouvement1 = mouvementRepository.findByArticleEmplacement(
                    mouvementRequest.getArticle().getId() + "",
                    mouvementRequest.getEmplacement1().getId() + ""
            );

            if (mouvement1 == null) {
                mouvement1 = Mouvement.builder()
                        .date(LocalDateTime.now())
                        .quantity(0)
                        .emplacement(mouvementRequest.getEmplacement1())
                        .article(mouvementRequest.getArticle())
                        .type(mouvementRequest.getTypeMouvement())
                        .build();
            }
        }

        if (mouvement == null) {
            mouvement = Mouvement.builder()
                    .date(LocalDateTime.now())
                    .quantity(0)
                    .emplacement(mouvementRequest.getEmplacement())
                    .article(mouvementRequest.getArticle())
                    .type(mouvementRequest.getTypeMouvement())
                    .build();
        }

        switch (mouvementRequest.getTypeMouvement()) {
            case ENTREE:
                mouvement.setQuantity(mouvement.getQuantity() + mouvementRequest.getQuantity());
                break;
            case SORTIE:
                mouvement.setQuantity(mouvement.getQuantity() - mouvementRequest.getQuantity());
                break;
            case TRANSFERT:
                if (mouvement1 == null) {
                    throw new IllegalArgumentException("Destination emplacement is null for TRANSFERT");
                }
                mouvement.setQuantity(mouvement.getQuantity() - mouvementRequest.getQuantity());
                mouvement1.setQuantity(mouvement1.getQuantity() + mouvementRequest.getQuantity());
                mouvementRepository.save(mouvement1); // Save mouvement1 after updating its quantity
                break;
            default:
                throw new IllegalArgumentException("Unknown type of movement: " + mouvementRequest.getTypeMouvement());
        }

        mouvement.setDate(LocalDateTime.now());
        respository_activity.save(activite) ;
        return mouvementRepository.save(mouvement);
    }

    /*public Mouvement gererMouvementArticle(MouvementRequest mouvementRequest) {
        Mouvement mouvement = mouvementRepository.findByArticleEmplacement(
                mouvementRequest.getArticle().getId() + "",
                mouvementRequest.getEmplacement().getId() + ""
        );
        Mouvement mouvement1 ;
        if ( mouvementRequest.getEmplacement1() != null){
                 mouvement1 = mouvementRepository.findByArticleEmplacement(
                    mouvementRequest.getArticle().getId() + "",
                    mouvementRequest.getEmplacement1().getId() + ""
            );
            if (mouvement1 == null  ) {
                mouvement = Mouvement.builder()
                        .date(LocalDateTime.now())
                        .quantity(0) // Initialize with 0 quantity
                        .emplacement(mouvementRequest.getEmplacement())
                        .article(mouvementRequest.getArticle())
                        .type(mouvementRequest.getTypeMouvement())
                        .build();
            }
        }



        if (mouvement == null  ) {
            mouvement = Mouvement.builder()
                    .date(LocalDateTime.now())
                    .quantity(0) // Initialize with 0 quantity
                    .emplacement(mouvementRequest.getEmplacement())
                    .article(mouvementRequest.getArticle())
                    .type(mouvementRequest.getTypeMouvement())
                    .build();
        }



        switch (mouvementRequest.getTypeMouvement()) {
            case ENTREE:
                mouvement.setQuantity(mouvement.getQuantity() + mouvementRequest.getQuantity());
                break;
            case SORTIE:
                mouvement.setQuantity(mouvement.getQuantity() - mouvementRequest.getQuantity());
                break;
            case TRANSFERT:
                //update mouvement source
                mouvement.setQuantity(mouvement.getQuantity() - mouvementRequest.getQuantity());
                //
                mouvement1.setQuantity(mouvement1.getQuantity()+mouvementRequest.getQuantity());
                mouvementRepository.save(mouvement1);
                // Implement transfer logic if needed
                break;
            default:
                // Handle unknown type of movement
                throw new IllegalArgumentException("Unknown type of movement: " + mouvementRequest.getTypeMouvement());
        }

        // Update the mouvement with the new quantity and save it
        mouvement.setDate(LocalDateTime.now());
        return mouvementRepository.save(mouvement);
    }*/

/*
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }*/
}
