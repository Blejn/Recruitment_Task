import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class AdminView {
    private VBox root;

    public AdminView(main main) {
        Label titleLabel = new Label("Administrator View");

        Label dailyAllowanceLabel = new Label("Daily Allowance Rate ($):");
        TextField dailyAllowanceField = new TextField("15");

        Label mileageRateLabel = new Label("Car Mileage Rate ($/km):");
        TextField mileageRateField = new TextField("0.3");



        Button backButton = new Button("Back to Main");
        backButton.setOnAction(event -> main.backToMainScene());

        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.setPadding(new Insets(10));
        formLayout.addRow(0, dailyAllowanceLabel, dailyAllowanceField);
        formLayout.addRow(1, mileageRateLabel, mileageRateField);
        // Dodaj pozostałe komponenty UI do GridPane

        formLayout.addRow(5, backButton);

        root = new VBox(20);
        root.getChildren().addAll(titleLabel, formLayout);
    }

    public VBox getRoot() {
        return root;
    }
}
