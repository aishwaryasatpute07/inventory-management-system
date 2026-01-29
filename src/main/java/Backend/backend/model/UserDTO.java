package Backend.backend.model;

public record UserDTO(Long userId, String fullName, String userEmail, String userPassword, String confirmPassword, String userAddress, String username, String securityQuestion, String securityAnswer, String companyName, String industry, Boolean isUser)  {
}
