package com.inatlas.domain.db.mapper;

import com.inatlas.domain.entity.OrderItem;
import com.inatlas.infra.api.dto.OrderItemDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = ProductDTOMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderItemDTOMapper extends AbstractDTOMapper<OrderItem, OrderItemDTO>{
}
