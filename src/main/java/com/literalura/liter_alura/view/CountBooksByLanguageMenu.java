package com.literalura.liter_alura.view;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.literalura.liter_alura.books.application.BookServiceImpl;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CountBooksByLanguageMenu {

    private static ApplicationContext springContext;

    private final BookServiceImpl bookServiceImpl;

    public static void setApplicationContext(ApplicationContext context) {
        springContext = context;
    }

    public CountBooksByLanguageMenu() {
        // Retrieve the Spring bean here
        bookServiceImpl = springContext.getBean(BookServiceImpl.class);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Count of Books by Language");

        Label label = new Label("Books Count by Language:");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(event -> stage.close());

        try {
            Map<String, Long> languageCounts = bookServiceImpl.getBooksCountByLanguage();
            StringBuilder result = new StringBuilder();

            if (!languageCounts.isEmpty()) {
                languageCounts.forEach((language, count) -> {
                    result.append("Language: ").append(language)
                            .append(" - Count: ").append(count).append("\n");
                });
            }

            resultArea.setText(result.length() > 0 ? result.toString() : "No books found for any language in the database.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve book count information: " + e.getMessage());
            return;
        }

        VBox vbox = new VBox(10, label, resultArea, goBackButton);
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
