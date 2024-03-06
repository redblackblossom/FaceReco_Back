package image.faceReco.service.customer;

import image.faceReco.domain.entity.Customer;
import image.faceReco.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public List<Customer> findByUserId(String userId) {
        return customerRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
