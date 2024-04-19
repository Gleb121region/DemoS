package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {

    @Bean
    public Counter nullPointerExceptionCounter(MeterRegistry meterRegistry) {
        return Counter.builder("factorial.exception.null_pointer")
                .description("Number of NullPointerException occurrences")
                .register(meterRegistry);
    }

    @Bean
    public Counter negativeInputCounter(MeterRegistry meterRegistry) {
        return Counter.builder("factorial.input.error.negative")
                .description("Number of negative input errors")
                .register(meterRegistry);
    }

    @Bean
    public Counter largeInputCounter(MeterRegistry meterRegistry) {
        return Counter.builder("factorial.input.error.large")
                .description("Number of large input errors")
                .register(meterRegistry);
    }

    @Bean
    public Counter floatingPointInputCounter(MeterRegistry meterRegistry) {
        return Counter.builder("factorial.input.error.floating_point")
                .description("Number of floating point input errors")
                .register(meterRegistry);
    }
}
