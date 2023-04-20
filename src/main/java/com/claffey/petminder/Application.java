package com.claffey.petminder;

import com.claffey.petminder.model.entity.RoleEntity;
import com.claffey.petminder.model.entity.UserEntity;
import com.claffey.petminder.service.RoleService;
import com.claffey.petminder.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService) {
		return args -> {
//			roleService.save(new RoleEntity(null, "ROLE_USER"));
//			roleService.save(new RoleEntity(null, "ROLE_ADMIN"));

//			userService.addRoleToUser("rossi", "ROLE_USER");
//			userService.addRoleToUser("bianchi", "ROLE_ADMIN");
//			userService.addRoleToUser("bianchi", "ROLE_USER");
		};
	}

}
