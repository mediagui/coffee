/**
 * This is an abstract mapper interface for converting between domain objects and database entities.
 * It provides methods to convert a single object, a list of objects, and vice versa.
 */
package com.inatlas.domain.db.mapper;

import java.util.List;

public interface AbstractDTOMapper<DOMAIN, DTO> {
  DOMAIN toDomain(DTO DTO);
  DTO toDTO(DOMAIN domain);
  List<DOMAIN> toDomainList(List<DTO> DTOList);
  List<DTO> toDTOList(List<DOMAIN> domainList);
}
