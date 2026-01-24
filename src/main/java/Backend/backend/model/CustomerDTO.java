package Backend.backend.model;

public record CustomerDTO(Long customerId,String customerName, String customerEmail, String customerAddress, Boolean isUser) {
}
