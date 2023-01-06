package mk.ukim.finki.wpaud.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.wpaud.config.JwtAuthConstants;
import mk.ukim.finki.wpaud.model.dto.UserDetailsDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager
                                          authenticationManager) {
        super(authenticationManager);


    }


    //cekame baranje da izvlecem headerot so auth. inoforamcija i vrz baza na toa da izvleceme informacii
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(JwtAuthConstants.HEADER_STRING);
        if (header == null || !header.startsWith(JwtAuthConstants.TOKEN_PREFIX)) {
            //ne e najaven korisnikot
            chain.doFilter(request, response); //ostanatite filtri da si se izvrsuvaat
            return;
        }
        //treba da gi proverime informacii, da napravime dekripcija
        String user = JWT.require(Algorithm.HMAC256(JwtAuthConstants.SECRET.getBytes()))
                .build()
                .verify(header.replace(JwtAuthConstants.TOKEN_PREFIX, ""))//go trgame toa Bearer so prazen string
                .getSubject();//zimame subject-> toj e json objekt
        if (user == null) {
            return;
        }
        //od user, vo userdetailsdto prefrlame
        UserDetailsDto userDetailsDto = new ObjectMapper().readValue(user, UserDetailsDto.class);
        //token koj e biten za spring security, za da go vovedeme korisnikot vo springsecurity kontekstot
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
                (userDetailsDto.getUsername(), "", Collections.singleton(userDetailsDto.getRole()));
        SecurityContextHolder.getContext().setAuthentication(token);
        //si porodolzuva chainot od filtri
        chain.doFilter(request, response);

    }
}
