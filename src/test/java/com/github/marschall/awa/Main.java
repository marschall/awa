package com.github.marschall.awa;

import java.lang.reflect.Method;

public class Main {

  public static void main(String[] args) {
    for (Method method : SchedulerService.class.getDeclaredMethods()) {
      System.out.println(method);
    }
  }

}
