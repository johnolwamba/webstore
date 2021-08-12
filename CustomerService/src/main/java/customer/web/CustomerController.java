package customer.web;

import customer.service.CustomerDTO;
import customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO responseDTO = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable("customerId") Long customerId) {
        CustomerDTO responseDTO = customerService.getCustomer(customerId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable("customerId") Long customerId,
                                      @RequestBody CustomerDTO customerDTO) {
        CustomerDTO responseDTO = customerService.updateCustomer(customerDTO, customerId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
