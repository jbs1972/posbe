package com.jbs.posbe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerPatchDto {
	
	private String cname;
	private String cmobile;
	private Boolean active;

}
