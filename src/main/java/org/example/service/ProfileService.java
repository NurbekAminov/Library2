package org.example.service;

import org.example.Controller.AdminController;
import org.example.Controller.UserController;
import org.example.Util.PhoneValidationUtil;
import org.example.dto.ProfileDto;
import org.example.entity.ProfileEntity;
import org.example.enums.Role;
import org.example.enums.StatusProfile;
import org.example.mapper.StudentByBookMapper;
import org.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ProfileService {
    @Autowired
    public ProfileRepository profileRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private AdminController adminController;
    @Autowired
    private Scanner scanner;
    public void register(ProfileEntity profile){
        String phone = profile.getPhone();

        if (!PhoneValidationUtil.isValidPhone(phone)) {
            return;
        }

        ProfileEntity profileEntity = profileRepository.getProfileByPhone(profile.getPhone());
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
        ProfileDto profileDto = profileRepository.login(login);
        if (profileDto == null) {
            System.out.println("login or password is incorrect!");
            return;

        } else if (profileDto.getStatusProfile().equals(StatusProfile.BLOCK)) {
            System.out.println("your account is blocked!");
            return;
        }
        if (!password.equals(profileDto.getPassword())){
            System.out.println("Wrong password");
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
    public void getStudentList() {
        List<Object[]> objectList = profileRepository.getStudentList();
        if (objectList.size() == 0){
            System.out.println("Profile list is empty");
            return;
        }
        List<ProfileDto> profileDTOList = new ArrayList<>();
        objectList.forEach((objects) ->{
            ProfileDto profileDTO = new ProfileDto((Integer) objects[0], (String) objects[1], (String) objects[2], (String) objects[3], (String) objects[4], (String) objects[5], (StatusProfile) objects[6], (Role) objects[7], (LocalDateTime) objects[8]);
            profileDTOList.add(profileDTO);
        });

        profileDTOList.forEach(System.out::println);
    }

    public void searchStudent() {
        System.out.print("ENTER STUDENT id or name or surname or login or phone -> ");
        String value = scanner.next();
        ProfileEntity profileEntity;
        try {
            System.out.println("value -> ");
            int id = Integer.parseInt(value);
            System.out.println(id);
            profileEntity = profileRepository.getProfileById(id);
        }catch (NumberFormatException e){
            profileEntity = profileRepository.searchStudent(value);
        }
        if (profileEntity == null){
            System.out.println("Student not found");
            return;
        }
        System.out.println(profileEntity);
    }

    public void blockingStudent() {
        System.out.print("ENTER STUDENT ID -> ");
        int id = scanner.nextInt();

        ProfileEntity profileEntity = profileRepository.getProfileById(id);
        if (profileEntity == null){
            System.out.println("Student not found");
            return;
        }
        boolean result = profileRepository.blockingStudent(id);
        if (result){
            System.out.println("Student blocked");
        }else {
            System.out.println("Student not blocked");
        }
    }

    public void activateStudent() {
        System.out.print("ENTER STUDENT ID -> ");
        int id = scanner.nextInt();

        ProfileEntity profileEntity = profileRepository.getProfileById(id);
        if (profileEntity == null){
            System.out.println("Student not found");
            return;
        }
        boolean result = profileRepository.activateStudent(id);
        if (result){
            System.out.println("Student activated");
        }else {
            System.out.println("Student not activated");
        }
    }

    public void studentByBook() {
        System.out.print("ENTER STUDENT ID -> ");
        int id = scanner.nextInt();

        ProfileEntity profileEntity = profileRepository.getProfileById(id);
        if (profileEntity == null){
            System.out.println("Student not found");
            return;
        }

        List<Object[]> profileEntityList = profileRepository.studentByBook(id);
        if (profileEntityList.size() == 0){
            System.out.println("Profile list is empty");
            return;
        }
        List<StudentByBookMapper> studentByBookMapperList = new ArrayList<>();
        profileEntityList.forEach((objects) ->{
            StudentByBookMapper studentByBookMapper = new StudentByBookMapper((Integer) objects[0], (String) objects[1], (String) objects[2], (String) objects[3], (String) objects[4], (String) objects[5], (Long) objects[6], (Long) objects[7]);
            studentByBookMapperList.add(studentByBookMapper);
        });

        studentByBookMapperList.forEach(System.out::println);
    }

    public void getAllProfileList() {
        List<Object[]> objectList = profileRepository.getProfileList();
        if (objectList.size() == 0){
            System.out.println("Profile list is empty");
            return;
        }
        List<ProfileDto> profileDTOList = new ArrayList<>();
        objectList.forEach((objects) ->{
            ProfileDto profileDTO = new ProfileDto((Integer) objects[0], (String) objects[1], (String) objects[2], (String) objects[3], (String) objects[4], (String) objects[5], (StatusProfile) objects[6], (Role) objects[7], (LocalDateTime) objects[8]);
            profileDTOList.add(profileDTO);
        });

        profileDTOList.forEach(System.out::println);
    }

    public void searchProfile() {
        System.out.print("ENTER PROFILE id or name or surname or login or phone -> ");
        String value = scanner.next();
        ProfileEntity profileEntity;
        try {
            int id = Integer.parseInt(value);
            profileEntity = profileRepository.getProfileById(id);
        }catch (NumberFormatException e){
            profileEntity = profileRepository.searchProfile(value);
        }
        if (profileEntity == null){
            System.out.println("Profile not found");
            return;
        }
        System.out.println(profileEntity);
    }

    public void changeStatus() {
        System.out.print("ENTER PROFILE ID -> ");
        int id = scanner.nextInt();

        ProfileEntity profileEntity = profileRepository.getProfileById(id);
        if (profileEntity == null){
            System.out.println("Profile not found");
            return;
        }

        String value = "BLOCK";
        StatusProfile status = StatusProfile.BLOCK;
        if (profileEntity.getStatusProfile().equals(StatusProfile.BLOCK)) {
            status = StatusProfile.ACTIVE;
            value = "ACTIVE";
        }

        boolean result = profileRepository.changeStatus(id,status);
        if (result){
            System.out.println("Profile status changed to"+value);
        }else {
            System.out.println("Profile status has not changed");
        }

    }
}
