package org.example.service;

import org.example.Controller.AdminController;
import org.example.Controller.UserController;
import org.example.Util.PhoneValidationUtil;
import org.example.dto.ProfileDto;
import org.example.entity.ProfileEntity;
import org.example.enums.Role;
import org.example.enums.StatusProfile;
import org.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class ProfileService {
    @Autowired
    public ProfileRepository profileRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private AdminController adminController;
    public void register(ProfileDto profile){
        String phone = profile.getPhone();

        if (!PhoneValidationUtil.isValidPhone(phone)) {
            return;
        }

        List<ProfileEntity> profileEntity = profileRepository.getProfileByPhone(profile.getPhone());
        if (profileEntity != null) {
            System.out.println("This phone is already registered");
            return;
        }
        profile.setCreatedDate(LocalDateTime.now());
        profile.setStatusProfile(StatusProfile.ACTIVE);
        profile.setRole(Role.STUDENT);

        boolean result = profileRepository.addProfile(profile);
        if (result) {
            System.out.println("you have successfully registered");
            System.out.println("Login to your account!");
        } else {
            System.out.println("Something went wrong");
        }
    }
    public void login(String login, String password) {
        ProfileDto profileDto = profileRepository.login(login, password);
        if (profileDto == null) {
            System.out.println("login or password is incorrect!");
            return;

        }else if (profileDto.getStatusProfile().equals(StatusProfile.BLOCK)) {
            System.out.println("your account is blocked!");
            return;
        }
        System.out.println("you have logged in successfully!");
        if (profileDto.getRole().equals(Role.STUDENT)) {
            userController.start();
            System.out.println("Welcome user");
        } else if (profileDto.getRole().equals(Role.ADMIN)){
            adminController.startAdmin();
            System.out.println("Welcome admin");
        }else {
            adminController.startStaff();
            System.out.println("Welcome staff");
        }
    }
}
