package com.literalura.liter_alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.literalura.liter_alura.view.ConsultBookMenu;
import com.literalura.liter_alura.view.MainMenu;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LiterAluraApplication  {


    public static void main(String[] args) {
        // Initialize Spring Context
        ApplicationContext context = SpringApplication.run(LiterAluraApplication.class, args);

        // Pass the context to ConsultBookMenu
        ConsultBookMenu.setApplicationContext(context);

        // Launch the JavaFX MainMenu
        MainMenu.launch(MainMenu.class, args);
    }

}
