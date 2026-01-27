package Backend.backend.service;

import Backend.backend.model.User;
import Backend.backend.model.UserDTO;
import Backend.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Optional<UserDTO> getUserById(Long userId) {
        return userRepository.findById((userId)).map(this::convertToDTO);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }


    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setUserName(userDTO.userName());
        user.setUserAddress(userDTO.userAddress());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    //Conversion methods between DTO and Entity
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUserId(), user.getUserName(),user.getUserPassword(), user.getUserEmail(), user.getUserAddress(), user.getUser());
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.userName());
        user.setUserAddress(userDTO.userAddress());
        user.setUserPassword(userDTO.userPassword());
        user.setUserEmail(userDTO.userEmail());
        user.setUser(userDTO.isUser());

        return user;
    }
}
