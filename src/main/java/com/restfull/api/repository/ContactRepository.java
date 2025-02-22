package com.restfull.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restfull.api.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, String> {
    
}
