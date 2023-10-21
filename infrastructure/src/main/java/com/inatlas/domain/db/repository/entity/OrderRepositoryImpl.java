package com.inatlas.domain.db.repository.entity;

import com.inatlas.domain.db.entity.OrderDB;
import com.inatlas.domain.db.entity.OrderItemDB;
import com.inatlas.domain.db.mapper.OrderMapper;
import com.inatlas.domain.db.repository.jpa.ItemOrderJpaRepository;
import com.inatlas.domain.db.repository.jpa.OrderJpaRepository;

import com.inatlas.domain.entity.Order;
import com.inatlas.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderJpaRepository orderJpaRepository;
  private ItemOrderJpaRepository itemOrderJpaRepository;
  private final OrderMapper orderMapper;

  public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository, OrderMapper orderMapper) {
    this.orderJpaRepository = orderJpaRepository;
    this.orderMapper = orderMapper;
  }

  @Override
  public Order createNewOrder() {
    return orderMapper.toDomain(orderJpaRepository.save(new OrderDB()));

  }

  @Override
  public Optional<Order> getOrderByID(Integer id) {
    return orderJpaRepository.findById(id).map(orderMapper::toDomain);
  }

@Override
public void setCompleted(Integer id) {

    Optional<OrderDB> optionalOrder = orderJpaRepository.findById(id);
    if (optionalOrder.isPresent()) {
        OrderDB order = optionalOrder.get();
        order.setComplete(true);
        orderJpaRepository.save(order);
    }

}

  @Override
  public void addProductToOrder(Integer orderId, Integer itemId, Integer amount) {

    Optional<OrderDB> optionalOrder = orderJpaRepository.findById(orderId);
    if (optionalOrder.isPresent()) {
      OrderDB order = optionalOrder.get();
      OrderItemDB orderItem = new OrderItemDB();
      orderItem.setAmount(amount);
      List<OrderItemDB> items = order.getItems();
      items.add(orderItem);
      orderJpaRepository.save(order);
    }



  }

  @Override
  public void updateItemOrderFromOrder(Integer orderId, Integer itemId, Integer amount) {

      Optional<OrderDB> optionalOrder = orderJpaRepository.findById(orderId);

      if (optionalOrder.isPresent()) {
        OrderDB order = optionalOrder.get();

        itemOrderJpaRepository.findById(itemId).ifPresent(orderItem -> {
          orderItem.setAmount(orderItem.getAmount() - amount);
          itemOrderJpaRepository.save(orderItem);
        });

      }

  }
}
