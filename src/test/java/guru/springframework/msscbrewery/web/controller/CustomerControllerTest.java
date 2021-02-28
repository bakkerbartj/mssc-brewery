package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.Assert.*;

@WebMvcTest(CustomerController.class)
@RunWith(SpringRunner.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    CustomerDto validCustomer;

    @Before
    public void setUp() throws Exception {
        validCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .Name("Rocky Balboa")
                .build();
    }

    @Test
    public void getCustomer() {
        if(validCustomer == null) {
            System.out.println("Out of customers ...");
        } else {
            System.out.println("Customer name: " + validCustomer.getName());
        }
    }
}