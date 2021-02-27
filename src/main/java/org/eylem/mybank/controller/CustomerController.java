package org.eylem.mybank.controller;

import org.eylem.mybank.dto.CustomerDto;
import org.eylem.mybank.service.CustomerService;
import org.eylem.mybank.entity.Customer;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@Valid @RequestBody CustomerDto customerDto){
        return customerService.create(customerDto.toCustomer()).customerDto();
    }

    @GetMapping(value = "/customers",params = {"page","size"})
    public List<CustomerDto> list(@Min(value = 0) @RequestParam("page") int page,@RequestParam("size") int size){
        return customerService.list(PageRequest.of(page, size)).stream()
                .map(Customer::customerDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable("id") UUID id){
        return customerService.get(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found!")).customerDto();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto update(@Valid @RequestBody CustomerDto customerDto){
        return customerService.update(customerDto.toCustomer()).customerDto();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id){
        customerService.delete(id);
    }
}
