package image.faceReco.repository.customer;

import image.faceReco.domain.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MybatisCustomerRepository implements CustomerRepository {
    private final CustomerMapper customerMapper;

    @Override
    public List<Customer> findByUserId(String userId) {
        return customerMapper.findByUserId(userId);
    }

    @Override
    public Customer save(Customer customer) {
        customerMapper.save(customer);
        return customer;
    }
}
