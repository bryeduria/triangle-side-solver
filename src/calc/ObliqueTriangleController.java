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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ObliqueTriangleController implements Initializable {

    @FXML
    private Button btnClearAll;

    @FXML
    private Button btnClearAnswer;

    @FXML
    private TextField txtBoxSideA;

    @FXML
    private TextField txtBoxSideC;

    @FXML
    private TextField txtBoxSideB;

    @FXML
    private ComboBox comboTriangle;

    @FXML
    private RadioButton rbExact;

    @FXML
    private Button btnCompute;

    @FXML
    private TextField txtBoxAngleB;

    @FXML
    private TextField txtBoxAngleC;

    @FXML
    private TextField txtBoxAngleA;

    @FXML
    private RadioButton rbRoundOff;

    @FXML
    private RadioButton rbAAS;

    @FXML
    private RadioButton rbASA;

    @FXML
    private RadioButton rbSAS;

    @FXML
    private RadioButton rbSSA;

    @FXML
    private RadioButton rbSSS;

    public ToggleGroup rbToggleGroup;

    public ToggleGroup rbTriangle;

    DecimalFormat ef = new DecimalFormat("#.######");
    DecimalFormat df = new DecimalFormat("#.##");

    ObservableList<String> triangleList = FXCollections.observableArrayList("Right Triangle", "Oblique Triangle");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //This is the comboBox values for the Triangle Selector
        comboTriangle.setItems(triangleList);

        //This is the toggle group for the radio buttons
        rbToggleGroup = new ToggleGroup();
        this.rbExact.setToggleGroup(rbToggleGroup);
        this.rbRoundOff.setToggleGroup(rbToggleGroup);

        //Toggle group for
        rbTriangle = new ToggleGroup();
        this.rbAAS.setToggleGroup(rbTriangle);
        this.rbASA.setToggleGroup(rbTriangle);
        this.rbSAS.setToggleGroup(rbTriangle);
        this.rbSSA.setToggleGroup(rbTriangle);
        this.rbSSS.setToggleGroup(rbTriangle);
    }

    @FXML
    void selectTriangle(ActionEvent event) throws IOException {
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

    @FXML
    void handleButtonEvent(ActionEvent t) {

        //Formula for AAS
        if (this.rbTriangle.getSelectedToggle().equals(this.rbAAS)) {

            //Verify output
            if (((txtBoxAngleB.getText() == null) && (txtBoxAngleB.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null) && (txtBoxSideB.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null && (txtBoxSideC.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleB.setText(df.format(new formulaAAS().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideB.setText(df.format(new formulaAAS().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(df.format(new formulaAAS().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleB.setText(ef.format(new formulaAAS().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAAS().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAAS().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));

                    //clear output
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                }

            }
            //Verify output
            if (((txtBoxAngleB.getText() == null) && (txtBoxAngleB.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null) && (txtBoxSideB.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null && (txtBoxSideA.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleB.setText(df.format(new formulaAAS().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideA.setText(df.format(new formulaAAS().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(df.format(new formulaAAS().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideC.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleB.setText(ef.format(new formulaAAS().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAAS().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAAS().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideC.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                }
            }

            //Verify output
            if (((txtBoxAngleC.getText() == null) && (txtBoxAngleC.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null) && (txtBoxSideC.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null && (txtBoxSideA.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleC.setText(df.format(new formulaAAS().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideA.setText(df.format(new formulaAAS().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(df.format(new formulaAAS().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideA.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleC.setText(ef.format(new formulaAAS().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAAS().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAAS().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideA.clear();
                        txtBoxSideC.clear();
                    });
                }
            }

            //Verify output
            if (((txtBoxAngleC.getText() == null) && (txtBoxAngleC.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null) && (txtBoxSideC.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null && (txtBoxSideB.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleC.setText(df.format(new formulaAAS().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideB.setText(df.format(new formulaAAS().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(df.format(new formulaAAS().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideA.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleC.setText(ef.format(new formulaAAS().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAAS().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAAS().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideA.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                }
            }

            //Verify output
            if (((txtBoxAngleA.getText() == null) && (txtBoxAngleA.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null) && (txtBoxSideA.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null && (txtBoxSideB.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleA.setText(df.format(new formulaAAS().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(df.format(new formulaAAS().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(df.format(new formulaAAS().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleA.setText(ef.format(new formulaAAS().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAAS().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAAS().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                }
            }

            //Verify output
            if (((txtBoxAngleA.getText() == null) && (txtBoxAngleA.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null) && (txtBoxSideA.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null && (txtBoxSideC.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleA.setText(df.format(new formulaAAS().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(df.format(new formulaAAS().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(df.format(new formulaAAS().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideB.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxSideA.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleA.setText(ef.format(new formulaAAS().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAAS().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAAS().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideB.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxSideA.clear();
                        txtBoxSideC.clear();
                    });
                }
            }

            this.btnClearAll.setOnAction((ActionEvent e) -> {
                txtBoxAngleA.clear();
                txtBoxAngleB.clear();
                txtBoxAngleC.clear();
                txtBoxSideA.clear();
                txtBoxSideB.clear();
                txtBoxSideC.clear();
            });
        }


    }
}
