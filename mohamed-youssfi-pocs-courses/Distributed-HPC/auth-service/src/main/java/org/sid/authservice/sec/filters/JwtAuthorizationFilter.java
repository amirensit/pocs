package org.sid.authservice.sec.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.sid.authservice.sec.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/refreshToken")) { // in case of refresh token, this filter should not be used !
            filterChain.doFilter(request, response);
        } else {
            String authorizationToken = request.getHeader(JwtUtil.AUTHORIZATION_HEADER);
            if (authorizationToken != null && authorizationToken.startsWith(JwtUtil.PREFIX)) {
                try {
                    String jwt = authorizationToken.substring(JwtUtil.PREFIX.length());
                    Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    Arrays.stream(roles)
                            .map(authority -> authorities.add(new SimpleGrantedAuthority(authority))
                            );
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = // we don't need password here
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    response.setHeader("errorMsg", e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } else { // no need to treat that request
                filterChain.doFilter(request, response);
            }
        }
    }
}
