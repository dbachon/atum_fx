package sample.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import okhttp3.ResponseBody;

import java.io.IOException;

public class AlertsFactory {

    public static void apiCallError(Throwable throwable) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Błąd Krytyczny");
            alert.setHeaderText("Brak połączenia z API");
            alert.setContentText(throwable.getMessage());

            alert.showAndWait();
        });
    }

    public static void responseStatusError(ResponseBody body) {
        String message = null;
        if (body != null) {
            try {
                message = body.string();
            } catch (IOException e) {
                AlertsFactory.unknownError(e.getMessage());
            }
        }
        String messageReceived = message;
        Platform.runLater(() -> {

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Błąd");
            alert.setHeaderText("Coś poszło nie tak.");
            alert.setContentText(messageReceived);
            alert.showAndWait();
        });
    }

    private static void unknownError(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Błąd Krytyczny");
            alert.setHeaderText("Nieznany błąd...");
            alert.setContentText(message);
            alert.showAndWait();
        });
    }


    public static void success(String message) {
        Platform.runLater(() -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukces");
            alert.setHeaderText("Operacja zakończona powodzeniem.");
            alert.setContentText(message);

            alert.showAndWait();
        });
    }

}