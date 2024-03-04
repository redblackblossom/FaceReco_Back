package image.faceReco.repository.customer;

import image.faceReco.domain.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findByUserId(String userId);
    Customer save(Customer customer);
}
