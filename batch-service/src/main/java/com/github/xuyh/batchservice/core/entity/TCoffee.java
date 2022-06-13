package com.github.xuyh.batchservice.core.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TCoffee {
  private Long id;
  private String name;
  private Long price;
  private Date createTime;
  private Date updateTime;
}
