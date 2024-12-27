package com.literalura.liter_alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.literalura.liter_alura.gutendex.domain.services.GutendexService;
import com.literalura.liter_alura.view.MainMenu;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LiterAluraApplication  {

    private final GutendexService gutendexService;

    public LiterAluraApplication(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
        // Launch the JavaFX MainMenu
        MainMenu.launch(MainMenu.class, args);
    }

    // @Override
    // public void run(String... args) {
    //     SearchResponse response = gutendexService.searchBooks("science");

    //     response.results().forEach(book -> {
    //         System.out.println("Title: " + book.title());

    //         // Print authors
    //         System.out.print("Authors: ");
    //         if (book.authors().isEmpty()) {
    //             System.out.println("No authors available.");
    //         } else {
    //             book.authors().forEach(author -> {
    //                 System.out.println(" - " + author.name() +
    //                         " (Born: " + (author.birth_year() != null ? author.birth_year() : "Unknown") +
    //                         ", Died: " + (author.death_year() != null ? author.death_year() : "Unknown") + ")");
    //             });
    //         }

    //         System.out.println("-----------------------");
    //     });
    // }
}
