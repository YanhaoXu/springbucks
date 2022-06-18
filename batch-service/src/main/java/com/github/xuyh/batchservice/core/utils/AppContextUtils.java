package com.github.xuyh.batchservice.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContextUtils implements ApplicationContextAware {
  private static ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (this.context == null) this.context = applicationContext;
  }

  /**
   * 获取applicationContext
   *
   * @return
   */
  public static ApplicationContext getApplicationContext() {
    return context;
  }

  /**
   * 通过class获取Bean.
   *
   * @param requiredType
   * @return
   * @param <T>
   */
  public static <T> T getBean(Class<T> requiredType) {
    return getApplicationContext().getBean(requiredType);
  }

  /**
   * 通过name获取 Bean.
   *
   * @param name
   * @return
   */
  public static Object getBean(String name) {
    return getApplicationContext().getBean(name);
  }

  /**
   * 通过name,以及Clazz返回指定的Bean.
   *
   * @param name
   * @param requiredType
   * @return
   * @param <T>
   */
  public static <T> T getBean(String name, Class<T> requiredType) {
    return getApplicationContext().getBean(name, requiredType);
  }
}
