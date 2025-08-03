package com.sachin.blog.config;

import com.sachin.blog.model.Category;
import com.sachin.blog.model.Role;
import com.sachin.blog.repository.CategoryRepository;
import com.sachin.blog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create roles if they don't exist
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        // Create a default category if none exist
        if (categoryRepository.count() == 0) {
            Category defaultCategory = new Category();
            defaultCategory.setName("General");
            categoryRepository.save(defaultCategory);
        }
    }
}
