
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.util.converter.IntegerStringConverter;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserView {
    private VBox root;
    private HBox receiptsContainer;
    private DatePicker datePickerStart;
    private DatePicker datePickerEnd;
    private TextField daysField;
    private List<Receipt> receiptsList;
    private Label receiptInfoLabel;
    private VBox receiptBox;
    private VBox daysDietBox;
    private Administrator administrator;


    public UserView(main main, Administrator administrator) {
        receiptsList = new ArrayList<>();
        receiptBox = new VBox(10);
        daysDietBox = new VBox();
        receiptsContainer = new HBox();
        this.administrator = administrator;

        addReceipt();
        Label titleLabel = new Label("Create Reimbursement Claim");


        Label dateStartLabel = new Label("Start Trip Date:");
        datePickerStart = new DatePicker();
        Label dateEndLabel = new Label("End Trip Date:");
        datePickerEnd = new DatePicker();

        datePickerStart.setOnAction(event -> updateDaysField());
        datePickerEnd.setOnAction(event -> updateDaysField());






        Label daysLabel = new Label("Number of Days:");
        daysField = new TextField();
        daysField.setEditable(false);

        Label disableDaysLabel = new Label("Disable Specific Days:");
        CheckBox disableDaysCheckbox = new CheckBox();
        TextField dailyAllowanceField = new TextField();
        if(!disableDaysCheckbox.isSelected() && !daysLabel.getText().isEmpty()){
            dailyAllowanceField.setEditable(false);
        }else
        {
            dailyAllowanceField.setEditable(true);
        }
        dailyAllowanceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                dailyAllowanceField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        daysDietBox.getChildren().addAll(new Label("Daily Allowance Days:"), dailyAllowanceField);


        disableDaysCheckbox.setOnAction(event->{
            System.out.println("DZIAŁA event");
            if(disableDaysCheckbox.isSelected() == true && !daysLabel.getText().isEmpty()){
                dailyAllowanceField.setEditable(true);


            } else{
                dailyAllowanceField.setEditable(false);


            }
        });

        dailyAllowanceField.setOnKeyTyped(event->{
            String input = dailyAllowanceField.getText();
            if (!input.matches("\\d*")) {
                event.consume();
                System.out.println("DZIAŁA pierwszy if" +input);
            }
            if(!input.isEmpty()){
                int maxDays = Integer.parseInt(daysField.getText());
                int enteredDays = Integer.parseInt(input);
                if(enteredDays > maxDays){
                    dailyAllowanceField.setText(Integer.toString(maxDays));

                }
                System.out.println("DZIAŁA drugi if" +input);


            }
        });




        Label mileageLabel = new Label("Car Mileage (km):");
        TextField mileageField = new TextField();

        Label totalAmountLabel = new Label("Total Reimbursement Amount:");
        Label totalAmountResultLabel = new Label();

        Button applyButton = new Button("Apply");
        applyButton.setOnAction(event -> System.out.println("Date trip:" + datePickerStart.getValue()));

        Button calculateButton = new Button("Calculate Reimbursement");
        calculateButton.setOnAction(event -> {

            double totalAmount = calculateReimbursementAmount(mileageField,dailyAllowanceField);
            totalAmountResultLabel.setText(String.format("$%.2f", totalAmount));
        });

        Button backButton = new Button("Back to Main");
        backButton.setOnAction(event -> main.backToMainScene(administrator));

        GridPane formLayout = new GridPane();
        formLayout.setHgap(11);
        formLayout.setVgap(11);
        formLayout.setPadding(new Insets(10));
        formLayout.addRow(0, dateStartLabel, datePickerStart,dateEndLabel,datePickerEnd);
        formLayout.addRow(1,  receiptBox);
        formLayout.addRow(2, receiptsContainer);
        formLayout.addRow(3, daysLabel, daysField);
        formLayout.addRow(4, disableDaysLabel, disableDaysCheckbox);
        formLayout.addRow(5, daysDietBox);
        formLayout.addRow(6, mileageLabel, mileageField);
        formLayout.addRow(7, totalAmountLabel, totalAmountResultLabel);
        formLayout.addRow(8, calculateButton);
        formLayout.addRow(9, applyButton);
        formLayout.addRow(10, backButton);

        root = new VBox(20);
        root.getChildren().addAll(titleLabel, formLayout);
    }

    private void addReceipt() {
        VBox receiptBox = new VBox(10);

        ComboBox<String> receiptTypeComboBox = new ComboBox<>();
        receiptTypeComboBox.getItems().addAll("Taxi", "Hotel", "Plane Ticket", "Train");
        TextField costField = new TextField();
        Label dateReceipt = new Label("Receipt Date:");
        DatePicker datePickerReceipt = new DatePicker();
        Label receiptTypeText= new Label("Receipt:");
        Label cost = new Label("Cost in $:");
        Button addReceiptButton = new Button("Add Receipt");
        addReceiptButton.setOnAction(event -> addReceiptToList(receiptTypeComboBox.getValue(),Double.parseDouble(costField.getText()),datePickerReceipt.getValue()));



        receiptBox.getChildren().addAll(receiptTypeText,receiptTypeComboBox, cost,costField,dateReceipt,datePickerReceipt, addReceiptButton);
        receiptsContainer.getChildren().add(receiptBox);
    }

    private void addReceiptToList(String receiptType, double cost , LocalDate date){
        Receipt receipt = new Receipt(receiptType, cost ,date,receiptType);
        receiptsList.add(receipt);
        receiptInfoLabel = new Label(receipt.toString());
        receiptBox.getChildren().addAll(receiptInfoLabel);

    }

    private double calculateReimbursementAmount(TextField mileageField, TextField dailyAllowanceField) {
        double totalAmount = 0.0;
        for (Receipt receipt : receiptsList) {
            System.out.println(receipt);
            totalAmount += receipt.getAmount();
        }

        DoubleProperty distanceCostProperty = administrator.distanceCostProperty();
        DoubleProperty dietCostPerDayProperty = administrator.dietCostPerDayProperty();

        double distanceCost = distanceCostProperty.get();
        double dietCostPerDay = dietCostPerDayProperty.get();
        double mileage = Double.parseDouble(mileageField.getText());
        int dailyAllowance = Integer.parseInt(dailyAllowanceField.getText());

        System.out.println("distance cost"+distanceCost);
        System.out.println("dietCostPerDay"+dietCostPerDay);

        System.out.println("mileage"+mileage);

        System.out.println("dailyAllowane"+dailyAllowance);


        totalAmount += (mileage * distanceCost) + (dailyAllowance * dietCostPerDay);

        return totalAmount;
    }

    private void updateDaysField(){
        LocalDate startDate = datePickerStart.getValue();
        LocalDate endDate = datePickerEnd.getValue();
        if(startDate != null && endDate !=null){
            long days = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays() +1;
            daysField.setText(Long.toString(days));
        }
    }

    public VBox getRoot() {
        return root;
    }
}