package com.github.marschall.awa;

import static net.bytebuddy.dynamic.Transformer.ForMethod.withModifiers;
import static net.bytebuddy.matcher.ElementMatchers.isSynchronized;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.not;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.modifier.SynchronizationState;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

public class RemoveSynchronized {

  public static void premain(String arguments, Instrumentation instrumentation) {
    new AgentBuilder.Default()
    // the class named SchedulerService
    .type(ElementMatchers.nameEndsWith(".SchedulerService"))
    .transform((builder, type, classLoader, module) ->  builder.method(
              // all synchronized methods except load and unload
              isSynchronized().and(not(named("load").or(named("unload")))))
            .intercept(SuperMethodCall.INSTANCE)
            // remove synchronized
            .transform(withModifiers(SynchronizationState.PLAIN)))
    .installOn(instrumentation);
  }

}
