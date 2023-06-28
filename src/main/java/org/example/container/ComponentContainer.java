package org.example.container;

import org.example.service.ProfileService;

import java.util.Scanner;

public class ComponentContainer {
    public static Scanner stringScanner = new Scanner(System.in);
    public static Scanner intScanner = new Scanner(System.in);
    public static ProfileService profileService = new ProfileService();
}
