/**
 * This is an abstract mapper interface for converting between domain objects and database entities.
 * It provides methods to convert a single object, a list of objects, and vice versa.
 */
package com.inatlas.domain.db.mapper;

import java.util.List;

public interface AbstractBBDDMapper<DOMAIN, BBDD> {
  DOMAIN toDomain(BBDD bbdd);
  BBDD toBBDD(DOMAIN domain);
  List<DOMAIN> toDomainList(List<BBDD> bbddList);
  List<BBDD> toBBDDList(List<DOMAIN> domainList);
}
