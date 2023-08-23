import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class main extends Application {
    private Stage primaryStage;
    private UserView userView;
    private AdminView adminView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Expense Reimbursement Application");

        userView = new UserView(this);
        adminView = new AdminView(this);

        Button userButton = new Button("User View");
        Button adminButton = new Button("Administrator View");

        userButton.setOnAction(event -> openView(userView.getRoot()));
        adminButton.setOnAction(event -> openView(adminView.getRoot()));

        VBox root = new VBox(10);
        root.getChildren().addAll(userButton, adminButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openView(Pane view) {
        Scene scene = new Scene(view, 1000, 800);
        primaryStage.setScene(scene);
    }

    public void backToMainScene() {
        openView(new VBox(10));
    }
}