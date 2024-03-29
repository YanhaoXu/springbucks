package com.github.xuyh.waiterservice.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;

public class MoneyDeserializer extends StdDeserializer<Money> {
  protected MoneyDeserializer() {
    super(Money.class);
  }

  @Override
  public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return Money.of(CurrencyUnit.of("CNY"), p.getDecimalValue());
  }
}
