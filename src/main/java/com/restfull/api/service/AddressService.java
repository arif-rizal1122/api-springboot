package com.restfull.api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.restfull.api.entity.Address;
import com.restfull.api.entity.Contact;
import com.restfull.api.entity.User;
import com.restfull.api.model.AddressResponse;
import com.restfull.api.model.CreateAddressRequest;
import com.restfull.api.repository.AddressRepository;
import com.restfull.api.repository.ContactRepository;

public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;


    public AddressResponse create(User user, CreateAddressRequest request){
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "contact is not found"));

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setContact(contact);
        address.setPostalCode(request.getPostalCode());
        address.setStreet(request.getStreet());
        address.setProvince(request.getProvince());
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());

        addressRepository.save(address);
        return toAddressResponse(address);
    }


    private AddressResponse toAddressResponse(Address address){
        return AddressResponse.builder()
        .id(address.getId())
        .street(address.getStreet())
        .postalCode(address.getPostalCode())
        .province(address.getProvince())
        .city(address.getCity())
        .country(address.getCountry())
        .build();
    }



}
