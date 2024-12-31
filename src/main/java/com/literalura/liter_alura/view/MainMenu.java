package com.literalura.liter_alura.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        
        primaryStage.setTitle("Main Menu");

        Label label = new Label("Select an option:");

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(
                "Consult Book for title",
                "List All Authors Of Searched Books",
                "List Searched Books by Language",
                "List Authors Alive in Specific Year",
                "Top 10 Downloaded Books",
                "Search Author by Name",
                "Search Author by Birthdate"
        );

        Button selectButton = new Button("Select");

        selectButton.setOnAction(event -> {
            String selectedOption = listView.getSelectionModel().getSelectedItem();
            if (selectedOption == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select an option!");
            } else {
                handleChoice(selectedOption);
            }
        });

        VBox vbox = new VBox(10, label, listView, selectButton);
        vbox.setSpacing(15);
        vbox.setStyle("-fx-padding: 10; -fx-alignment: center;");

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleChoice(String choice) {
        switch (choice) {
            case "Consult Book for title" -> openConsultBookMenu();
            case "List All Authors Of Searched Books" -> consultAuthor();
            case "List Searched Books by Language" -> listBooksByLanguage();
            case "List Authors Alive in Specific Year" -> listAuthorsByYear();
            case "Top 10 Downloaded Books" -> top10Books();
            case "Search Author by Name" -> searchAuthorByName();
            case "Search Author by Birthdate" -> searchAuthorByBirthdate();
            default -> showAlert(Alert.AlertType.ERROR, "Error", "Invalid choice!");
        }
    }

    private void openConsultBookMenu() {
        ConsultBookMenu consultBookMenu = new ConsultBookMenu();
        consultBookMenu.show();
    }

    private void consultAuthor() {
        ListAllAuthorsMenu listAllAuthorsMenu = new ListAllAuthorsMenu();
        listAllAuthorsMenu.show();
    }

    private void listBooksByLanguage() {
        String language = showTextInputDialog("List Books", "Enter language:");
        if (language != null) {
            showAlert(Alert.AlertType.INFORMATION, "List Books", "Listing books in: " + language);
            // Add backend integration here
        }
    }

    private void listAuthorsByYear() {
        String year = showTextInputDialog("List Authors", "Enter year:");
        if (year != null) {
            showAlert(Alert.AlertType.INFORMATION, "List Authors", "Listing authors alive in: " + year);
            // Add backend integration here
        }
    }

    private void top10Books() {
        showAlert(Alert.AlertType.INFORMATION, "Top 10 Books", "Displaying top 10 downloaded books.");
        // Add backend integration here
    }

    private void searchAuthorByName() {
        String name = showTextInputDialog("Search Author", "Enter author name:");
        if (name != null) {
            showAlert(Alert.AlertType.INFORMATION, "Search Author", "Searching author: " + name);
            // Add backend integration here
        }
    }

    private void searchAuthorByBirthdate() {
        String birthdate = showTextInputDialog("Search Author", "Enter birthdate (YYYY-MM-DD):");
        if (birthdate != null) {
            showAlert(Alert.AlertType.INFORMATION, "Search Author", "Searching author born on: " + birthdate);
            // Add backend integration here
        }
    }

    private String showTextInputDialog(String title, String content) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(content);
        return dialog.showAndWait().orElse(null);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
