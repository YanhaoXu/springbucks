package com.github.xuyh.waiterservice.controller;

import com.github.xuyh.waiterservice.controller.request.NewOrderRequest;
import com.github.xuyh.waiterservice.model.Coffee;
import com.github.xuyh.waiterservice.model.CoffeeOrder;
import com.github.xuyh.waiterservice.service.CoffeeOrderService;
import com.github.xuyh.waiterservice.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/order")
public class CoffeeOrderController {
  @Autowired private CoffeeOrderService orderService;

  @Autowired private CoffeeService coffeeService;

  @GetMapping("/{id}")
  public CoffeeOrder getOrder(@PathVariable("id") Long id) {
    CoffeeOrder order = orderService.get(id);
    log.info("Get Order: {}", order);
    return order;
  }

  @PostMapping(
      path = "/",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
    log.info("Receive new Order {}", newOrder);
    Coffee[] coffeeList =
        coffeeService.getCoffeeByName(newOrder.getItems()).toArray(new Coffee[] {});
    return orderService.createOrder(newOrder.getCustomer(), coffeeList);
  }
}
