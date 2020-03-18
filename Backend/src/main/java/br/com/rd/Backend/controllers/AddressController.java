package br.com.rd.Backend.controllers;

import br.com.rd.Backend.DTOs.AddressDTO;
import br.com.rd.Backend.models.Address;
import br.com.rd.Backend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/create-address")
    public ResponseEntity saveAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.saveAddress(addressDTO);
    }

    @GetMapping("/find-address/{id}")
    public ResponseEntity findAddressById(@PathVariable("id") Long id) {
        return addressService.findAddressById(id);
    }

    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity deleteAddress(@PathVariable("id") Long id) {
       return addressService.deleteAddressById(id);
    }
}
