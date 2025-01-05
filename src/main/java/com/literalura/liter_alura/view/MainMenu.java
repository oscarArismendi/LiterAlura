package com.literalura.liter_alura.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
                "List All Searched Books",
                "List All Authors Of Searched Books",
                "List Authors Alive in Specific Year",
                "List Stats Of Searched Books by Language",
                "Top 10 Downloaded Books"
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
            case "List All Searched Books" -> listDatabaseBooksMenu();
            case "List All Authors Of Searched Books" -> consultAuthor();
            case "List Stats Of Searched Books by Language" -> listBooksByLanguage();
            case "List Authors Alive in Specific Year" -> listAuthorsByYear();
            case "Top 10 Downloaded Books" -> top10Books();
            default -> showAlert(Alert.AlertType.ERROR, "Error", "Invalid choice!");
        }
    }

    private void openConsultBookMenu() {
        ConsultBookMenu consultBookMenu = new ConsultBookMenu();
        consultBookMenu.show();
    }

    private void listDatabaseBooksMenu(){
        ListAllBooksMenu listAllBooksMenu = new ListAllBooksMenu();
        listAllBooksMenu.show();
    }

    private void consultAuthor() {
        ListAllAuthorsMenu listAllAuthorsMenu = new ListAllAuthorsMenu();
        listAllAuthorsMenu.show();
    }

    private void listBooksByLanguage() {
        CountBooksByLanguageMenu countBooksByLanguageMenu = new CountBooksByLanguageMenu();
        countBooksByLanguageMenu.show();
    }

    private void listAuthorsByYear() {
        ListAuthorsAliveMenu listAuthorsAliveMenu = new ListAuthorsAliveMenu();
        listAuthorsAliveMenu.show();
    }

    private void top10Books() {
        Top10DownloadBooksMenu top10DownloadBooksMenu = new Top10DownloadBooksMenu();
        top10DownloadBooksMenu.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
