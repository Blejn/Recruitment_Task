import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class UserView {
    private VBox root;
    public UserView() {
        Button claimButton = new Button("Create Reimbursement Claim");

        root = new VBox(10);
        root.getChildren().add(claimButton);
    }

    public VBox getRoot() {
        return root;
    }
}
