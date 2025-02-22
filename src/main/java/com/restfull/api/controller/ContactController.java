package com.restfull.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.api.entity.User;
import com.restfull.api.model.ContactResponse;
import com.restfull.api.model.CreateContactRequest;
import com.restfull.api.model.WebResponse;
import com.restfull.api.service.ContactService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @PostMapping(
        path = "/create/contact",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> create(User user, @RequestBody CreateContactRequest request){
        ContactResponse response = contactService.create(user, request);
        return WebResponse.<ContactResponse>builder().data(response).build();
    }



    



}
