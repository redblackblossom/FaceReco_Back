package image.faceReco.domain.DTO;


import image.faceReco.domain.entity.Customer;
import lombok.Data;

@Data
public class LoginDTO {
    String userId;
    String pwd;
    Customer LoginDTOToCustomer(){
        return new Customer(this);
    }
}
