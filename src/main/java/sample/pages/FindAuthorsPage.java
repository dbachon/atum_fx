package sample.pages;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.components.AuthorComponent;
import sample.components.BookComponent;
import sample.dto.in.AuthorDto;
import sample.dto.in.BookDto;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@MenuItem(name = "Wyszukaj autora")
@Component(resource = "/pages/find-authors-page.fxml")
public class FindAuthorsPage extends BaseComponent {

    @FXML
    private TextField authorFilter;

    @FXML
    private VBox authorsList;


    @Override
    public void init() {
        super.init();


        authorService.getAuthors(null,null).enqueue(new Callback<List<AuthorDto>>() {
            @Override
            public void onResponse(Call<List<AuthorDto>> call, Response<List<AuthorDto>> response) {
                FilteredList<AuthorDto> filteredList = new FilteredList<AuthorDto>(FXCollections.observableArrayList(response.body()));
                Platform.runLater(() -> authorsList.getChildren().setAll(response.body().stream().map(it -> createAuthor(it)).filter(Objects::nonNull).collect(Collectors.toList())));
            }

            @Override
            public void onFailure(Call<List<AuthorDto>> call, Throwable throwable) {

            }
        });

    }


    @FXML
    private void findAuthors() {
        authorService.getAuthors(authorFilter.getText(),"").enqueue(new Callback<List<AuthorDto>>() {
            @Override
            public void onResponse(Call<List<AuthorDto>> call, Response<List<AuthorDto>> response) {
                Platform.runLater(() -> authorsList.getChildren().setAll(response.body().stream().map(it -> createAuthor(it)).filter(Objects::nonNull).collect(Collectors.toList())));

            }

            @Override
            public void onFailure(Call<List<AuthorDto>> call, Throwable throwable) {

            }

        });


    }


    private Pane createAuthor(AuthorDto authorDto) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(AuthorComponent.class.getAnnotation(Component.class).resource()));

        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new AuthorComponent.Props(authorDto.getFirstName(),authorDto.getLastName()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
