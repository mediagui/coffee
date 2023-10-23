package com.inatlas.domain.db.repository.entity;

import com.inatlas.domain.db.entity.OrderDB;
import com.inatlas.domain.db.entity.OrderItemDB;
import com.inatlas.domain.db.mapper.OrderMapper;
import com.inatlas.domain.db.repository.jpa.ItemOrderJpaRepository;
import com.inatlas.domain.db.repository.jpa.OrderJpaRepository;
import com.inatlas.domain.db.repository.jpa.ProductJpaRepository;
import com.inatlas.domain.entity.Order;
import com.inatlas.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(rollbackFor = {Exception.class, RuntimeException.class})
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderJpaRepository orderJpaRepository;
  private final ProductJpaRepository productJpaRepository;
  private final OrderMapper orderMapper;


  public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository, ProductJpaRepository productJpaRepository, OrderMapper orderMapper) {
    this.orderJpaRepository = orderJpaRepository;
    this.productJpaRepository = productJpaRepository;
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
  public Optional<Order> addProductToCurrentOrder(Integer productId, Integer amount) {

    // Get the actual order or create a new one
    OrderDB orderDB = orderJpaRepository.findOrderDBByCompleteFalse().orElseGet(OrderDB::new);

    Optional<List<OrderItemDB>> optItems = Optional.ofNullable(orderDB.getItems());

    optItems.ifPresentOrElse(items -> {

              Optional<OrderItemDB> item = items.stream()
                      .filter(itemDB -> itemDB.getProduct() != null) // May be unnecessary
                      .filter(itemDB -> itemDB.getProduct().getId() > 0 && itemDB.getProduct().getId() == productId)
                      .findFirst();

              if (item.isPresent())
                includeOrUpdateItemInOrderDB(orderDB, item.get(), amount);
              else {
                OrderItemDB newItem = getNewOrderItemDB(productId, amount);
                includeOrUpdateItemInOrderDB(orderDB, newItem, amount);
              }
            },
            () -> {
              OrderItemDB newItem = getNewOrderItemDB(productId, amount);
              includeOrUpdateItemInOrderDB(orderDB, newItem, amount);
            });


    return Optional.of(orderMapper.toDomain(orderJpaRepository.save(orderDB)));


  }

  private OrderItemDB getNewOrderItemDB(Integer productId, Integer amount) {
    OrderItemDB i = new OrderItemDB();
    i.setProduct(productJpaRepository.findById(productId).orElseThrow());
    i.setUnitPrice(i.getProduct().getPrice());
    i.setAmount(amount);
    i.setTotal(i.getUnitPrice() * i.getAmount());
    return i;
  }

  private void includeOrUpdateItemInOrderDB(OrderDB orderDB, OrderItemDB orderItemDB, int amount) {

    Optional<List<OrderItemDB>> itemList = Optional.ofNullable(orderDB.getItems());

    itemList.ifPresentOrElse(items ->
              items.stream()
                      .filter(itemDB -> itemDB.getId().equals(orderItemDB.getId()))
                      .findFirst()
                      .ifPresentOrElse(item -> {
                                item.setAmount(orderItemDB.getAmount() + amount);
                                item.setTotal(orderItemDB.getAmount() * orderItemDB.getUnitPrice());
                                item.setProduct(orderItemDB.getProduct());
                              },
                              () -> orderDB.getItems().add(getNewOrderItemDB(orderItemDB.getProduct().getId(), amount))
                              )
            ,
            () -> {
              orderDB.setItems(new ArrayList<>());
              orderDB.getItems().add(orderItemDB);
            }
    );

    orderItemDB.setOrderDB(orderDB);
  }


  @Override
  public Optional<Order> getActualOrder() {
    return orderJpaRepository.findOrderDBByCompleteFalse().map(orderMapper::toDomain);
  }

  @Override
  public Optional<Order> payOrder() {
    Optional<OrderDB> orderDB = orderJpaRepository.findOrderDBByCompleteFalse();
    orderDB.ifPresent(order -> {
      finishOrder(order);
      orderJpaRepository.save(order);
    });
    return orderDB.map(orderMapper::toDomain);
  }

  private void finishOrder(OrderDB order) {
    order.setOrderDate(LocalDateTime.now());
    order.setComplete(true);
    updateOrderTotalAmount(order);
  }

  private void updateOrderTotalAmount(OrderDB order){

      order.setTotal(order.getItems().stream().mapToDouble(OrderItemDB::getTotal).sum());

  }

  @Override
  public Optional<Order> getLastOrderCompleted() {
    return orderJpaRepository.findLastOrderDBCompleted().map(orderMapper::toDomain);
  }
}
