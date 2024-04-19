package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FactorialController {

    private final FactorialService factorialService;
    private final Counter nullPointerExceptionCounter;

    @Operation(summary = "Calculate factorial")
    @PostMapping("/factorial")
    public ResponseEntity<?> calculateFactorial(@RequestBody FactorialRequest request) {
        try {
            Number factorialNum = request.factorialNum();
            String result = factorialService.calculateFactorial(factorialNum);
            return ResponseEntity.ok(new FactorialResponse(result));
        } catch (NullPointerException e) {
            nullPointerExceptionCounter.increment();
            return ResponseEntity.badRequest().body("NullPointerException occurred");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
