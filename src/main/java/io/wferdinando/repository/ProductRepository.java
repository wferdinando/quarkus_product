package io.wferdinando.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.wferdinando.entity.ProductEntity;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductEntity> {
    
}
