package com.github.xuyh.waiterservice.controller;

import com.github.xuyh.waiterservice.model.Coffee;
import com.github.xuyh.waiterservice.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/coffee")
public class CoffeeController {
  @Autowired private CoffeeService coffeeService;

  //  @PostMapping(path = "/" ,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  //  @ResponseStatus(HttpStatus.CREATED)
  //  public Coffee addCoffeeWithoutBindingResult(@Value ) {
  //    return coffeeService.saveCoffee()
  //  }

  @GetMapping(path = "/", params = "!name")
  public List<Coffee> getAll() {
    return coffeeService.getAllCoffee();
  }

  @GetMapping("/{id}")
  public Coffee getById(@PathVariable Long id) {
    Coffee coffee = coffeeService.getCoffee(id);
    log.info("Coffee {}:", coffee);
    return coffee;
  }

  @GetMapping(value = "/", params = "name")
  public Coffee getByName(@RequestParam String name) {
    return coffeeService.getCoffee(name);
  }
}
