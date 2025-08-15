package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        customerMap = new HashMap<>();

        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .id(UUID.fromString("0aedda30-c0cb-43e5-9ad8-5eaaff064c34"))
                .customerName("Jonathan")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Christopher")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Carina")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customerDTO1.getId(), customerDTO1);
        customerMap.put(customerDTO2.getId(), customerDTO2);
        customerMap.put(customerDTO3.getId(), customerDTO3);
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        log.debug("In List customers");
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        log.debug("In get customer by id: " + id);
        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        CustomerDTO savedCustomerDTO = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName(customerDTO.getCustomerName())
                .version(customerDTO.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomerDTO.getId(), savedCustomerDTO);

        return savedCustomerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO existingCustomerDTO = customerMap.get(customerId);

        existingCustomerDTO.setCustomerName(customerDTO.getCustomerName());
        existingCustomerDTO.setVersion(customerDTO.getVersion());
        existingCustomerDTO.setLastModifiedDate(LocalDateTime.now());

        return existingCustomerDTO;
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public CustomerDTO patchCustomer(UUID customerId, CustomerDTO customerDTO) {

        CustomerDTO existingCustomerDTO = customerMap.get(customerId);

        if (StringUtils.hasText(customerDTO.getCustomerName())) {
            existingCustomerDTO.setCustomerName(customerDTO.getCustomerName());
        }

        if (customerDTO.getVersion() != null) {
            existingCustomerDTO.setVersion(customerDTO.getVersion());
        }

        existingCustomerDTO.setLastModifiedDate(LocalDateTime.now());

        return existingCustomerDTO;

    }
}
