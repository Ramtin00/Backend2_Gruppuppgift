package com.example.springboot_grupparbete.Controllers;

import com.example.springboot_grupparbete.Models.AuthenticationResponse;
import com.example.springboot_grupparbete.Models.User;
import com.example.springboot_grupparbete.Services.MyUserDetailsService;
import com.example.springboot_grupparbete.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping(value = "/login/authenticate", method = RequestMethod.POST)
    //@ModelAttribute istället för @RequestBody gör att man slipper skriva credentials i JSON format i Postman
    public ResponseEntity<?> createAuthenticationToken(@ModelAttribute User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}