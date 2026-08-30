package com.jbs.posbe.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jbs.posbe.dto.request.CustomerPatchDto;
import com.jbs.posbe.dto.request.CustomerRequestDto;
import com.jbs.posbe.dto.response.CustomerResponseDto;
import com.jbs.posbe.entity.Customer;
import com.jbs.posbe.repository.CustomerRepository;
import com.jbs.posbe.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;

	@Override
	public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
		if (customerRequestDto == null) {
			throw new IllegalArgumentException("Company cannot be null");
		}
		
		Customer customer = new Customer();
		customer.setCname(customerRequestDto.getCname());
		customer.setCmobile(customerRequestDto.getCmobile());
		customer.setActive(customerRequestDto.getActive() != null ? 
				customerRequestDto.getActive() : true);
		Customer savedCustomer = customerRepository.save(customer);
		
		return convertToDto(savedCustomer);
	}

	@Override
	public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {
		Page<Customer> customers = customerRepository.findAll(pageable);
		return customers.map(this::convertToDto);
	}

	@Override
	public CustomerResponseDto getCustomerById(Long customerId) {
		Customer customer = getCustomerEntity(customerId);
		return convertToDto(customer);
	}

	@Override
	public CustomerResponseDto updateCustomer(Long customerId, 
			CustomerPatchDto customerPatchDto) {
		
		Customer customer = getCustomerEntity(customerId);
		
		if(customerPatchDto.getCname() != null) {
			customer.setCname(customerPatchDto.getCname());
		}
		
		if(customerPatchDto.getCmobile() != null) {
			customer.setCmobile(customerPatchDto.getCmobile());
		}
		
		if(customerPatchDto.getActive() != null) {
			customer.setActive(customerPatchDto.getActive());
		}
		
		Customer updatedCustomer = customerRepository.saveAndFlush(customer);
		
		return convertToDto(updatedCustomer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		
		Customer customer = getCustomerEntity(customerId);
		customerRepository.delete(customer);
	}
	
	// Helper methods
	private Customer getCustomerEntity(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> 
				new RuntimeException("Customer not found with id: " + customerId));
	}
	
	private CustomerResponseDto convertToDto(Customer customer) {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setCustomerId(customer.getCustomerId());
		customerResponseDto.setCname(customer.getCname());
		customerResponseDto.setCmobile(customer.getCmobile());
		customerResponseDto.setActive(customer.isActive());
		customerResponseDto.setCreatedAt(customer.getCreatedAt().toString());
		customerResponseDto.setUpdatedAt(customer.getUpdatedAt().toString());
		return customerResponseDto;
	}

}
