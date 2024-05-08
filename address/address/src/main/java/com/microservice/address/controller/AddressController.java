package com.microservice.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.address.Address;
import com.microservice.address.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

//	create address
	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody Address address) {
		
		Address address1 = addressService.saveAddress(address);
		return ResponseEntity.status(HttpStatus.CREATED).body(address1);
	}

//	single address
	@GetMapping("/{id}")
	public ResponseEntity<Address> getSingleAddress(@PathVariable Long id) {
		Address address = addressService.getAddress(id);
		return ResponseEntity.ok(address);
	}


//	all address
	@GetMapping
	public ResponseEntity<List<Address>> getAddresss() {
		List<Address> allAddresss = addressService.getAllAddresss();
		return ResponseEntity.ok(allAddresss);
	}

//	delete address
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAddress(@PathVariable long id) {
		String result = addressService.deleteAddress(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

//  update address
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAddress(@RequestBody Address address, @PathVariable long id) {

		Address addressOptional = addressService.UpdateAddress(id, address);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(addressOptional);
	}
}
