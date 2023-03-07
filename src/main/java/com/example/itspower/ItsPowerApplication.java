package com.example.itspower;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ItsPowerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItsPowerApplication.class, args);
    }
//    @Bean
//    CommandLineRunner run(UserService userService){
//        return args -> {
//           userService.saveRole(new RolesEntity(null,"ROLE_USER"));
//           userService.saveRole(new RolesEntity(null,"ROLE_MANAGER"));
//           userService.saveRole(new RolesEntity(null,"ROLE_ADMIN"));
//           userService.saveRole(new RolesEntity(null,"ROLE_SUPPER_ADMIN"));
//
//           userService.saveUser(new UserEntity(null,"dainv","dai223","dai223", new ArrayList<>()));
//            userService.saveUser(new UserEntity(null,"chinhvq","chinhvq","chinhvq", new ArrayList<>()));
//
//           userService.addRoleToUser("dai223","ROLE_USER");
//          userService.addRoleToUser("chinhvq","ROLE_MANAGER");
//        };
//    }
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
