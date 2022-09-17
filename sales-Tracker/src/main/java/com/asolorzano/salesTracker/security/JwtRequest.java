package com.asolorzano.salesTracker.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//3
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {

    private static final long serialVersionUID=1L;

    private String username;
    private String password;

}
