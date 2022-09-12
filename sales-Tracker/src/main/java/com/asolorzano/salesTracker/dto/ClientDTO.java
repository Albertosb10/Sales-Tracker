package com.asolorzano.salesTracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {


    private Integer idClient;
    private String firstName;
    private String lastName;
    private String cardId;
    private String phoneNumber;
    private String email;
    private String address;
}
