package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class FactorialService {

    private final MeterRegistry meterRegistry;
    private final Counter negativeInputCounter;
    private final Counter largeInputCounter;
    private final Counter floatingPointInputCounter;

    public String calculateFactorial(Number factorialNum) {
        var value = factorialNum.intValue();
        if (value < 0) {
            negativeInputCounter.increment();
            throw new IllegalArgumentException("The factorial of the given number is undefined");
        }
        if (value > 100) {
            largeInputCounter.increment();
            throw new IllegalArgumentException("Number is too large");
        }
        double valueDouble = factorialNum.doubleValue();
        if (valueDouble - value != 0) {
            floatingPointInputCounter.increment();
            throw new IllegalArgumentException("The factorial of the given number is undefined");
        }
        Timer timer = this.meterRegistry.timer("factorial.calculation.time");
        return timer.record(() -> {
            BigInteger result = BigInteger.ONE;
            for (int i = 1; i <= value; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result.toString();
        });
    }
}
