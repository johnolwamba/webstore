package customer.web;

import customer.service.CustomerDTO;
import customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @GetMapping("/customer/{customerId}")
    public CustomerDTO getCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PutMapping("/customer/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable("customerId") Long customerId,
                                      @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerDTO, customerId);
    }

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}
