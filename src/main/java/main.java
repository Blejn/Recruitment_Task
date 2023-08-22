import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Business Trip Reimbursement Application");
        Button userButton = new Button("User View");
        Button adminButton = new Button("Administrator View");
        userButton.setOnAction(event -> openUserView(primaryStage));
        adminButton.setOnAction(event -> openAdminView());
        VBox root = new VBox(10);
        root.getChildren().addAll(userButton, adminButton);
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void openUserView(Stage primaryStage) {
        UserView userView = new UserView();  // Tworzenie instancji interfejsu użytkownika

        Scene userScene = new Scene(userView.getRoot(), 600, 400);  // Tworzenie sceny z korzeniem interfejsu
        primaryStage.setScene(userScene);  // Ustawienie sceny na primaryStage i pokazanie jej
    }

    private void openAdminView() {
        // Tutaj otwórz widok administratora (np. za pomocą FXMLLoader w JavaFX)
    }
}
