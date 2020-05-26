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
import sample.AppState;

import sample.components.ReturnedBorrowingComponent;
import sample.dto.in.BorrowingDto;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

import sample.utils.enums.Status;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@MenuItem(name = "Moje wypożyczenia")
@Component(resource = "/pages/my-borrowing-page.fxml")
public class MyBorrowingPage extends BaseComponent {

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
        borrowingService.getMyBorrowings(AppState.getInstance().getToken(),Status.ALL).enqueue(new Callback<List<BorrowingDto>>() {
            @Override
            public void onResponse(Call<List<BorrowingDto>> call, Response<List<BorrowingDto>> response) {

                if(response.isSuccessful()){
                    Platform.runLater(() -> borrowingList.getChildren().setAll(response.body().stream().map(it -> createBorrowing(it)).filter(Objects::nonNull).collect(Collectors.toList())));

                }
            }
            @Override
            public void onFailure(Call<List<BorrowingDto>> call, Throwable throwable) {

            }
        });

    }


    @FXML
    private void findBorrowings() {
        borrowingService.getMyBorrowings(AppState.getInstance().getToken(),status.getValue()).enqueue(new Callback<List<BorrowingDto>>() {
            @Override
            public void onResponse(Call<List<BorrowingDto>> call, Response<List<BorrowingDto>> response) {
                Platform.runLater(() -> borrowingList.getChildren().setAll(response.body().stream().map(it -> createBorrowing(it)).filter(Objects::nonNull).collect(Collectors.toList())));
            }
            @Override
            public void onFailure(Call<List<BorrowingDto>> call, Throwable throwable) {

            }
        });

    }


    private Pane createBorrowing(BorrowingDto borrowingDto) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(ReturnedBorrowingComponent.class.getAnnotation(Component.class).resource()));

        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new ReturnedBorrowingComponent.Props(borrowingDto.getEmail(),borrowingDto.getId(),borrowingDto.getDate(),borrowingDto.getReturnedDate(),borrowingDto.getStatus(),borrowingDto.getCopies()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }


}
