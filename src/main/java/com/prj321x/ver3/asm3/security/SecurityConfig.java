package com.prj321x.ver3.asm3.security;

import com.prj321x.ver3.asm3.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    //kết nối bằng JPA/hibernate để lấy xác thực tài khoản đăng nhập
    //sử dung mã hóa mật khẩu Bcypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(AccountService accountService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        System.out.println("từ security DaoAuthentication " + auth);
        auth.setUserDetailsService(accountService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        System.out.println("Done security DaoAuthentication " + auth);
        return auth;
    }


    //trang đăng nhập tùy chỉnh
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET,"/accounts","/accounts/**",
                                        "/api-account/**","/search/**","/api-dangkykhambenh/**").hasAnyRole("ADMIN", "BACSI","BENHNHAN")
                                .requestMatchers(HttpMethod.POST, "/api-account/**").hasAnyRole("BENHNHAN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api-bacsi/**").hasRole("BACSI")
                                .requestMatchers(HttpMethod.PUT, "/api-bacsi/**","/dangKyKhamBenhs").hasRole("BACSI")
                                .requestMatchers(HttpMethod.POST,"/api-bacsi/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/accounts","/accounts/**","/api-account/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                .anyRequest().authenticated() //yêu cầu người dùng phải đăng nhập
                )
        ;
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }
}
