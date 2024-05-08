package com.microservice.student.externals;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.student.responses.AddressResponse;

@FeignClient("ADDRESS-SERVICE")
public interface AddressResponseFeign {


//	single address
	@GetMapping("address/{id}")
	public ResponseEntity<AddressResponse> getSingleAddressResponse(@PathVariable Long id); 

}
