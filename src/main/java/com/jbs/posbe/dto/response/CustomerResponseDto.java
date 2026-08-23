package com.jbs.posbe.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {
	
	private Long customerId;
	private String cname;
	private String cmobile;
	private Boolean active;
	private String createdAt;
	private String updatedAt;

}
