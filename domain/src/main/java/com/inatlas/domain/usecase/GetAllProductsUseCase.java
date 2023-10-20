package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Product;

import java.util.List;

public interface GetAllProductsUseCase {
  List<Product> getAllProducts();
}
