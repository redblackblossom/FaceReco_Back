package image.faceReco.repository;

import image.faceReco.domain.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findByUserId(String userId);
    Customer save(Customer customer);
}
