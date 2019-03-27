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
        this.rbSSA.setToggleGroup(rbTriangle);
        this.rbSSS.setToggleGroup(rbTriangle);
    }

    @FXML
    void closeMenu(ActionEvent event) {

    }

    @FXML
    void contactMenu(ActionEvent event) {

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
    public void handleButtonEvent(ActionEvent t) {

        //Formula for AAS
        if (this.rbTriangle.getSelectedToggle().equals(this.rbAAS)) {
            System.out.println("AAS toggled");
            //Verify output
            if (((txtBoxAngleB.getText() == null) || (txtBoxAngleB.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null) || (txtBoxSideB.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null || (txtBoxSideC.getText().trim().isEmpty())))) {
                System.out.println("Find Angle B, Side B and Side C");
                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleB.setText(df.format(new formulaAASASA().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideB.setText(df.format(new formulaAASASA().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(df.format(new formulaAASASA().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleB.setText(ef.format(new formulaAASASA().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAASASA().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAASASA().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    System.out.println("Exact Value");
                    //clear output
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                }

            }
            //Verify output
            else if (((txtBoxAngleB.getText() == null) || (txtBoxAngleB.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null) || (txtBoxSideB.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null || (txtBoxSideA.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleB.setText(df.format(new formulaAASASA().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideA.setText(df.format(new formulaAASASA().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(df.format(new formulaAASASA().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideC.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleB.setText(ef.format(new formulaAASASA().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAASASA().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAASASA().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleC.getText(), txtBoxSideC.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                }
            }

            //Verify output
            else if (((txtBoxAngleC.getText() == null) || (txtBoxAngleC.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null) || (txtBoxSideC.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null || (txtBoxSideA.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleC.setText(df.format(new formulaAASASA().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideA.setText(df.format(new formulaAASASA().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(df.format(new formulaAASASA().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideA.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleC.setText(ef.format(new formulaAASASA().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAASASA().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAASASA().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideA.clear();
                        txtBoxSideC.clear();
                    });
                }
            }

            //Verify output
            else if (((txtBoxAngleC.getText() == null) || (txtBoxAngleC.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null) || (txtBoxSideC.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null || (txtBoxSideB.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleC.setText(df.format(new formulaAASASA().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideB.setText(df.format(new formulaAASASA().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(df.format(new formulaAASASA().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideA.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleC.setText(ef.format(new formulaAASASA().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAASASA().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAASASA().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleA.getText(), txtBoxSideA.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                }
            }

            //Verify output
            else if (((txtBoxAngleA.getText() == null) || (txtBoxAngleA.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null) || (txtBoxSideA.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null || (txtBoxSideB.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleA.setText(df.format(new formulaAASASA().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(df.format(new formulaAASASA().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(df.format(new formulaAASASA().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleA.setText(ef.format(new formulaAASASA().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAASASA().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAASASA().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                }
            }

            //Verify output
            else if (((txtBoxAngleA.getText() == null) || (txtBoxAngleA.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null) || (txtBoxSideA.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null || (txtBoxSideC.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleA.setText(df.format(new formulaAASASA().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(df.format(new formulaAASASA().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(df.format(new formulaAASASA().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideB.getText())));

                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxSideA.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleA.setText(ef.format(new formulaAASASA().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAASASA().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAASASA().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleB.getText(), txtBoxSideB.getText())));

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

        //Formula for ASA
        else if (this.rbTriangle.getSelectedToggle().equals(this.rbASA)) {

            //Verify output
            if (((txtBoxAngleC.getText() == null) || (txtBoxAngleC.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null) || (txtBoxSideA.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null || (txtBoxSideB.getText().trim().isEmpty())))) {
                System.out.println("Find Angle B, Side B and Side C");
                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleC.setText(df.format(new formulaAASASA().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(df.format(new formulaAASASA().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(df.format(new formulaAASASA().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleC.setText(ef.format(new formulaAASASA().findMissingAngle(txtBoxAngleA.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAASASA().findMissingSide1(txtBoxAngleA.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAASASA().findMissingSide2(txtBoxAngleA.getText(), txtBoxAngleB.getText(), txtBoxSideC.getText())));
                    System.out.println("Exact Value");
                    //clear output
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxSideA.clear();
                        txtBoxSideB.clear();
                    });
                }

            }

            //Verify output
            else if (((txtBoxAngleA.getText() == null) || (txtBoxAngleA.getText().trim().isEmpty())) && ((txtBoxSideB.getText() == null) || (txtBoxSideB.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null || (txtBoxSideC.getText().trim().isEmpty())))) {
                System.out.println("Find Angle B, Side B and Side C");
                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleA.setText(df.format(new formulaAASASA().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleC.getText())));
                    txtBoxSideB.setText(df.format(new formulaAASASA().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(df.format(new formulaAASASA().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleA.setText(ef.format(new formulaAASASA().findMissingAngle(txtBoxAngleB.getText(), txtBoxAngleC.getText())));
                    txtBoxSideB.setText(ef.format(new formulaAASASA().findMissingSide1(txtBoxAngleB.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAASASA().findMissingSide2(txtBoxAngleB.getText(), txtBoxAngleC.getText(), txtBoxSideA.getText())));
                    System.out.println("Exact Value");
                    //clear output
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxSideB.clear();
                        txtBoxSideC.clear();
                    });
                }

            }

            //Verify output
            else if (((txtBoxAngleB.getText() == null) || (txtBoxAngleB.getText().trim().isEmpty())) && ((txtBoxSideA.getText() == null) || (txtBoxSideA.getText().trim().isEmpty())) && ((txtBoxSideC.getText() == null || (txtBoxSideC.getText().trim().isEmpty())))) {
                System.out.println("Find Angle B, Side B and Side C");
                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleB.setText(df.format(new formulaAASASA().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleA.getText())));
                    txtBoxSideA.setText(df.format(new formulaAASASA().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(df.format(new formulaAASASA().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideA.clear();
                        txtBoxSideC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleB.setText(ef.format(new formulaAASASA().findMissingAngle(txtBoxAngleC.getText(), txtBoxAngleA.getText())));
                    txtBoxSideA.setText(ef.format(new formulaAASASA().findMissingSide1(txtBoxAngleC.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));
                    txtBoxSideC.setText(ef.format(new formulaAASASA().findMissingSide2(txtBoxAngleC.getText(), txtBoxAngleA.getText(), txtBoxSideB.getText())));
                    System.out.println("Exact Value");
                    //clear output
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxSideA.clear();
                        txtBoxSideC.clear();
                    });
                }

            }

        }

        //Formula for SSA
        else if (this.rbTriangle.getSelectedToggle().equals(this.rbSSA)) {

            //inputs abA
            if (!((txtBoxSideA.getText() == null) || (txtBoxSideA.getText().trim().isEmpty())) && !((txtBoxSideB.getText() == null) || (txtBoxSideB.getText().trim().isEmpty())) && !((txtBoxAngleA.getText() == null || (txtBoxAngleA.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleB.setText(df.format(new formulaSSS().findAngleA(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxAngleA.getText())));
                    txtBoxAngleC.setText(df.format(new formulaSSS().findAngleB(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideC.setText(df.format(new formulaSSS().findAngleC(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxAngleA.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxAngleC.clear();
                        txtBoxSideC.clear();
                    });
                }

                else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleB.setText(ef.format(new formulaSSS().findAngleA(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxAngleA.getText())));
                    txtBoxAngleC.setText(ef.format(new formulaSSS().findAngleB(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxAngleA.getText())));
                    txtBoxSideC.setText(ef.format(new formulaSSS().findAngleC(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxAngleA.getText())));
                    System.out.println("Exact");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxAngleC.clear();
                        txtBoxSideC.clear();
                    });
                }
            }

            //inputs acA
            else if (!((txtBoxSideA.getText() == null) || (txtBoxSideA.getText().trim().isEmpty())) && !((txtBoxSideC.getText() == null) || (txtBoxSideC.getText().trim().isEmpty())) && !((txtBoxAngleA.getText() == null || (txtBoxAngleA.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleB.setText(df.format(new formulaSSS().findAngleA(txtBoxSideA.getText(), txtBoxSideC.getText(), txtBoxAngleA.getText())));
                    txtBoxAngleC.setText(df.format(new formulaSSS().findAngleB(txtBoxSideA.getText(), txtBoxSideC.getText(), txtBoxAngleA.getText())));
                    txtBoxSideB.setText(df.format(new formulaSSS().findAngleC(txtBoxSideA.getText(), txtBoxSideC.getText(), txtBoxAngleA.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxAngleC.clear();
                        txtBoxSideB.clear();
                    });
                }

                else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleB.setText(ef.format(new formulaSSS().findAngleA(txtBoxSideA.getText(), txtBoxSideC.getText(), txtBoxAngleA.getText())));
                    txtBoxAngleC.setText(ef.format(new formulaSSS().findAngleB(txtBoxSideA.getText(), txtBoxSideC.getText(), txtBoxAngleA.getText())));
                    txtBoxSideB.setText(ef.format(new formulaSSS().findAngleC(txtBoxSideA.getText(), txtBoxSideC.getText(), txtBoxAngleA.getText())));
                    System.out.println("Exact");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxAngleC.clear();
                        txtBoxSideB.clear();
                    });
                }
            }

            //inputs bcB
            else if (!((txtBoxSideB.getText() == null) || (txtBoxSideB.getText().trim().isEmpty())) && !((txtBoxSideC.getText() == null) || (txtBoxSideC.getText().trim().isEmpty())) && !((txtBoxAngleB.getText() == null || (txtBoxAngleB.getText().trim().isEmpty())))) {

                //select between rounded off and exact values
                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleA.setText(df.format(new formulaSSS().findAngleA(txtBoxSideB.getText(), txtBoxSideC.getText(), txtBoxAngleB.getText())));
                    txtBoxAngleC.setText(df.format(new formulaSSS().findAngleB(txtBoxSideB.getText(), txtBoxSideC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(df.format(new formulaSSS().findAngleC(txtBoxSideB.getText(), txtBoxSideC.getText(), txtBoxAngleB.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxAngleC.clear();
                        txtBoxSideA.clear();
                    });
                }

                else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleA.setText(ef.format(new formulaSSS().findAngleA(txtBoxSideB.getText(), txtBoxSideC.getText(), txtBoxAngleB.getText())));
                    txtBoxAngleC.setText(ef.format(new formulaSSS().findAngleB(txtBoxSideB.getText(), txtBoxSideC.getText(), txtBoxAngleB.getText())));
                    txtBoxSideA.setText(ef.format(new formulaSSS().findAngleC(txtBoxSideB.getText(), txtBoxSideC.getText(), txtBoxAngleB.getText())));
                    System.out.println("Exact");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxAngleC.clear();
                        txtBoxSideA.clear();
                    });
                }
            }

            //inputs baB
            else if (!((txtBoxSideB.getText() == null) || (txtBoxSideB.getText().trim().isEmpty())) && !((txtBoxSideA.getText() == null) || (txtBoxSideA.getText().trim().isEmpty())) && !((txtBoxAngleB.getText() == null || (txtBoxAngleB.getText().trim().isEmpty())))) {

                //select between rounded off and exact values
                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleC.setText(df.format(new formulaSSS().findAngleA(txtBoxSideB.getText(), txtBoxSideA.getText(), txtBoxAngleB.getText())));
                    txtBoxAngleA.setText(df.format(new formulaSSS().findAngleB(txtBoxSideB.getText(), txtBoxSideA.getText(), txtBoxAngleB.getText())));
                    txtBoxSideC.setText(df.format(new formulaSSS().findAngleC(txtBoxSideB.getText(), txtBoxSideA.getText(), txtBoxAngleB.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxAngleA.clear();
                        txtBoxSideC.clear();
                    });
                }

                else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleC.setText(ef.format(new formulaSSS().findAngleA(txtBoxSideB.getText(), txtBoxSideA.getText(), txtBoxAngleB.getText())));
                    txtBoxAngleA.setText(ef.format(new formulaSSS().findAngleB(txtBoxSideB.getText(), txtBoxSideA.getText(), txtBoxAngleB.getText())));
                    txtBoxSideC.setText(ef.format(new formulaSSS().findAngleC(txtBoxSideB.getText(), txtBoxSideA.getText(), txtBoxAngleB.getText())));
                    System.out.println("Exact");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleC.clear();
                        txtBoxAngleA.clear();
                        txtBoxSideC.clear();
                    });
                }
            }

            //inputs caC
            else if (!((txtBoxSideC.getText() == null) || (txtBoxSideC.getText().trim().isEmpty())) && !((txtBoxSideA.getText() == null) || (txtBoxSideA.getText().trim().isEmpty())) && !((txtBoxAngleC.getText() == null || (txtBoxAngleC.getText().trim().isEmpty())))) {

                //select between rounded off and exact values
                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleB.setText(df.format(new formulaSSS().findAngleA(txtBoxSideC.getText(), txtBoxSideA.getText(), txtBoxAngleC.getText())));
                    txtBoxAngleA.setText(df.format(new formulaSSS().findAngleB(txtBoxSideC.getText(), txtBoxSideA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideB.setText(df.format(new formulaSSS().findAngleC(txtBoxSideC.getText(), txtBoxSideA.getText(), txtBoxAngleC.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxAngleA.clear();
                        txtBoxSideB.clear();
                    });
                }

                else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleB.setText(ef.format(new formulaSSS().findAngleA(txtBoxSideC.getText(), txtBoxSideA.getText(), txtBoxAngleC.getText())));
                    txtBoxAngleA.setText(ef.format(new formulaSSS().findAngleB(txtBoxSideC.getText(), txtBoxSideA.getText(), txtBoxAngleC.getText())));
                    txtBoxSideB.setText(ef.format(new formulaSSS().findAngleC(txtBoxSideC.getText(), txtBoxSideA.getText(), txtBoxAngleC.getText())));
                    System.out.println("Exact");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleB.clear();
                        txtBoxAngleA.clear();
                        txtBoxSideB.clear();
                    });
                }
            }

            //inputs cbC
            else if (!((txtBoxSideC.getText() == null) || (txtBoxSideC.getText().trim().isEmpty())) && !((txtBoxSideB.getText() == null) || (txtBoxSideB.getText().trim().isEmpty())) && !((txtBoxAngleC.getText() == null || (txtBoxAngleC.getText().trim().isEmpty())))) {

                //select between rounded off and exact values
                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleA.setText(df.format(new formulaSSS().findAngleA(txtBoxSideC.getText(), txtBoxSideB.getText(), txtBoxAngleC.getText())));
                    txtBoxAngleB.setText(df.format(new formulaSSS().findAngleB(txtBoxSideC.getText(), txtBoxSideB.getText(), txtBoxAngleC.getText())));
                    txtBoxSideA.setText(df.format(new formulaSSS().findAngleC(txtBoxSideC.getText(), txtBoxSideB.getText(), txtBoxAngleC.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxAngleB.clear();
                        txtBoxSideA.clear();
                    });
                }

                else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleA.setText(ef.format(new formulaSSS().findAngleA(txtBoxSideC.getText(), txtBoxSideB.getText(), txtBoxAngleC.getText())));
                    txtBoxAngleB.setText(ef.format(new formulaSSS().findAngleB(txtBoxSideC.getText(), txtBoxSideB.getText(), txtBoxAngleC.getText())));
                    txtBoxSideA.setText(ef.format(new formulaSSS().findAngleC(txtBoxSideC.getText(), txtBoxSideB.getText(), txtBoxAngleC.getText())));
                    System.out.println("Exact");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxAngleB.clear();
                        txtBoxSideA.clear();
                    });
                }
            }

        }

        //Formula for SSS
        else if (this.rbTriangle.getSelectedToggle().equals(this.rbSSS)) {

            if (!((txtBoxSideA.getText() == null) || (txtBoxSideA.getText().trim().isEmpty())) && !((txtBoxSideB.getText() == null) || (txtBoxSideB.getText().trim().isEmpty())) && !((txtBoxSideC.getText() == null || (txtBoxSideC.getText().trim().isEmpty())))) {

                if (this.rbToggleGroup.getSelectedToggle().equals(this.rbRoundOff)) {
                    txtBoxAngleA.setText(df.format(new formulaSSS().findAngleA(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxSideC.getText())));
                    txtBoxAngleB.setText(df.format(new formulaSSS().findAngleB(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxSideC.getText())));
                    txtBoxAngleC.setText(df.format(new formulaSSS().findAngleC(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxSideC.getText())));
                    System.out.println("Round off");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxAngleB.clear();
                        txtBoxAngleC.clear();
                    });
                } else if (this.rbToggleGroup.getSelectedToggle().equals(this.rbExact)) {
                    txtBoxAngleA.setText(ef.format(new formulaSSS().findAngleA(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxSideC.getText())));
                    txtBoxAngleB.setText(ef.format(new formulaSSS().findAngleB(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxSideC.getText())));
                    txtBoxAngleC.setText(ef.format(new formulaSSS().findAngleC(txtBoxSideA.getText(), txtBoxSideB.getText(), txtBoxSideC.getText())));
                    System.out.println("Exact Value");
                    this.btnClearAnswer.setOnAction((ActionEvent event) -> {
                        txtBoxAngleA.clear();
                        txtBoxAngleB.clear();
                        txtBoxAngleC.clear();
                    });
                }
            }
        }
    }

}

