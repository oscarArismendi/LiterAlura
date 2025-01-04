package com.literalura.liter_alura.view;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.literalura.liter_alura.books.application.BookServiceImpl;
import com.literalura.liter_alura.books.domain.entity.Book;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListAllBooksMenu {
    private static ApplicationContext springContext;

    private final BookServiceImpl bookServiceImpl;

    public static void setApplicationContext(ApplicationContext context) {
        springContext = context;
    }

    public ListAllBooksMenu() {
        // Retrieve the Spring bean here
        bookServiceImpl = springContext.getBean(BookServiceImpl.class);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("List of all books searched");
        Label label = new Label("List of books:");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(event -> stage.close());
        try {

            List<Book> response = bookServiceImpl.getAll();
            StringBuilder result = new StringBuilder();
            if(response != null){

                response.stream()
                        .forEach(book -> {
                            result.append("Title: ").append(book.getTitle()).append("\n");
                            result.append("Authors:\n");
                            if (book.getAuthors().isEmpty()) {
                                result.append("  - No authors available.\n");
                            } else {
                                book.getAuthors().forEach(author -> {
                                    result.append("  - ").append(author.getName())
                                            .append(" (Born: ")
                                            .append(author.getBirthYear() != null ? author.getBirthYear() : "Unknown")
                                            .append(", Died: ")
                                            .append(author.getDeathYear() != null ? author.getDeathYear() : "Unknown")
                                            .append(")\n");
                                });
                            }
                            if (book.getLanguages().isEmpty()) {
                                result.append("  - No languages available.\n");
                            } else {
                                result.append("Languages:\n");
                                book.getLanguages().forEach(language -> {
                                    result.append("  - ").append(language)
                                            .append("\n");
                                });
                            }
                            result.append("Download Count: ").append(book.getDownload_count());
                            result.append("\n");
                            result.append("----------------------------------------");
                            result.append("\n");



                        });
            }

            resultArea.setText(result.length() > 0 ? result.toString() : "No books found in the database ");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve book information: " + e.getMessage());
            return;
        }

        VBox vbox = new VBox(10,label, resultArea,goBackButton);
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
