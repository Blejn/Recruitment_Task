
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
    private  TextField usernameField;
    private List<Receipt> receiptsList;
    private Label receiptInfoLabel;
    private VBox receiptBox;
    private VBox daysDietBox;
    private Administrator administrator;
    private List<ReimbursementApplication> applicationsList;

    private User user;

    private double totalCost;




    public UserView(Main main, Administrator administrator,User user, List<ReimbursementApplication> applicationsList) {
        receiptsList = new ArrayList<>();
        receiptBox = new VBox(10);
        daysDietBox = new VBox();
        receiptsContainer = new HBox();
        this.administrator = administrator;
        this.applicationsList = applicationsList;


        addReceipt();
        Label titleLabel = new Label("Create Reimbursement Claim");

       Label usernameLabel = new Label("username");
       usernameField = new TextField();


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
        HBox dailyAllowanceBox = new HBox(10);
        dailyAllowanceBox.getChildren().addAll(new Label("Daily Allowance Days:"), dailyAllowanceField);
        daysDietBox.getChildren().add(dailyAllowanceBox);


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
        applyButton.setOnAction(event -> {apply();});

        Button calculateButton = new Button("Calculate Reimbursement");
        calculateButton.setOnAction(event -> {

            double totalAmount = calculateReimbursementAmount(mileageField,dailyAllowanceField);
            totalAmountResultLabel.setText(String.format("$%.2f", totalAmount));
        });

        Button backButton = new Button("Back to Main");
        backButton.setOnAction(event -> main.backToMainScene(administrator));

        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.setPadding(new Insets(10));
        formLayout.addRow(0, usernameLabel, usernameField);
        formLayout.addRow(1, dateStartLabel, datePickerStart,dateEndLabel,datePickerEnd);
        formLayout.addRow(2,  receiptBox);
        formLayout.addRow(3, receiptsContainer);
        formLayout.addRow(4, daysLabel, daysField);
        formLayout.addRow(5, disableDaysLabel, disableDaysCheckbox);
        formLayout.addRow(6, daysDietBox);
        formLayout.addRow(7, mileageLabel, mileageField);
        formLayout.addRow(8, totalAmountLabel, totalAmountResultLabel);
        formLayout.addRow(9, calculateButton);
        formLayout.addRow(10, applyButton);
        formLayout.addRow(11, backButton);

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
        totalAmount += (mileage * distanceCost) + (dailyAllowance * dietCostPerDay);
        totalCost = totalAmount;
        return totalAmount;
    }

    private void apply(){
        String username = usernameField.getText();
        List<Receipt> receipts = new ArrayList<>(receiptsList);
        double total = totalCost;
        System.out.println(username);
        for(Receipt receipt:receipts){
            System.out.println(receipt.getName());
            System.out.println(receipt.getAmount());
        }
        System.out.println(total);
        ReimbursementApplication application = new ReimbursementApplication(username, receipts, total);
        applicationsList.add(application);
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