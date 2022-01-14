package com.jumia.exercise.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSearchCustomers() throws Exception {
        mockMvc.perform(get("/customer").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchCustomersWithPagination() throws Exception {
        mockMvc.perform(get("/customer?page=0&size=10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(10));
    }

    @Test
    public void testSearchCustomersWithFilters() throws Exception {
        mockMvc.perform(get("/customer?page=0&size=10&country=Cameroon&state=VALID").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].country").value("Cameroon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].state").value("VALID"));

    }


}
