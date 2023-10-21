/**
 * This is a mapper interface for converting between Product and ProductDB entities.
 * It uses MapStruct library and is annotated with @Mapper and @ComponentModel.
 * The mapper interface extends AbstractBBDDMapper  interface.
 * It provides mapping methods for converting Product objects to ProductDB objects and vice versa.
 * The componentModel attribute is set to "spring" to enable Spring component model.
 */
package com.inatlas.domain.db.mapper;

import com.inatlas.domain.db.entity.OrderDB;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends AbstractBBDDMapper<com.inatlas.domain.entity.Order, OrderDB> {
}