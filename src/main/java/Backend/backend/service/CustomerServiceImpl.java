package Backend.backend.service;

import Backend.backend.model.Customer;
import Backend.backend.model.CustomerDTO;
import Backend.backend.model.User;
import Backend.backend.repository.CustomerRepository;
import Backend.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(Long customerId) {
        return customerRepository.findById((customerId)).map(this::convertToDTO);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);

        String email = customer.getCustomerEmail();
        String password = customer.getCustomerPassword();
        String username = customer.getCustomerName();
        Long u_id = customer.getCustomerId();
        Boolean isCustomer = true;

        User userCustomer = new User(email, password, username ,"","",u_id, isCustomer);

        if(customer.getUser())
        {
            User savedUser = userRepository.save(userCustomer);
        }

        return convertToDTO(savedCustomer);
    }


    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        customer.setCustomerName(customerDTO.customerName());
        customer.setCustomerAddress(customerDTO.customerAddress());
        Customer updatedCustomer = customerRepository.save(customer);
        return convertToDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    //Conversion methods between DTO and Entity
    private CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerAddress(), customer.getUser());
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.customerName());
        customer.setCustomerAddress(customerDTO.customerAddress());
        return customer;
    }
}
