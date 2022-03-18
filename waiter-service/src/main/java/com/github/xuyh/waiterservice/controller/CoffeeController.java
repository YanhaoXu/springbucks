package com.github.xuyh.waiterservice.controller;

import com.github.xuyh.waiterservice.controller.request.NewCoffeeRequest;
import com.github.xuyh.waiterservice.model.Coffee;
import com.github.xuyh.waiterservice.service.CoffeeService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/coffee")
public class CoffeeController {
  @Autowired private CoffeeService coffeeService;

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Coffee addCoffeeWithoutBindingRequest(@Valid NewCoffeeRequest newCoffee) {
    return coffeeService.saveCoffee(newCoffee.getName(), newCoffee.getPrice());
  }

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Coffee addCoffeeWithoutBindingResult(@Valid @RequestBody NewCoffeeRequest newCoffee) {
    return coffeeService.saveCoffee(newCoffee.getName(), newCoffee.getPrice());
  }

  @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public List<Coffee> batchAddCoffee(@RequestParam("file") MultipartFile file) {
    List<Coffee> coffees = Lists.newArrayList();
    if (!file.isEmpty()) {
      try (BufferedReader reader =
          new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        String str;

        while ((str = reader.readLine()) != null) {
          String[] arr = StringUtils.split(str, " ");
          if (arr != null && arr.length == 2) {
            coffees.add(
                coffeeService.saveCoffee(
                    arr[0],
                    Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(arr[1]))));
          }
        }

      } catch (IOException e) {
        log.error("exception", e);
      }
    }
    return coffees;
  }

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
