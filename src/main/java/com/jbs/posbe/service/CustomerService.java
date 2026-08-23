package com.jbs.posbe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jbs.posbe.dto.request.CustomerPatchDto;
import com.jbs.posbe.dto.request.CustomerRequestDto;
import com.jbs.posbe.dto.response.CustomerResponseDto;

public interface CustomerService {
	
	CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);
	Page<CustomerResponseDto> getAllCustomers(Pageable pageable);
	CustomerResponseDto getCustomerById(Long customerId);
	CustomerResponseDto updateCustomer(Long customerId, CustomerPatchDto customerPatchDto);
	void deleteCustomer(Long customerId);

}
