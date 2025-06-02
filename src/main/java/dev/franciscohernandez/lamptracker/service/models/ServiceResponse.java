package dev.franciscohernandez.lamptracker.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceResponse {
    private String message;
    private int status;
}