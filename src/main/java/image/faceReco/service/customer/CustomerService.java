package image.faceReco.service.customer;

import image.faceReco.domain.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findByUserId(String userId);
    Customer save(Customer customer);
}
