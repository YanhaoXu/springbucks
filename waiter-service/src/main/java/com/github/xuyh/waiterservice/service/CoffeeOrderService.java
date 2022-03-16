package com.github.xuyh.waiterservice.service;

import com.github.xuyh.waiterservice.model.Coffee;
import com.github.xuyh.waiterservice.model.CoffeeOrder;
import com.github.xuyh.waiterservice.model.OrderState;
import com.github.xuyh.waiterservice.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@Slf4j
@Transactional
public class CoffeeOrderService {
  @Autowired private CoffeeOrderRepository orderRepository;

  public CoffeeOrder get(Long id) {
    return orderRepository.getById(id);
  }

  public CoffeeOrder createOrder(String customer, Coffee... coffees) {
    CoffeeOrder order =
        CoffeeOrder.builder()
            .customer(customer)
            .items(new ArrayList<>(Arrays.asList(coffees)))
            .state(OrderState.INIT)
            .build();
    CoffeeOrder saved = orderRepository.save(order);
    log.info("New Order: {}", saved);
    return saved;
  }

  public boolean updateState(CoffeeOrder order, OrderState state) {

    if (state.compareTo(order.getState()) < 0) {
      log.warn("Wrong State order: {}, {}", state, order.getState());
      return false;
    }

    order.setState(state);
    orderRepository.save(order);
    log.info("Update Order: {}", order);
    return true;
  }
}
