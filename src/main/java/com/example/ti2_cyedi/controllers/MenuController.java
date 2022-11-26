package com.example.ti2_cyedi.controllers;

import com.example.ti2_cyedi.HelloApplication;
import com.example.ti2_cyedi.model.Graph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private TextField txtColumns;

    @FXML
    private TextField txtGraphCreation;

    @FXML
    private TextField txtRows;

    private static Graph<String> graph;

    @FXML
    void btnCreation(ActionEvent event) {

        String txtImplementation = txtGraphCreation.getText();
        String txtR = txtRows.getText();
        String txtC = txtColumns.getText();

        if(!txtImplementation.equals("") && !txtR.equals("") && !txtC.equals("")) {
            if(txtImplementation.equals("1") || txtImplementation.equals("2")) {
                if(txtImplementation.equals("1")) {
                    txtImplementation = "adjacency_list";
                    graph = new Graph<>(txtImplementation, 0, 0);
                    HelloApplication.showWindow("hello-view.fxml");
                    Stage currentStage = (Stage) txtGraphCreation.getScene().getWindow();
                    currentStage.hide();
                } else {
                    if(txtR.equals(txtC)) {
                        graph = new Graph<>(txtImplementation, Integer.parseInt(txtR), Integer.parseInt(txtC));
                        HelloApplication.showWindow("hello-view.fxml");
                        Stage currentStage = (Stage) txtGraphCreation.getScene().getWindow();
                        currentStage.hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("Rows and columns should be the same");
                        alert.showAndWait();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Invalid graph creation");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Complete all fields");
            alert.showAndWait();
        }

    }

    public static Graph<String> getGraph() {
        return graph;
    }

}