package com.example.itspower.filter;

import com.example.itspower.service.impl.UserLoginConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtToken token;

    @Autowired
    private UserLoginConfig userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //kiem tra duong dan dang nhap
        try {
            // Lấy jwt từ request
            String authenticationHeader = getJwtFromRequest(request);
            if (StringUtils.hasText(authenticationHeader) && token.validateToken(authenticationHeader)) {
                // Lấy userLogin từ chuỗi jwt
                String userLogin = token.getUserNameFromJWT(authenticationHeader);
                // Lấy thông tin người dùng qua userLogin
                UserDetails userDetails = userService.loadUserByUsername(userLogin);
                if (userDetails != null) {
                    // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
                    UsernamePasswordAuthenticationToken
                            usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (Exception e) {
            log.error("Error logging in : {} " + e.getMessage());
            response.setHeader("erorr", e.getMessage());
            response.setStatus(404);
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
