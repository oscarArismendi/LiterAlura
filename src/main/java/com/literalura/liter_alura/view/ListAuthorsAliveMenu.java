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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListAuthorsAliveMenu {
private static ApplicationContext springContext;

    private final AuthorServiceImpl authorServiceImpl;

    public static void setApplicationContext(ApplicationContext context) {
        springContext = context;
    }

    public ListAuthorsAliveMenu() {
        // Retrieve the Spring bean here
        authorServiceImpl = springContext.getBean(AuthorServiceImpl.class);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("List authors alive in the database in specific year");
        Label label = new Label("Enter year:");
        TextField textField = new TextField();
        Button searchButton = new Button("Search");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(event -> stage.close());

        searchButton.setOnAction(event -> {
            String yearInput = textField.getText();
            if (yearInput == null || yearInput.isBlank()) {
                showAlert(Alert.AlertType.WARNING, "Input Required", "Please enter a valid year.");
            } else{

                try {
                    int year = Integer.parseInt(yearInput);
                    List<Author> response = authorServiceImpl.getAuthorsAliveInYear(year);
                    StringBuilder result = new StringBuilder();
                    if(response != null){
        
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
                }catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve author information: " + e.getMessage());
                }
            }
        });

        VBox vbox = new VBox(10,label,textField,searchButton, resultArea,goBackButton);
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
