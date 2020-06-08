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
import sample.components.CopyFindComponent;
import sample.dto.in.CopyDto;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@MenuItem(name = "Wyszukaj egzemplarz")
@Component(resource = "/pages/find-copy-page.fxml")
public class FindCopyPage extends BaseComponent {

    @FXML
    VBox copiesList;
    @FXML
    private TextField title;
    @FXML
    private TextField code;
    private List<CopyDto> copyDtoList;

    @Override
    public void init() {
        super.init();
        AppState.getInstance().setBorrowingsAddCopies(new ArrayList<>());
        findAllCopiesBy(null, null);
    }

    private void initCopyList() {
        Platform.runLater(() -> copiesList.getChildren().setAll(copyDtoList.stream().map(this::createCopy).filter(Objects::nonNull).collect(Collectors.toList())));
    }

    @FXML
    private void findCopies() {
        findAllCopiesBy(title.getText(), null);

    }

    @FXML
    private void findCopiesCode() {
        findAllCopiesBy(null, code.getText());
    }


    private void findAllCopiesBy(String title, String code) {
        copyService.getAllCopy(title, code).enqueue(new Callback<List<CopyDto>>() {
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


    private Pane createCopy(CopyDto copyDto) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(CopyFindComponent.class.getAnnotation(Component.class).resource()));

        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new CopyFindComponent.Props(copyDto.getId(), copyDto.getCode(), copyDto.getBook().getId(), copyDto.getBook().getTitle(), copyDto.getBook().getAuthors(), copyDto.getAvailability()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}



