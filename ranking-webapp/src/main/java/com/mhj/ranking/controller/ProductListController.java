package com.mhj.ranking.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mhj.ranking.entity.Product;
import com.mhj.ranking.repository.ProductRepository;

@Scope (value = "session")
@Component (value = "productList")
@ELBeanName(value = "productList")
@Join(path = "/ranking/products", to = "/product-list.jsf")
public class ProductListController {
	
    @Autowired
    private ProductRepository productRepository;

    private List<Product> products;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
//        products = productRepository.findAll();
    	products = new ArrayList<>();
    	Product p = new Product();
    	p.setId(1L);
    	p.setName("Teste");
    	p.setPrice(new BigDecimal(90));
    	products.add(p);
    }

    public List<Product> getProducts() {
        return products;
    }
}
