package com.IsaacDeveloperTools.userInterface;

import com.IsaacDeveloperTools.IsaacDeveloperTools;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class IsaacDeveloperToolsUIController implements Initializable {

    @FXML
    private Button setCoordinate1;
    @FXML
    private Button setCoordinate2;
    @FXML
    private Button setObjectName;

    @FXML
    private TextField coordinate1;
    @FXML
    private TextField coordinate2;
    @FXML
    private TextField objectName;
    @FXML
    private TextArea codeOutput;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        setCoordinate1.setOnAction(event -> {
            coordinate1.setText(IsaacDeveloperTools.getCurrentPosition());
        });

        setCoordinate2.setOnAction(event -> {
            coordinate2.setText(IsaacDeveloperTools.getCurrentPosition());
        });

        setObjectName.setOnAction(event -> {

            var smartObject = "new SmartObject(new Area.Rectangular(new " + coordinate1.getText() + ", new " + coordinate2.getText() + "), \"" + objectName.getText() + "\", \"" + IsaacDeveloperTools.getObjectType(objectName.getText()) + "\"))";

            codeOutput.setText(smartObject);
        });
    }

}