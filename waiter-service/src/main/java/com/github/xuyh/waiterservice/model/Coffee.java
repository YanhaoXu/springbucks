package com.github.xuyh.waiterservice.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "T_COFFEE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Coffee extends BaseEntity implements Serializable {
  private String name;

  @Type(
      type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmoun",
      parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
  private Money price;
}
