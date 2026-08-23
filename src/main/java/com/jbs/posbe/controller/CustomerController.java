package com.jbs.posbe.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbs.posbe.dto.ManagedApiResponse;
import com.jbs.posbe.dto.request.CustomerPatchDto;
import com.jbs.posbe.dto.request.CustomerRequestDto;
import com.jbs.posbe.dto.response.CustomerResponseDto;
import com.jbs.posbe.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	// ---------------------------------------------------------------------
	@Operation(
			tags = "Customers", summary = "Create a new customer", description = "Creates a new customer for sale.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Customer created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data"),
			@ApiResponse(responseCode = "500", description = "Error creating customer") })
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PostMapping
	public ResponseEntity<ManagedApiResponse<CustomerResponseDto>> saveCustomer(
			@Valid @RequestBody CustomerRequestDto dto) {
		
		CustomerResponseDto savedCustomer = customerService.createCustomer(dto);
		
		ManagedApiResponse<CustomerResponseDto> response = new ManagedApiResponse<>(
						HttpStatus.CREATED.value(),
						"Customer created successfully",
						savedCustomer
				);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// ---------------------------------------------------------------------
	@Operation(
			tags = "Customers", summary = "List customers", description = "Retrieve all customers.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Customers retrieved successfully"),
			@ApiResponse(responseCode = "500", description = "Error retrieving users") 
	})
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/page")
	public ResponseEntity<ManagedApiResponse<Page<CustomerResponseDto>>> getAllCustomers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		
		var pageable = PageRequest.of(page, size);
		
		Page<CustomerResponseDto> customersPage = customerService.getAllCustomers(pageable);
		
		ManagedApiResponse<Page<CustomerResponseDto>> response = new ManagedApiResponse<>(
				HttpStatus.OK.value(),
				"Customers retrieved successfully",
				customersPage
		);
		
		return ResponseEntity.ok(response);
	}
	
	// ---------------------------------------------------------------------
	@Operation(
			tags = "Customers", summary = "Get customer by ID", description = "Fetch customer by ID.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Customer retrieved successfully"),
			@ApiResponse(responseCode = "500", description = "Error retrieving customers") })
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/{customerId}")
	public ResponseEntity<ManagedApiResponse<CustomerResponseDto>> getCustomerById(@PathVariable Long customerId) {
		
		CustomerResponseDto customer = customerService.getCustomerById(customerId);
		
		ManagedApiResponse<CustomerResponseDto> response = new ManagedApiResponse<>(
				HttpStatus.OK.value(),
				"Customer retrieved successfully",
				customer
		);
		
		return ResponseEntity.ok(response);
	}
	
	// ---------------------------------------------------------------------
	@Operation(
			tags = "Customers", summary = "Update customer", description = "Update customer details (partial/total).")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "User updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data"),
			@ApiResponse(responseCode = "500", description = "Error updating user") })
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PatchMapping("/{customerId}")
	public ResponseEntity<ManagedApiResponse<CustomerResponseDto>> updateCustomer(
			@PathVariable Long customerId,
			@Valid @RequestBody CustomerPatchDto dto) {
		
		CustomerResponseDto updatedCustomer = customerService.updateCustomer(customerId, dto);
		
		ManagedApiResponse<CustomerResponseDto> response = new ManagedApiResponse<>(
				HttpStatus.OK.value(),
				"Customer updated successfully",
				updatedCustomer
		);
		
		return ResponseEntity.ok(response);
	}
	
	// ---------------------------------------------------------------------
	@Operation(
			tags = "Customers", summary = "Delete customer", description = "Delete customer by ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Customer not found"),
			@ApiResponse(responseCode = "500", description = "Error deleting customer")
	})
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<ManagedApiResponse<Void>> deleteCustomer(@PathVariable Long customerId) {
		
		customerService.deleteCustomer(customerId);
		
		ManagedApiResponse<Void> response = new ManagedApiResponse<>(
				HttpStatus.NO_CONTENT.value(),
				"Customer deleted successfully",
				null
		);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}
}
