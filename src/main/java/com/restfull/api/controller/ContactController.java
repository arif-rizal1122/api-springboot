package com.restfull.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.api.entity.User;
import com.restfull.api.model.ContactResponse;
import com.restfull.api.model.CreateContactRequest;
import com.restfull.api.model.PagingResponse;
import com.restfull.api.model.SearchContactRequest;
import com.restfull.api.model.UpdateContactRequest;
import com.restfull.api.model.WebResponse;
import com.restfull.api.service.ContactService;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @PostMapping(
        path = "/contact/create",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> create(User user, @RequestBody CreateContactRequest request) {
        ContactResponse contactResponse = contactService.create(user, request);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }


    @GetMapping(
        path = "/contact/{contactId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> get(User user, @PathVariable("contactId") String contactId){
        ContactResponse contactResponse =  contactService.get(user, contactId);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }


    @PutMapping(
        path = "/contact/update/{contactId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> update(User user,
                                               @RequestBody UpdateContactRequest request,
                                               @PathVariable("contactId") String contactId) {

        request.setId(contactId);

        ContactResponse contactResponse = contactService.update(user, request);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @DeleteMapping(
        path = "/contact/delete/{contactId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable("contactId") String contactId){
        contactService.delete(user, contactId);
        return WebResponse.<String>builder().data("OK").build();
    }


    @GetMapping(
        path = "/contacts/search",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ContactResponse>> search(User user,
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "email", required = false) String email,
    @RequestParam(value = "phone", required = false) String phone,
    @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
    @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
            SearchContactRequest request = SearchContactRequest.builder()
            .page(page)
            .size(size)
            .name(name)
            .email(email)
            .phone(phone)
            .build();

            Page<ContactResponse> contactResponses = contactService.search(user, request);
            return WebResponse.<List<ContactResponse>>builder()
            .data(contactResponses.getContent())
                .paging(PagingResponse.builder()
                .currentPage(contactResponses.getNumber())
                .totalPage(contactResponses.getTotalPages())
                .size(contactResponses.getSize())
                .build())
            .build();
            }

}
