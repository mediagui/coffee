/**
 * This is an abstract mapper interface for converting between domain objects and database entities.
 * It provides methods to convert a single object, a list of objects, and vice versa.
 */
package com.inatlas.domain.db.mapper;

import java.util.List;

/**
 * This is an abstract mapper interface for converting between domain objects and database entities.
 * It provides methods to convert a single object, a list of objects, and vice versa.
 * @param <D> Domain object
 * @param <T> DTO object
 */
public interface AbstractDTOMapper<D, T> {
  D toDomain(T dto);
  T toDTO(D domain);
  List<D> toDomainList(List<T> dtoList);
  List<T> toDTOList(List<D> domainList);
}
