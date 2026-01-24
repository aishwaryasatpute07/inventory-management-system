package Backend.backend.model;

public record UserDTO(String userEmail, String userName, String userLastName, String userFirstName, Long userId, Boolean isCustomer) {
}
