package com.restfull.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
        path = "/contacts/{contactId}/addresses",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> create(User user, 
            @RequestBody CreateAddressRequest request, 
            @PathVariable("contactId") String contactId){

            request.setContactId(contactId);
            AddressResponse response = addressService.create(user, request);
            return WebResponse.<AddressResponse>builder().data(response).build();
    }


    @GetMapping(
        path = "/contacts/{contactId}/addresses/{addressId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> get(User user,
        @PathVariable("contactId") String contactId,
        @PathVariable("addressId") String addressId) {
        AddressResponse addressResponse = addressService.get(user, contactId, addressId);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }

}
