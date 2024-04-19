package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FactorialControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculateFactorial_ValidInput_ZERO_ReturnsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/factorial").contentType(MediaType.APPLICATION_JSON).content("{\"factorial_num\": 0}")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json("{\"result\":\"1\"}"));
    }

    @Test
    public void calculateFactorial_ValidInput_ONE_ReturnsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/factorial").contentType(MediaType.APPLICATION_JSON).content("{\"factorial_num\": 1}")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json("{\"result\":\"1\"}"));
    }

    @Test
    public void calculateFactorial_ValidInput_ReturnsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/factorial").contentType(MediaType.APPLICATION_JSON).content("{\"factorial_num\": 5}")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json("{\"result\":\"120\"}"));
    }

    @Test
    public void calculateFactorial_ValidInput_ZERO_string_ReturnsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/factorial").contentType(MediaType.APPLICATION_JSON).content("{\"factorial_num\": \"0\"}")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json("{\"result\":\"1\"}"));
    }

    @Test
    public void calculateFactorial_Empty_Input_ReturnsBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/factorial").contentType(MediaType.APPLICATION_JSON).content("{\"factorial_num\": \"\"}")).andExpect(status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string("NullPointerException occurred"));
    }

    @Test
    public void calculateFactorial_NullInput_ReturnsBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/factorial").contentType(MediaType.APPLICATION_JSON).content("{\"factorial_num\": null}")).andExpect(status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string("NullPointerException occurred"));
    }

    @Test
    public void calculateFactorial_NegativeInput_ReturnsBadRequestWithErrorMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/factorial").contentType(MediaType.APPLICATION_JSON).content("{\"factorial_num\": -5}")).andExpect(status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string("The factorial of the given number is undefined"));
    }

    @Test
    public void calculateFactorial_FloatInput_ReturnsBadRequestWithErrorMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/factorial").contentType(MediaType.APPLICATION_JSON).content("{\"factorial_num\": 1.2}")).andExpect(status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string("The factorial of the given number is undefined"));
    }

    @Test
    public void calculateFactorial_String_FloatInput_ReturnsBadRequestWithErrorMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/factorial").contentType(MediaType.APPLICATION_JSON).content("{\"factorial_num\": \"1.2\"}")).andExpect(status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string("The factorial of the given number is undefined"));
    }
}
