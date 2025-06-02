package dev.franciscohernandez.lamptracker.service.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.franciscohernandez.lamptracker.service.repository.LampRepository;
import dev.franciscohernandez.lamptracker.service.models.LampModel;
import dev.franciscohernandez.lamptracker.service.models.ServiceResponse;


@RestController
@RequestMapping("/v1")
public class LampController {
    

    @Autowired
    LampRepository lampRepository;
    
    @GetMapping("/lamps")
    public Page<LampModel> getAllLamps(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "sizePerPage", defaultValue = "2") int sizePerPage,
        @RequestParam(name = "sortField", defaultValue = "status") String sortBy,
        @RequestParam(name = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection
    ) {

        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        return lampRepository.findAll(pageable);
    }

    @GetMapping("/lamp/count")
    public Long getCount() {
        try {
            return lampRepository.count();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting lamps", e);
        }
    }
    

    @GetMapping("/lamp/{id}")
    public LampModel getLampById(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID cannot be null or empty");
        }

        return lampRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lamp not found with ID: " + id));
    }

    @PostMapping("/lamp")
    public String createLamp(@RequestBody LampModel lamp) {
       try {
            return lampRepository.save(lamp).getId();
       } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating lamp", e);
       }
    }

    @PatchMapping("/lamp/{id}")
    public LampModel updateLamp(@PathVariable("id") String id, @RequestBody LampModel lamp) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }

        LampModel existingLamp = lampRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lamp not found with ID: " + id));

        // Only update fields if the incoming value is not null
        if (lamp.getStatus() != null) existingLamp.setStatus(lamp.getStatus());
        if (lamp.getCoordinates() != null) existingLamp.setCoordinates(lamp.getCoordinates());
        if (lamp.getInstalledDate() != null) existingLamp.setInstalledDate(lamp.getInstalledDate());
        if (lamp.getBarcode() != null) existingLamp.setBarcode(lamp.getBarcode());
        if (lamp.getModel() != null) existingLamp.setModel(lamp.getModel());
        if (lamp.getType() != null) existingLamp.setType(lamp.getType());
        if (lamp.getPower() != null) existingLamp.setPower(lamp.getPower());
        if (lamp.getBrand() != null) existingLamp.setBrand(lamp.getBrand());
        if (lamp.getProvider() != null) existingLamp.setProvider(lamp.getProvider());
        if (lamp.getChageCounter() != null) existingLamp.setChageCounter(existingLamp.getChageCounter() + 1);
        if (lamp.getDepartment() != null) existingLamp.setDepartment(lamp.getDepartment());
        if (lamp.getDistrict() != null) existingLamp.setDistrict(lamp.getDistrict());

        existingLamp.setLastMaintenanceDate(new Date());

        try {
            return lampRepository.save(existingLamp);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating lamp with ID: " + id, e);
        }
    }

    @DeleteMapping("/lamp/{id}")
    public ServiceResponse deleteLamp(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID cannot be null or empty");
        }

        try {
            lampRepository.deleteById(id);
            return new ServiceResponse("Lamp with ID " + id + " deleted successfully", HttpStatus.OK.value());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ID cannot be null or empty");

        }
    }
}
