package com.example.invertech.config;

import com.example.invertech.token.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
//se activa cada que el usuario envie una request (OncePerRequestFilter)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //validate the token
    private final JwtService jwtService;
    //charge info from user from a database
    private final UserDetailsService userDetailsService;
    //a token it's save in TokenRepository and it's used to know a user can access to a source
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains("/api/v1/auth")) { //solo lo que contenga esa ruta se hace autenticacion
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization"); //Authorization es el nombre del header
        final String jwt; //aqui se va a guardar los datos del token
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) { //Bearrer es el tipo de authentication
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);//despues de extraer los datos del token
        userEmail = jwtService.extractUsername(jwt);//se extrae el username(correo)
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //validar usuario en database, ApplicationConfig valida email y no usuario
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
