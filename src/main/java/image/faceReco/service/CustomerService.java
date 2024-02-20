package image.faceReco.service;

import image.faceReco.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findByUserId(String userId);
    Customer save(Customer customer);
}
