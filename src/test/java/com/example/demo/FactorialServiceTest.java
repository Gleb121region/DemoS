package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class FactorialServiceTest {

    @Mock
    private MeterRegistry meterRegistry;

    @Mock
    private Counter negativeInputCounter;

    @Mock
    private Counter largeInputCounter;

    @Mock
    private Counter floatingPointInputCounter;

    @Mock
    private Timer timer;

    private FactorialService factorialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(meterRegistry.timer(anyString())).thenReturn(timer);
        factorialService = new FactorialService(meterRegistry, negativeInputCounter, largeInputCounter, floatingPointInputCounter);
    }


    @Test
    public void testCalculateFactorial() {
        when(timer.record(any(Supplier.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return ((Supplier<?>) args[0]).get();
        });

        String result = factorialService.calculateFactorial(0);
        assertEquals("1", result);
    }

    @Test
    public void testCalculateFactorial1() {
        when(timer.record(any(Supplier.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return ((Supplier<?>) args[0]).get();
        });

        String result = factorialService.calculateFactorial(1);
        assertEquals("1", result);
    }

    @Test
    void testCalculateFactorialWithPositiveNumber() {
        when(timer.record(any(Supplier.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return ((Supplier<?>) args[0]).get();
        });

        String result = factorialService.calculateFactorial(5);
        assertEquals("120", result);
    }

    @Test
    public void testCalculateFactorialWithNegativeNumber() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> factorialService.calculateFactorial(-5));
    }

    @Test
    public void testCalculateFactorialWithLargeNumber() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> factorialService.calculateFactorial(101));
    }

    @Test
    public void testCalculateFactorialWithNonIntegerNumber() {
        Number nonIntegerNumber = Mockito.mock(Number.class);
        when(nonIntegerNumber.doubleValue()).thenReturn(5.5);
        Assertions.assertThrows(IllegalArgumentException.class, () -> factorialService.calculateFactorial(nonIntegerNumber));
    }
}
