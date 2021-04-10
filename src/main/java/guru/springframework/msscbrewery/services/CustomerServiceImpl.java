package guru.springframework.msscbrewery.services;


import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().id(customerId)
                .Name("Roger Rabbit").build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomer(UUID beerId, CustomerDto customerDto) {
        // Do nothing
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.debug("Deleted customer ....");
    }
}
