package sample;


import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller extends Application {
    @FXML
    private AnchorPane rootPane;
    @FXML
    public TextField op1;
    @FXML
    public TextField op2;

    public static Label resultLabel=new Label("0");
    @FXML
    public Button somme;
    @FXML
    public Button produit;
    @FXML
    public Button facto;
    @FXML
    private Button quitter;
    @FXML
    public VBox vboxLay;
    @FXML
    public Button trace;

    public static final CountDownLatch latch = new CountDownLatch(1);
    public static Controller c = null;

    public static Controller waitForLaunch() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return c;
    }
    public static void setC(Controller _c) {
        c = _c;
    }
    public Controller() {
        setC(this);

    }
    @FXML
    private void cancel(javafx.event.ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


    public void loadTrace(){
        loadWindow("trace.fxml", "Trace");
    }

    void loadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FilterGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void alertNonEmpty(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir les 2 champs");
        alert.showAndWait();

    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Calculatrice");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        latch.countDown();
        resultLabel.setFont(Font.font("System Bold"));
        resultLabel.setAlignment(Pos.CENTER);
        System.out.println("label est"+c.resultLabel.getText());
        resultLabel.setStyle("-fx-font-weight: bold");
        resultLabel.setPadding(new Insets(0, 0, 0, 240));
        c.vboxLay.getChildren().add(7,c.resultLabel);
    }
    public static void main(String[] args) {
        Application.launch();
    }


}
