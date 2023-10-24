package com.inatlas.domain.usecase;

import com.inatlas.domain.entity.Product;

import java.util.Optional;

public interface GetOneProductUseCase {
  Optional<Product> getTheProduct(int id);
}
