import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.util.converter.NumberStringConverter;

public class AdminView {
    private VBox root;
    private Administrator administrator;

    public AdminView(main main,Administrator administrator) {
        this.administrator = administrator;
        Label titleLabel = new Label("Administrator View");

        Label dailyAllowanceLabel = new Label("Daily Allowance Rate ($):");
        TextField dailyAllowanceField = new TextField();
        dailyAllowanceField.textProperty().bindBidirectional(administrator.dietCostPerDayProperty(), new NumberStringConverter());

        Label mileageRateLabel = new Label("Car Mileage Rate ($/km):");
        TextField mileageRateField = new TextField();
        mileageRateField.textProperty().bindBidirectional(administrator.distanceCostProperty(), new NumberStringConverter());

        Button acceptButton = new Button("Accept");
        acceptButton.setOnAction(event -> main.backToMainScene(administrator));

        Button backButton = new Button("Back to Main");
        backButton.setOnAction(event -> main.backToMainScene(administrator));

        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.setPadding(new Insets(10));
        formLayout.addRow(0, dailyAllowanceLabel, dailyAllowanceField);
        formLayout.addRow(1, mileageRateLabel, mileageRateField);
        formLayout.addRow(2, acceptButton);
        formLayout.addRow(5, backButton);

        root = new VBox(20);
        root.getChildren().addAll(titleLabel, formLayout);
    }

    public VBox getRoot() {
        return root;
    }
}
