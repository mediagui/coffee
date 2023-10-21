package com.inatlas.domain.db.mapper;

import com.inatlas.domain.db.entity.OrderItemDB;
import com.inatlas.domain.entity.OrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {ProductMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderItemMapper extends AbstractBBDDMapper<OrderItem, OrderItemDB>{
}
