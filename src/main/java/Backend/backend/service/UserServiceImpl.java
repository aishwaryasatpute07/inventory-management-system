package Backend.backend.service;

import Backend.backend.model.Customer;
import Backend.backend.model.User;
import Backend.backend.model.UserDTO;
import Backend.backend.repository.CustomerRepository;
import Backend.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<UserDTO> getAllUsers(){
        System.out.println(userRepository.findAll());
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long userId) {
        return userRepository.findById((userId)).map(this::convertToDTO);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);

        Long customerId = user.getUserId();
        String customerName = user.getUserFirstName();
        String customerEmail = user.getUserEmail();
        String customerPassword = user.getUserPassword();
        Boolean isUser = true;
        Customer customerUser = new Customer(customerId, customerName, customerEmail, customerPassword, "", isUser);

        if(user.getIsCustomer()){
            Customer savedCustomer = customerRepository.save(customerUser);
        }

        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setUserFirstName(userDTO.userFirstName());
        user.setUserLastName(userDTO.userLastName());
        user.setUserEmail(userDTO.userEmail());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    //Conversion methods between DTO and Entity
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUserEmail(), user.getUserFirstName(), user.getUserLastName(), user.getUserName(),user.getUserId(), user.getIsCustomer());
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserFirstName(userDTO.userFirstName());
        user.setUserLastName(userDTO.userLastName());
        user.setUserEmail(userDTO.userEmail());
        return user;
    }
}
