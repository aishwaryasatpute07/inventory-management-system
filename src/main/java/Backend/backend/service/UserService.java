package Backend.backend.service;

import Backend.backend.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();

    List<UserDTO> findByUserEmailIgnoreCase(String userEmail);

    Optional<UserDTO> getUserById(Long userId);


    UserDTO saveUser(UserDTO userDTO);

    UserDTO updateUser(Long userId, UserDTO userDTO);

    void deleteUser(Long userId);

    boolean login(String username, String userPassword);
}
