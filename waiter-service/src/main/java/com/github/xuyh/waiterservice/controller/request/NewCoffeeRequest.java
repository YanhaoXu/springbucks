package com.github.xuyh.waiterservice.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class NewCoffeeRequest {
  @NotEmpty private String name;
}
