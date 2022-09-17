package com.asolorzano.salesTracker.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//5
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        final String requestTokenHeader = request.getHeader("Authorization");

        String username=null;
        String jwtToken=null;

        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            //a partir de la poscicion 7
            jwtToken = requestTokenHeader.substring(7);
            try{
                username = jwtTokenUtil.getUserNameFromToken(jwtToken);
            }catch(IllegalArgumentException e){
                System.out.println("No se puede encontrar el token JWT");
            }catch (ExpiredJwtException e)
            {
                System.out.println("Token Jwt ha expirado");
            }
        }else{
            logger.warn("JWT Token no inicia con la palabra Bearer");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            if(jwtTokenUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
