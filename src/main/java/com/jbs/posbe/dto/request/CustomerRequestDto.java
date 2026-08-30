package com.jbs.posbe.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDto {
	
	private String cname;
	
	@NotBlank(message = "Customer mobile number is required")
	@Size(max = 15, message = "Customer mobile number cannot exceed 15 characters")	
	private String cmobile;
	
	private Boolean active = true;
	
}
