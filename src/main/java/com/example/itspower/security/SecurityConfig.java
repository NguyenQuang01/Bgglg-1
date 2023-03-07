package com.example.itspower.security;

import com.example.itspower.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Ngăn chặn request từ một domain khác
        http.cors().and().csrf().disable() // Ngăn chặn request từ một domain khác
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/login").permitAll()// Cho phép tất cả mọi người truy cập vào địa chỉ này
                .antMatchers("/api/register").permitAll()// đăng ký tài khoản
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Thêm một lớp Filter kiểm tra user
        http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        /// Cung cấp userservice cho spring security
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
