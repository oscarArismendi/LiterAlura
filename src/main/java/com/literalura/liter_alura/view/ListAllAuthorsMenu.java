package com.literalura.liter_alura.view;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.literalura.liter_alura.authors.application.AuthorServiceImpl;
import com.literalura.liter_alura.authors.domain.entity.Author;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListAllAuthorsMenu {

    private static ApplicationContext springContext;

    private final AuthorServiceImpl authorServiceImpl;

    public static void setApplicationContext(ApplicationContext context) {
        springContext = context;
    }

    public ListAllAuthorsMenu() {
        // Retrieve the Spring bean here
        authorServiceImpl = springContext.getBean(AuthorServiceImpl.class);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("List of all authors of searched books");
        Label label = new Label("List of authors:");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(event -> stage.close());
        try {

            List<Author> response = authorServiceImpl.getAll();
            StringBuilder result = new StringBuilder();
            if (response != null) {

                response.stream()
                        .forEach(author -> {
                            result.append("  - ").append(author.getName())
                                    .append(" (Born: ")
                                    .append(author.getBirthYear() != null ? author.getBirthYear() : "Unknown")
                                    .append(", Died: ")
                                    .append(author.getDeathYear() != null ? author.getDeathYear() : "Unknown")
                                    .append(")\n");
                        });
            }

            resultArea.setText(result.length() > 0 ? result.toString() : "No authors found in the data base ");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve book information: " + e.getMessage());
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
