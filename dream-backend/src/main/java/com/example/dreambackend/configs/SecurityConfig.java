package com.example.dreambackend.configs;


import com.example.dreambackend.services.nhanvien.NhanVienService;
import com.example.dreambackend.services.phanquyen.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Bật tính năng Method Security
public class SecurityConfig  {

//    // Cấu hình AuthenticationManager (nếu cần thiết)
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("admin")
//                        .password(passwordEncoder().encode("admin123"))
//                        .roles("ADMIN")
//                        .build(),
//                User.withUsername("user")
//                        .password(passwordEncoder().encode("user123"))
//                        .roles("USER")
//                        .build()
//        );
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Cấu hình mã hóa mật khẩu
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
//                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/auth/login").permitAll()
                                .requestMatchers("/api/khuyenmai/**").permitAll()
                                // Tài khoản ADMIN có quyền truy cập tất cả các API
                                .requestMatchers("/api/**").hasRole("QuanLy")
                                // Tài khoản USER không thể truy cập các API đặc biệt
                                .requestMatchers("/api/thong-ke/**", "/api/nhan-vien/**").hasRole("ADMIN")
                                // Tất cả các API khác chỉ yêu cầu người dùng có quyền USER

                                .anyRequest().authenticated() // Mọi yêu cầu khác phải xác thực
                )
                .formLogin(Customizer.withDefaults()); // Cấu hình form login mặc định
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//
//        // Cấu hình AuthenticationManager
//        authenticationManagerBuilder.userDetailsService(userDetailsService());
//
//        return authenticationManagerBuilder.build();  // Trả về AuthenticationManager
//    }
}
