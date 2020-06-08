package sample.pages;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.components.BorrowingComponent;
import sample.dto.in.BorrowingDto;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;
import sample.utils.enums.Status;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@MenuItem(name = "Wyszukaj wypożyczenia")
@Component(resource = "/pages/find-borrowings-page.fxml")
public class FindBorrowingPage extends BaseComponent {

    @FXML
    private TextField email;

    @FXML
    ChoiceBox<Status> status;

    @FXML
    private VBox borrowingList;


    @Override
    public void init() {
        status.setItems(FXCollections.observableArrayList(Status.values()));
        status.setValue(Status.ALL);

        findBorrowingBy(null, null);
    }

    @FXML
    private void findBorrowings() {
        findBorrowingBy(email.getText(), status.getValue());
    }

    private void findBorrowingBy(String email, Status status) {
        borrowingService.getBorrowings(email, status).enqueue(new Callback<List<BorrowingDto>>() {
            @Override
            public void onResponse(Call<List<BorrowingDto>> call, Response<List<BorrowingDto>> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> {
                        if (response.body() != null) {
                            borrowingList.getChildren().setAll(response.body().stream().map(it -> createBorrowing(it)).filter(Objects::nonNull).collect(Collectors.toList()));
                        }
                    });
                } else {
                    AlertsFactory.responseStatusError(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<BorrowingDto>> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
            }
        });

    }


    private Pane createBorrowing(BorrowingDto borrowingDto) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(BorrowingComponent.class.getAnnotation(Component.class).resource()));

        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new BorrowingComponent.Props(borrowingDto.getEmail(), borrowingDto.getId(), borrowingDto.getDate(), borrowingDto.getReturnedDate(), borrowingDto.getStatus(), borrowingDto.getCopies()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }


}
