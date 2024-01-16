package com.socialmeli.SocialMeli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmeli.SocialMeli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Repository
public class ProductRepository implements IProductRepository {


    private List<Product> listProducts = new ArrayList<>();

    public ProductRepository() throws IOException {
        loadDataBase();

    }
    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Boolean remove(Product product) {
        return null;
    }

    @Override
    public Optional<Product> update(Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return listProducts.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }


    @Override
    public Product findByIdOrCreate(Product product) {
        return this.findById(product.getId()).orElseGet(() -> this.create(
                new Product(
                        product.getId(),
                        product.getProductName(),
                        product.getType(),
                        product.getBrand(),
                        product.getColor(),
                        product.getNotes()
                )
        ));
    }
    private void loadDataBase() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products;
        file= ResourceUtils.getFile("classpath:json/products.json");
        products= objectMapper.readValue(file,new TypeReference<List<Product>>(){});
        listProducts= products;
    }
}
