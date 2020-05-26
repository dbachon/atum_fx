package sample.pages;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.AppState;
import sample.components.CopyBorrowingComponent;
import sample.components.CopyComponent;
import sample.dto.in.CopyDto;
import sample.dto.out.BorrowingAddRequest;
import sample.utils.BaseComponent;
import sample.utils.BorrowingCopy;
import sample.utils.Component;
import sample.utils.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@MenuItem(name = "Wypożycz książki")
@Component(resource = "/pages/create-borrowing-page.fxml")
public class BorrowingBooksPage extends BaseComponent {

    @FXML
    private TextField title;

    @FXML
    VBox copiesList;

    @FXML
    VBox borrowingCopiesList;

    private List<CopyDto> copyDtoList;

    @Override
    public void init() {
        super.init();
        AppState.getInstance().setBorrowingsAddCopies(new ArrayList<>());
        copyService.getCopy(null).enqueue(new Callback<List<CopyDto>>() {
            @Override
            public void onResponse(Call<List<CopyDto>> call, Response<List<CopyDto>> response) {
                copyDtoList = response.body();
                initCopyList();
            }

            @Override
            public void onFailure(Call<List<CopyDto>> call, Throwable throwable) {

            }
        });

        AppState.getInstance().isBorrowingAddProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            borrowingCopiesList.getChildren().setAll(AppState.getInstance().getBorrowingsAddCopies().stream().map(it -> createAddCopy(it)).filter(Objects::nonNull).collect(Collectors.toList()));
            initCopyList();
        }));

    }

    private void initCopyList(){
        Platform.runLater(() -> copiesList.getChildren().setAll(copyDtoList.stream().map(it -> createCopy(it)).filter(Objects::nonNull).collect(Collectors.toList())));
    }

    @FXML
    private void findCopies() {
        copyService.getCopy(title.getText()).enqueue(new Callback<List<CopyDto>>() {
            @Override
            public void onResponse(Call<List<CopyDto>> call, Response<List<CopyDto>> response) {
                copyDtoList = response.body();
                initCopyList();

            }

            @Override
            public void onFailure(Call<List<CopyDto>> call, Throwable throwable) {

            }
        });

    }

    @FXML
    private void acceptBorrowing(){
        borrowingService.createBorrowing(AppState.getInstance().getToken(),
                new BorrowingAddRequest( AppState.getInstance().getBorrowingsAddCopies().stream().map(e -> e.getCopyId()).collect(Collectors.toList()) )).
                enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    router.accept(MyBorrowingPage.class,null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {

            }
        });
    }


    private Pane createCopy(CopyDto copyDto) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(CopyComponent.class.getAnnotation(Component.class).resource()));

        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new CopyComponent.Props(copyDto.getId(),copyDto.getCode(),copyDto.getBook().getTitle(),copyDto.getBook().getAuthors(),copyDto.getAvailability()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Pane createAddCopy(BorrowingCopy borrowingCopy) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(CopyBorrowingComponent.class.getAnnotation(Component.class).resource()));

        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new CopyBorrowingComponent.Props(borrowingCopy.getCopyId(),borrowingCopy.getCode(),borrowingCopy.getTitle(),borrowingCopy.getAuthors(),borrowingCopy.getAvailability()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
