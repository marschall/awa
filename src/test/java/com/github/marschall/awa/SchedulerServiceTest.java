package com.github.marschall.awa;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

class SchedulerServiceTest {

  @Test
  void methods() {
    for (Method method : SchedulerService.class.getDeclaredMethods()) {
      System.out.println(method);
    }
  }

}
