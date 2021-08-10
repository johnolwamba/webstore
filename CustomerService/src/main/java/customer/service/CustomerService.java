package customer.service;

import customer.data.CustomerRepository;
import customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerAdapter.getCustomerFromCustomerDTO(customerDTO);
        customerRepository.save(customer);
        return customerDTO;
    }

    public CustomerDTO getCustomer(Long customerId) {
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isPresent()) {
            return CustomerAdapter.getCustomerDTOFromCustomer(result.get());
        } else {
            return null;
        }
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Long customerId) {
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isPresent()) {
            Customer savedCustomer = result.get();
            Customer updatedCustomer = CustomerAdapter.getCustomerFromCustomerDTO(customerDTO);
            savedCustomer.update(updatedCustomer);
            return customerDTO;
        } else {
            return null;
        }
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

}
