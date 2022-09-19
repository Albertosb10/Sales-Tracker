package com.asolorzano.salesTracker.security;


import java.io.Serializable;


//4
//generation of the code in the response
public class JwtResponse implements Serializable {

    private static final long serialVersionUID=1L;

    private final String jwttoken;

    public String getJwttoken()
    {
        return jwttoken;
    }

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
