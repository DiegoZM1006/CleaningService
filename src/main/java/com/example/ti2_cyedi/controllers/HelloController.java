package com.example.ti2_cyedi.controllers;

import com.example.ti2_cyedi.model.Graph;
import com.example.ti2_cyedi.model.NodeGraph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnAddConnection;

    @FXML
    private Button btnAddStreet;

    @FXML
    private Button btnDeleteRoute;

    @FXML
    private Button btnShortestPath;

    @FXML
    private Button btnVerifyConnection;

    @FXML
    private Button btnVerifyRoute;

    @FXML
    private Button btnVerifyStreet;

    @FXML
    private TextField txtAddFrom;

    @FXML
    private TextField txtAddStreet;

    @FXML
    private TextField txtAddTo;

    @FXML
    private TextField txtVerifyFrom;

    @FXML
    private TextField txtVerifyStreet;

    @FXML
    private TextField txtVerifyTo;

    @FXML
    private TextArea txtMainArea;

    @FXML
    private TextField txtWeightConnection;

    private Graph<String> graph;
    private int pos = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        graph = MenuController.getGraph();
    }

    void showAlert(int i, String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if(i == 1) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else if(i == 2) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }

        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    void btnAddConnection(ActionEvent event) {
        txtMainArea.setText("");
        String nodeFromTxt = txtAddFrom.getText();
        String nodeToTxt = txtAddTo.getText();
        String weightTxt = txtWeightConnection.getText();

        if(!nodeFromTxt.equals("") && !nodeToTxt.equals("") && !weightTxt.equals("")) {
            NodeGraph<String> nodeFrom = graph.searchVertex(nodeFromTxt);
            NodeGraph<String> nodeTo = graph.searchVertex(nodeToTxt);

            if(nodeFrom == null || nodeTo == null) {
                showAlert(1, "Error", "Some of the nodes does not exist");
                txtAddFrom.setText("");
                txtAddTo.setText("");
                txtWeightConnection.setText("");
            } else {
                graph.addEdge(Integer.parseInt(weightTxt), nodeFrom, nodeTo);

                if (graph.searchEdge(nodeFromTxt, nodeToTxt).getTo().getValue().equals(nodeToTxt)) {
                    showAlert(2, "Done","The connection was added");
                    txtAddFrom.setText("");
                    txtAddTo.setText("");
                    txtWeightConnection.setText("");
                } else {
                    showAlert(1, "Error","The connection was not added");
                }
            }
        } else {
            showAlert(1, "Error","Please complete the fields");
        }
    }

    @FXML
    void btnAddStreet(ActionEvent event) {
        txtMainArea.setText("");
        String streetName = txtAddStreet.getText();

        if(!streetName.equals("")) {
            NodeGraph<String> node = new NodeGraph<>(streetName, pos);
            graph.addVertex(node);
            if(node.getValue().equals(graph.searchVertex(streetName).getValue())) {
                showAlert(2, "Done","The street was added");
                pos = pos + 1;
                txtAddStreet.setText("");
            } else {
                showAlert(1, "Error","The street was not added");
            }
        } else {
            showAlert(1, "Error","Please complete the field");
        }
    }

    @FXML
    void btnDeleteRoute(ActionEvent event) {

        if(graph.deleteGraph()){
            txtMainArea.setText("The graph was deleted");
            pos = 0;
        } else {
            txtMainArea.setText("Cannot delete the graph because is already empty");
        }

    }

    @FXML
    void btnShortestPath(ActionEvent event) {

        if(graph.getImplementation().equals("1")) {
            if(graph.getArrGraph().isEmpty()){
                txtMainArea.setText("There is no path");
            }else {
                String h = graph.floydWarshall2();
                txtMainArea.setText(h);
            }
        } else {
            String h = graph.floydWarshall2();
            txtMainArea.setText(h);
        }

    }

    @FXML
    void btnVerifyConnection(ActionEvent event) {
        txtMainArea.setText("");
        String verifyFromTxt = txtVerifyFrom.getText();
        String verifyToTxt = txtVerifyTo.getText();

        if(!verifyFromTxt.equals("") && !verifyToTxt.equals("")) {
            NodeGraph<String> nodeFrom = graph.searchVertex(verifyFromTxt);
            NodeGraph<String> nodeTo = graph.searchVertex(verifyToTxt);

            if(nodeFrom == null || nodeTo == null) {
                showAlert(1, "Error", "Some of the nodes does not exist");
                txtVerifyFrom.setText("");
                txtVerifyTo.setText("");
            } else if (graph.searchEdge(verifyFromTxt, verifyToTxt) != null) {
                showAlert(2, "Done","The connection exist");
                txtVerifyFrom.setText("");
                txtVerifyTo.setText("");
            } else {
                showAlert(1, "Error","The connection not exist");
                txtVerifyFrom.setText("");
                txtVerifyTo.setText("");
            }
        } else {
            showAlert(1, "Error","Please complete the fields");
        }

    }

    @FXML
    void btnVerifyRoute(ActionEvent event) {

        if(graph.getImplementation().equalsIgnoreCase("1")) {
            if(!graph.getArrGraph().isEmpty()) {
                if(graph.BFS(graph.getArrGraph().get(0).getValue())){
                    txtMainArea.setText("The route is good, you can go through all the city " + "\ncongrats! ");
                } else {
                    txtMainArea.setText("The route is not strongly connected");
                }
            } else {
                txtMainArea.setText("Cannot do the BFS algorithm because the graph is " + "\nempty");
            }
        } else {
            if(graph.getMatrixNode2()!= null) {
                if (graph.BFS(graph.getMatrixNode2())) {
                    txtMainArea.setText("The route is good, you can go through all the city " + "\ncongrats! ");
                } else {
                    txtMainArea.setText("The route is not strongly connected");
                }
            } else {
                txtMainArea.setText("Cannot do the BFS algorithm because the graph is " + "\nempty");
            }
        }

    }

    @FXML
    void btnVerifyStreet(ActionEvent event) {
        txtMainArea.setText("");
        String verifyStreetTxt = txtVerifyStreet.getText();

        if(!verifyStreetTxt.equals("")) {
            NodeGraph<String> nodeToSearch = graph.searchVertex(verifyStreetTxt);

            if(nodeToSearch == null) {
                showAlert(1, "Error", "The street does not exist");
                txtVerifyStreet.setText("");
            }  else {
                showAlert(2, "Done","The street exist");
                txtVerifyStreet.setText("");
            }
        } else {
            showAlert(1, "Error","Please complete the fields");
        }

    }



}