package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TraceController implements Initializable {
    @FXML
    public ListView<String> traceList;
    ObservableList<String> list=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadTrace() throws Exception {
    list.clear();
    list=FXCollections.observableArrayList(FilterTrace.lireop());
    traceList.setItems(list);
    }

}
