package calc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class RightTriangleController implements Initializable {

    @FXML
    public ComboBox comboTriangle;

    @FXML
    public Button btnCompute;

    @FXML
    public Button btnClearAnswer;

    @FXML
    public Button btnClearAll;

    @FXML
    public TextField txtBoxA;

    @FXML
    public TextField txtBoxB;

    @FXML
    public TextField txtBoxC;

    @FXML
    public RadioButton rbRoundOff;

    @FXML
    public RadioButton rbExact;

    @FXML
    public MenuItem close;

    @FXML
    public MenuItem contact;

    @FXML
    public MenuBar menuBar;

    @FXML
    public Parent root;

    public ToggleGroup rbToggleGroup;

    DecimalFormat ef = new DecimalFormat("#.######");
    DecimalFormat df = new DecimalFormat("#.##");

    ObservableList<String>  triangleList = FXCollections.observableArrayList("Right Triangle", "Oblique Triangle");

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //This is the comboBox values for the Triangle Selector
        comboTriangle.setItems(triangleList);

        //This is the toggle group for the radio buttons
        rbToggleGroup = new ToggleGroup();
        this.rbExact.setToggleGroup(rbToggleGroup);
        this.rbRoundOff.setToggleGroup(rbToggleGroup);

    }

   @FXML
    private void openContactUs() {
        openWindow("/calc/Contact.fxml");
    }

    // ...

    private void openWindow(String resource) {
        try {
            root = FXMLLoader.load(getClass().getResource(resource));
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setScene(scene);
            newWindow.initModality(Modality.APPLICATION_MODAL); // makes stage act as a modal
            newWindow.setMinWidth(250); // sets stage width
            newWindow.setMinHeight(250); // sets stage height
            newWindow.setResizable(false); // prevents resize and removes minimize and maximize buttons
            newWindow.showAndWait(); // blocks execution until the stage is closed
            newWindow.show();
        } catch (Exception exc) {
            // handle errors....
        }
    }

    public void selectTriangle(ActionEvent event) throws IOException {

        String triangleType = comboTriangle.getValue().toString();

        if (triangleType == "Right Triangle") {

            Parent rightTriangleLayout = FXMLLoader.load(getClass().getResource("RightTriangle.fxml"));
            Scene rightTriangleScene = new Scene(rightTriangleLayout);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(rightTriangleScene);
            window.show();

            System.out.println("This is the scene for Right Triangle.");
        }

        else if (triangleType == "Oblique Triangle") {

            Parent obliqueTriangleLayout = FXMLLoader.load(getClass().getResource("ObliqueTriangle.fxml"));
            Scene obliqueTriangleScene = new Scene(obliqueTriangleLayout);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(obliqueTriangleScene);
            window.show();

            System.out.println("This is the scene for Oblique Triangle.");
        }
    }


    public void handleButtonEvent(ActionEvent e) {

        if (txtBoxA.getText() == null || txtBoxA.getText().trim().isEmpty()) {
            if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxA.clear());
                    //Display the value of the missing leg in exact value
                    txtBoxA.setText(ef.format(RightTriangleController.this.findLeg(txtBoxC.getText(), txtBoxB.getText())));
                }
            else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxA.clear());
                    //Display the value of the missing leg in decimal notation
                    txtBoxA.setText(df.format(RightTriangleController.this.findLeg(txtBoxC.getText(), txtBoxB.getText())));
            }
        }

        else if ((txtBoxB.getText() == null) || txtBoxB.getText().trim().isEmpty()) {
            if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxB.clear());
                //Display the value of the missing leg in exact value
                txtBoxB.setText(ef.format(this.findLeg(txtBoxC.getText(), txtBoxA.getText())));
            }
            else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxB.clear());
                //Display the value of the missing leg in decimal notation
                txtBoxB.setText(df.format(this.findLeg(txtBoxC.getText(), txtBoxA.getText())));
            }
        }

        else if ((txtBoxC.getText() == null) || txtBoxC.getText().trim().isEmpty()) {
            if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxC.clear());
                //Display the value of the missing leg in exact value
                txtBoxC.setText(ef.format(findHypotenuse(txtBoxA.getText(), txtBoxB.getText())));
            }
            else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxC.clear());
                //Display the value of the missing leg in decimal notation
                txtBoxC.setText(df.format(findHypotenuse(txtBoxA.getText(), txtBoxB.getText())));

            }
        }

        this.btnClearAll.setOnAction((ActionEvent event) -> {
            txtBoxA.clear();
            txtBoxB.clear();
            txtBoxC.clear();
        });

    }


    //Right Triangle Formula - Hypotenuse
    public double findHypotenuse(String a, String b) {

        Double leg1 = Double.valueOf(a);
        Double leg2 = Double.valueOf(b);

        Double c = Math.pow(leg1, 2) + Math.pow(leg2, 2);

        return Math.sqrt(c);
    }

    //Right Triangle Formula - Legs
    private double findLeg(String c, String y) {

        Double hypo = Double.valueOf(c);
        Double leg = Double.valueOf(y);

        if (leg < hypo) {
            Double x = Math.pow(hypo, 2) - Math.pow(leg, 2);

            return Math.sqrt(x);
        }

        else {
            Double x = Math.pow(leg, 2) - Math.pow(hypo, 2);

            return Math.sqrt(x);
        }
    }

}


