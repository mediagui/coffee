package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface GetAllProductsUseCase {
  Optional< List<Product>> getAllProducts();
}
