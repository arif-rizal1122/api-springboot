package com.restfull.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.api.entity.User;
import com.restfull.api.model.AddressResponse;
import com.restfull.api.model.CreateAddressRequest;
import com.restfull.api.model.WebResponse;
import com.restfull.api.service.AddressService;

@RestController
@RequestMapping("/api/v1/addr")
public class AddressController {
    

    @Autowired
    private AddressService addressService;

    @PostMapping(
        path = "contacts/{contactId}/addresses"
    )
    public WebResponse<AddressResponse> create(User user, 
            @RequestBody CreateAddressRequest request, 
            @PathVariable("contactId") String contactId){

            request.setContactId(contactId);
            AddressResponse response = addressService.create(user, request);
            return WebResponse.<AddressResponse>builder().data(response).build();
    }


}
