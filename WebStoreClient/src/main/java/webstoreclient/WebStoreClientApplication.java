package webstoreclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import webstoreclient.models.Address;
import webstoreclient.models.Contact;
import webstoreclient.models.Customer;

@SpringBootApplication
public class WebStoreClientApplication implements CommandLineRunner {
    @Autowired
    private RestOperations restTemplate;

    private String serverUrl = "http://localhost:8081/";

    public static void main(String[] args) {
        SpringApplication.run(WebStoreClientApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        //create customer
        Address address = new Address("1000N Street", "Fairfield", "52557");
        Contact contact = new Contact("+254718694198", "johnolwamba@gmail.com");

        Customer customer = new Customer(101L,"Johnstone","Ananda", contact, address);
        restTemplate.postForLocation(serverUrl + "customer", customer);
        Customer customer1 = restTemplate.getForObject(serverUrl + "customer/101", Customer.class);
        System.out.println(customer1);

        //update customer
        customer1.setFirstName("Otoyo");
        restTemplate.put(serverUrl + "customer/101", customer1);
        customer1 = restTemplate.getForObject(serverUrl + "customer/101", Customer.class);
        System.out.println(customer1);

        //delete customer
//        restTemplate.delete(serverUrl+"customer/101", customer1);


    }

}
