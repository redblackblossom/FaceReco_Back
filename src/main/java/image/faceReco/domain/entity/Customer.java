package image.faceReco.domain.entity;

import image.faceReco.domain.DTO.JoinDTO;
import image.faceReco.domain.DTO.LoginDTO;
import lombok.Data;

@Data
public class Customer {
    int id;
    String userId;
    String pwd;
    String role;
    public Customer(LoginDTO loginDTO){
        this.userId = loginDTO.getUserId();
        this.pwd = loginDTO.getPwd();
    }
    public Customer(JoinDTO joinDTO){
        this.userId = joinDTO.getUserId();
        this.pwd = joinDTO.getPwd();
        this.role="Free";
    }

    public Customer(int id, String userId, String pwd, String role) {
        this.id = id;
        this.userId = userId;
        this.pwd = pwd;
        this.role = role;
    }
}
