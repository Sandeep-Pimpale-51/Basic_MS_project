package com.microservice.employee.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

	private Long addressID;
	private String landmark;
	private String area;
	private String city;
	private String State;
}
