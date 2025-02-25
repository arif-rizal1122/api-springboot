package com.restfull.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfull.api.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    
}
