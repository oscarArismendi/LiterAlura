package com.literalura.liter_alura.view;

import org.springframework.context.ApplicationContext;

import com.literalura.liter_alura.gutendex.application.GutendexServiceImpl;
import com.literalura.liter_alura.gutendex.domain.dto.SearchResponse;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConsultBookMenu {

    private static ApplicationContext springContext;

    private final GutendexServiceImpl gutendexService;

    public static void setApplicationContext(ApplicationContext context) {
        springContext = context;
    }

    public ConsultBookMenu() {
        // Retrieve the Spring bean here
        gutendexService = springContext.getBean(GutendexServiceImpl.class);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Consult Book");

        Label label = new Label("Enter book title:");
        TextField textField = new TextField();
        Button searchButton = new Button("Search");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        searchButton.setOnAction(event -> {
            String bookTitle = textField.getText();
            if (bookTitle == null || bookTitle.isBlank()) {
                showAlert(Alert.AlertType.WARNING, "Input Required", "Please enter a valid book title.");
            } else {
                try {

                    SearchResponse response = gutendexService.searchBooks(bookTitle);
                    StringBuilder result = new StringBuilder();

                    response.results().stream()
                            .filter(book -> book.title().toLowerCase().contains(bookTitle.toLowerCase()))
                            .findFirst().ifPresent(book -> {
                                result.append("Title: ").append(book.title()).append("\n");
                                result.append("Authors:\n");
                                if (book.authors().isEmpty()) {
                                    result.append("  - No authors available.\n");
                                } else {
                                    book.authors().forEach(author -> {
                                        result.append("  - ").append(author.name())
                                                .append(" (Born: ")
                                                .append(author.birth_year() != null ? author.birth_year() : "Unknown")
                                                .append(", Died: ")
                                                .append(author.death_year() != null ? author.death_year() : "Unknown")
                                                .append(")\n");
                                    });
                                }
                                if (book.languages().isEmpty()) {
                                    result.append("  - No languages available.\n");
                                } else {
                                    result.append("Languages:\n");
                                    book.languages().forEach(language -> {
                                        result.append("  - ").append(language)
                                                .append("\n");
                                    });
                                }
                                result.append("Download Count: ").append(book.download_count());
                                result.append("\n");

                            });

                    resultArea.setText(result.length() > 0 ? result.toString() : "No results found for: " + bookTitle);
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve book information: " + e.getMessage());
                }
            }
        });

        VBox vbox = new VBox(10, label, textField, searchButton, resultArea);
        vbox.setStyle("-fx-padding: 10; -fx-alignment: center;");

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
