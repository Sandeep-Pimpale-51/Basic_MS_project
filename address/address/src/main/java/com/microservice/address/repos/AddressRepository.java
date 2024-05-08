package com.microservice.address.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.address.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{


}
