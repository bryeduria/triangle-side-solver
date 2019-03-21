package calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ComboBox comboTriangle;

    @FXML
    private Button btnCompute;

    @FXML
    private Button btnClearAnswer;

    @FXML
    private Button btnClearAll;

    @FXML
    private TextField txtBoxA;

    @FXML
    private TextField txtBoxB;

    @FXML
    private TextField txtBoxC;

    @FXML
    private RadioButton rbRoundOff;

    @FXML
    private RadioButton rbExact;

    private ToggleGroup rbToggleGroup;

    DecimalFormat ef = new DecimalFormat("#.######");
    DecimalFormat df = new DecimalFormat("#.##");



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtBoxA.requestFocus();

        //This is the comboBox values for the Triangle Selector
        comboTriangle.getItems().addAll("Right Triangle", "Acute Triangle", "Obtuse Triangle");

        //This is the toggle group for the radio buttons
        rbToggleGroup = new ToggleGroup();
        this.rbExact.setToggleGroup(rbToggleGroup);
        this.rbRoundOff.setToggleGroup(rbToggleGroup);

    }


    public void handleButtonEvent(ActionEvent e) {

        if (txtBoxA.getText() == null || txtBoxA.getText().trim().isEmpty()) {
            if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxA.clear());
                //Display the value of the missing leg in exact value
                txtBoxA.setText(String.valueOf(ef.format(this.findLeg(txtBoxC.getText(), txtBoxB.getText()))));
            }
            else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxA.clear());
                //Display the value of the missing leg in decimal notation
                txtBoxA.setText(String.valueOf(df.format(this.findLeg(txtBoxC.getText(), txtBoxB.getText()))));
            }
        }

        else if ((txtBoxB.getText() == null) || txtBoxB.getText().trim().isEmpty()) {
            if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxB.clear());
                //Display the value of the missing leg in exact value
                txtBoxB.setText(String.valueOf(ef.format(this.findLeg(txtBoxC.getText(), txtBoxA.getText()))));
            }
            else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxB.clear());
                //Display the value of the missing leg in decimal notation
                txtBoxB.setText(String.valueOf(df.format(this.findLeg(txtBoxC.getText(), txtBoxA.getText()))));
            }
        }

        else if ((txtBoxC.getText() == null) || txtBoxC.getText().trim().isEmpty()) {
            if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxC.clear());
                //Display the value of the missing leg in exact value
                txtBoxC.setText(String.valueOf(df.format(findHypotenuse(txtBoxA.getText(), txtBoxB.getText()))));
            }
            else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                this.btnClearAnswer.setOnAction((ActionEvent event) -> txtBoxC.clear());
                //Display the value of the missing leg in decimal notation
                txtBoxC.setText(String.valueOf(df.format(findHypotenuse(txtBoxA.getText(), txtBoxB.getText()))));

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


