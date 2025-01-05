package com.literalura.liter_alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.literalura.liter_alura.view.ConsultBookMenu;
import com.literalura.liter_alura.view.CountBooksByLanguageMenu;
import com.literalura.liter_alura.view.ListAllAuthorsMenu;
import com.literalura.liter_alura.view.ListAllBooksMenu;
import com.literalura.liter_alura.view.ListAuthorsAliveMenu;
import com.literalura.liter_alura.view.MainMenu;

@SpringBootApplication
public class LiterAluraApplication  {


    public static void main(String[] args) {
        // Initialize Spring Context
        ApplicationContext context = SpringApplication.run(LiterAluraApplication.class, args);

        // Pass the context to UI
        ConsultBookMenu.setApplicationContext(context);
        ListAllAuthorsMenu.setApplicationContext(context);
        ListAllBooksMenu.setApplicationContext(context);
        ListAuthorsAliveMenu.setApplicationContext(context);
        CountBooksByLanguageMenu.setApplicationContext(context);
        // Launch the JavaFX MainMenu
        MainMenu.launch(MainMenu.class, args);
    }

}
