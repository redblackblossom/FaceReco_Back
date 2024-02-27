package image.faceReco.controller;

import image.faceReco.domain.Customer;
import image.faceReco.domain.JoinDTO;
import image.faceReco.domain.LoginDTO;
import image.faceReco.service.CustomerServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
     public ResponseEntity<String> registerCustomer(@RequestBody JoinDTO joinDTO){
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

    @GetMapping("/test")
    public String test(){
        return "good test!";
    }

    /*
    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody LoginDTO loginDTO){
        System.out.println(loginDTO.getUserId()+ "  "+ loginDTO.getPwd());
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        header.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PATCH,DELETE");
        header.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type,Authorization");
        header.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return ResponseEntity.status(500).headers(header).body("good");
    }
    */

    @GetMapping("/")
    ResponseEntity<String> index(){
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body("success");
    }
}
