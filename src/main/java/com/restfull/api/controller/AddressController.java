package com.restfull.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.api.entity.User;
import com.restfull.api.model.AddressResponse;
import com.restfull.api.model.CreateAddressRequest;
import com.restfull.api.model.UpdateAddressRequest;
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



    @PutMapping(
        path = "/contacts/{contactId}/addresses/{addressId}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> update(User user,
        @RequestBody UpdateAddressRequest request,
        @PathVariable("contactId") String contactId,
        @PathVariable("addressId") String addressId){

        request.setContactId(contactId);
        request.setAddressId(addressId);
    
        AddressResponse response = addressService.update(user, request);
        return WebResponse.<AddressResponse>builder().data(response).build();
    }


    @DeleteMapping(
        path = "/contacts/{contactId}/addresses/{addressId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> remove(User user,
        @PathVariable("contactId") String contactId,
        @PathVariable("addressId") String addressId){
        addressService.remove(user, contactId, addressId); 
        return WebResponse.<String>builder().data("OK").build();
    }


    @GetMapping(
        path = "/contacts/{contactId}/addresses",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AddressResponse>> listAddress(User user,
        @PathVariable("contactId") String contactId
        ) {
        List<AddressResponse> addressResponse = addressService.listAddress(user, contactId);
        return WebResponse.<List<AddressResponse>>builder().data(addressResponse).build();
    }

}
