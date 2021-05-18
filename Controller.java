package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;


public class Controller {

    private final Map<String, Integer> uniqCheckMap = new HashMap<>();

    @FXML
    TextArea textArea;
    @FXML
    TextField textField;



    public void sendMsg(){
        String warning = validate();
        if (warning == null) {
            textArea.appendText(textField.getText() + "\n");
            textField.clear();
        } else {
            new Alert(Alert.AlertType.WARNING, warning, ButtonType.OK).showAndWait();
        }
            textField.requestFocus();


    }

    private String validate() {
        String textFromField = textField.getText();
        String warning = null;
        if (textFromField.isEmpty()) {
            warning = "Нельзя отправлять пустое сообщение";
        } else {
            Integer count = uniqCheckMap.getOrDefault(textFromField, 0);
            if(count.equals(0)) {
                uniqCheckMap.clear();
            }
            uniqCheckMap.put(textFromField, ++count);
            if(count > 3) {
                warning = "Нельзя отправлять больше 3 одинаковых сообщений подряд. № вышей попытки: " + count;
            }
        }

            return warning;
    }

}
