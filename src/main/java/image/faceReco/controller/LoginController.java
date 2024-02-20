package image.faceReco.controller;

import image.faceReco.domain.Customer;
import image.faceReco.domain.JoinDTO;
import image.faceReco.domain.LoginDTO;
import image.faceReco.service.CustomerServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final CustomerServiceImp customerService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    ResponseEntity<String> registerCustomer(@RequestBody JoinDTO joinDTO){
        Customer savedCustomer = null;
        ResponseEntity response= null;
        try{
            String hashPwd = passwordEncoder.encode(joinDTO.getPwd());
            joinDTO.setPwd(hashPwd);
            savedCustomer = customerService.save(new Customer(joinDTO));
            if(savedCustomer.getId()>0){
                response = ResponseEntity.status(HttpStatus.CREATED).body("success");
            }
        } catch (Exception ex){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error : " + ex.getMessage());
        }

        return response;
    }
    /*
    @GetMapping("/login")
    ResponseEntity<String> loginCustomer(@ResponseBody LoginDTO loginDTO){

    }
     */
    @GetMapping("/")
    ResponseEntity<String> index(){
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body("sucees");
    }
}
