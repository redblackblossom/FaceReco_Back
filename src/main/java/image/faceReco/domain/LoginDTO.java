package image.faceReco.domain;


import lombok.Data;

@Data
public class LoginDTO {
    String userId;
    String pwd;
    Customer LoginDTOToCustomer(){
        return new Customer(this);
    }
}
