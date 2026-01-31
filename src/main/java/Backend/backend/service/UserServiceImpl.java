package Backend.backend.service;

import Backend.backend.model.User;
import Backend.backend.model.UserDTO;
import Backend.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByUserEmailIgnoreCase(String userEmail) {
        userEmail = userEmail.trim(); // remove extra spaces
        return userRepository.findByUserEmailIgnoreCase(userEmail) // use IgnoreCase
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    @Override
    public Optional<UserDTO> getUserById(Long userId) {
        return userRepository.findById((userId)).map(this::convertToDTO);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        boolean userExists = userRepository.existsByUserEmail(userDTO.userEmail());

        if (userExists) {
            throw new RuntimeException("User already exists with email: " + userDTO.userEmail());
        }

        if (!Objects.equals(userDTO.userPassword(), userDTO.confirmPassword())) {
            throw new RuntimeException("Password and Confirm Password do not match");
        }
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setfullName(userDTO.fullName());
        user.setUserAddress(userDTO.userAddress());
        user.setUser(userDTO.isUser());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public boolean login(String username, String userPassword) {
        return userRepository.login(username, userPassword).isPresent();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    //Conversion methods between DTO and Entity
    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getfullName(),
                user.getUserEmail(),     // ✅ email here
                user.getUserPassword(),  // ✅ password here (optional)
                null,                    // confirmPassword null for GET API
                user.getUserAddress(),
                user.getUsername(),
                user.getSecurityQuestion(),
                user.getSecurityAnswer(),
                user.getCompanyName(),
                user.getIndustry(),
                user.getUser()
        );    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setfullName(userDTO.fullName());
        user.setUserEmail(userDTO.userEmail());
        user.setUserAddress(userDTO.userAddress());
        user.setUserPassword(userDTO.userPassword());
        user.setUsername(userDTO.username());
        user.setSecurityQuestion(userDTO.securityQuestion());
        user.setSecurityAnswer(userDTO.securityAnswer());
        user.setCompanyName(userDTO.companyName());
        user.setIndustry(userDTO.industry());
        user.setUser(userDTO.isUser());

        return user;
    }
}
