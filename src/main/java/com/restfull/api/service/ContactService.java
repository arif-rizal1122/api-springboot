package com.restfull.api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.api.entity.Contact;
import com.restfull.api.entity.User;
import com.restfull.api.model.ContactResponse;
import com.restfull.api.model.CreateContactRequest;
import com.restfull.api.repository.ContactRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContactService {
    
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;




    public ContactResponse create(User user, CreateContactRequest request){
    validationService.validate(request);
  
    Contact contact = new Contact();
    contact.setId(UUID.randomUUID().toString());
    contact.setFirstName(request.getFirstName());
    contact.setLastName(request.getLastName());
    contact.setEmail(request.getEmail());
    contact.setPhone(request.getPhone());
    contact.setUser(user);

    contactRepository.save(contact);
    return ContactResponse.builder()
    .id(contact.getId())
    .firstName(contact.getFirstName())
    .lastName(contact.getLastName())
    .email(contact.getEmail())
    .phone(contact.getPhone())
    .build();
    }

}
