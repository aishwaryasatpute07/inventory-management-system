package Backend.backend.model;

public record UserDTO(Long userId, String userName,String userPassword, String userEmail, String userAddress, Boolean isUser) {
}
