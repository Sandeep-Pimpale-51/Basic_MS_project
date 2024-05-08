package com.microservice.address.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.address.Address;
import com.microservice.address.repos.AddressRepository;
import com.microservice.address.service.exceptions.ResourceNotFoundException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	public Address saveAddress(Address address) {

		return addressRepository.save(address);
	}

	public List<Address> getAllAddresss() {

		return addressRepository.findAll();
	}

	public Address getAddress(long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address with given Id not found :" + id));
	}

	
	public String deleteAddress(long id) {
		Address address= addressRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Address with given Id not found :" + id));

		addressRepository.delete(address);
		
		return "Address is deleted !!!!";
	}

	public Address UpdateAddress(long id, Address address) {
		addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address with given Id not found :" + id));
		address.setAddressID(id);
		return addressRepository.save(address);

	}

}
