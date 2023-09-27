package com.tassneemaltaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/customers")
public class Main {
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private final CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRegistry(
            String name,
            String email,
            Integer age
    ) {}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRegistry registry) {
        Customer customer = new Customer();
        customer.setName(registry.name());
        customer.setEmail(registry.email());
        customer.setAge(registry.age());
        customerRepository.save(customer);

    }

    @DeleteMapping("{customerId}")
    public void deleteUser(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }


}
