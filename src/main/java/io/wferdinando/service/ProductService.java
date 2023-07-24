package io.wferdinando.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.wferdinando.dto.ProductDTO;
import io.wferdinando.entity.ProductEntity;
import io.wferdinando.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        repository.findAll().stream().forEach(p -> {
            products.add(
                    ProductDTO.builder()
                            .name(p.getName())
                            .description(p.getDescription())
                            .category(p.getCategory())
                            .model(p.getModel())
                            .price(p.getPrice())
                            .build());
        });
        return products;
    }

    public ProductDTO getProductById(Long id) {
        ProductEntity entity = repository.findById(id);
        ProductDTO productDTO = ProductDTO.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .model(entity.getModel())
                .price(entity.getPrice())
                .build();
        return productDTO;
    }

    public ProductDTO createNewProduct(ProductDTO productDTO) {

        ProductEntity productEntity = ProductEntity.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .category(productDTO.getCategory())
                .model(productDTO.getModel())
                .price(productDTO.getPrice())
                .build();
        repository.persist(productEntity);
        return ProductDTO.builder()
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .category(productEntity.getCategory())
                .model(productEntity.getModel())
                .price(productEntity.getPrice())
                .build();
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        ProductEntity entity = repository.findById(id);
        entity.setId(id);
        entity.setName(productDTO.getName());
        entity.setDescription(productDTO.getDescription());
        entity.setCategory(productDTO.getCategory());
        entity.setModel(productDTO.getModel());
        entity.setPrice(productDTO.getPrice());
        repository.persist(entity);

        ProductDTO product = ProductDTO.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .model(entity.getModel())
                .price(entity.getPrice())
                .build();
        return product;
    }

    public void deleteProduct(Long id) {

        try {
            if (this.getProductById(id) != null) {
                repository.deleteById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage() + e.getCause());
        }
    }

}
