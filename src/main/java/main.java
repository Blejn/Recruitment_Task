import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class main extends Application {
    private Stage primaryStage;
    private UserView userView;
    private AdminView adminView;
    private VBox mainMenu;
    private Administrator administrator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        administrator = new Administrator("admin", 15, 0.3);

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Expense Reimbursement Application");

        userView = new UserView(this, administrator);
        adminView = new AdminView(this, administrator);
        mainMenu = createMainMenu();

        Scene scene = new Scene(mainMenu, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createMainMenu() {
        Button userButton = new Button("User View");
        Button adminButton = new Button("Administrator View");

        userButton.setOnAction(event -> openUserView());
        adminButton.setOnAction(event -> openAdminView());

        VBox menu = new VBox(10);
        menu.getChildren().addAll(userButton, adminButton);
        return menu;
    }

    private void openUserView() {
        VBox userRoot = new UserView(this, administrator).getRoot();
        openView(userRoot);
    }

    private void openAdminView() {
        VBox adminRoot = new AdminView(this, administrator).getRoot();
        openView(adminRoot);
    }

    private void openView(Pane view) {
        Scene scene = new Scene(view, 1000, 800);
        primaryStage.setScene(scene);
    }

    public void backToMainScene(Administrator updatedAdministrator) {
        administrator = updatedAdministrator;
        primaryStage.setScene(mainMenu.getScene());
    }
}