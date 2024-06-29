package com.example.digid.digid.productservice.controller;

import com.example.digid.digid.productservice.dto.ProductResponse;
import com.example.digid.digid.productservice.model.Article;
import com.example.digid.digid.productservice.repository.ArticleRepository;
import com.example.digid.digid.productservice.security.RoleFeaturePermissionGrantedAuthority;
import com.example.digid.digid.productservice.security.UserDetailsImpl;
import com.example.digid.digid.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ArticleController {

   @Autowired
   private  ProductService productService;
    @Autowired
    ArticleRepository repository ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Article productRequest) {
        repository.save(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Article> getAllProducts() {
        System.out.println("hello article");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority instanceof RoleFeaturePermissionGrantedAuthority) {
                RoleFeaturePermissionGrantedAuthority rfpAuthority = (RoleFeaturePermissionGrantedAuthority) authority;
                if (rfpAuthority.getAuthority().equals("ADMIN") && rfpAuthority.hasPermission("Articles", "canGet")) {
                    return repository.findAll();
                }
            }
        }
        return null;

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Article getProduct(@PathVariable String id ) {
        return repository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  void   deleteProduct(@PathVariable String id ) {

        //return
                repository.deleteById(id);
    }


    @GetMapping("/inStock")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> isInStock(@RequestParam List<String> skuCode) {
        return  productService.isInStock(skuCode) ;//productService.isInStock(skuCode);
    }

}
