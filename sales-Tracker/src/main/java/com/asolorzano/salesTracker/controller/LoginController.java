package com.asolorzano.salesTracker.controller;


import com.asolorzano.salesTracker.security.JwtRequest;
import com.asolorzano.salesTracker.security.JwtResponse;
import com.asolorzano.salesTracker.security.JwtTokenUtil;
import com.asolorzano.salesTracker.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest req)throws Exception{
        //for current user verification
        authenticate(req.getUsername(), req.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return  ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception{
        try{
            //For token validation (params at the end)
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e)
        {
            throw  new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e)
        {
            throw  new Exception("INVALID_CREDENTIALS", e);
        }

    }
}