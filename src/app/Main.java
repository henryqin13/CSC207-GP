package app;


import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import data_access.*;
import entity.CommonUserFactory;
import interface_adapter.Game.GameController;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.LoggedIn.LoggedInController;
import interface_adapter.LoggedIn.LoggedInViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Game.GameDataAccessInterface;
import use_case.GameSessionManagerUseCase;
import use_case.GameUseCase;
import use_case.Guest.GuestUserDataAccessInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.formdev.flatlaf.*;



public class Main {

    private static String apiKey; // Replace with your actual API key
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions"; // Adjust as needed

    public static void main(String[] args) throws IOException {

        // Load configuration and create API client
        ConfigLoader config = new ConfigLoader();
        String apiKey = config.getApiKey();
        System.out.println(apiKey);

    }
}