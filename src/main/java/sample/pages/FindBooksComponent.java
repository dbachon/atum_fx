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
import sample.components.BookComponent;
import sample.dto.in.BookDto;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@MenuItem(name = "Wyszukaj książkę")
@Component(resource = "/pages/find-book-page.fxml")
public class FindBooksComponent extends BaseComponent {

    @FXML
    private TextField title;

    @FXML
    private VBox booksList;


    @Override
    public void init() {
        super.init();
        bookService.getAllBooks(null).enqueue(new Callback<List<BookDto>>() {
            @Override
            public void onResponse(Call<List<BookDto>> call, Response<List<BookDto>> response) {

                Platform.runLater(() -> booksList.getChildren().setAll(response.body().stream().map(it -> createBook(it)).filter(Objects::nonNull).collect(Collectors.toList())));
            }

            @Override
            public void onFailure(Call<List<BookDto>> call, Throwable throwable) {

            }
        });
    }


        @FXML
        private void findBooks() {

            bookService.getAllBooks(title.getText()).enqueue(new Callback<List<BookDto>>() {
                @Override
                public void onResponse(Call<List<BookDto>> call, Response<List<BookDto>> response) {
                    Platform.runLater(() -> booksList.getChildren().setAll(response.body().stream().map(it -> createBook(it)).filter(Objects::nonNull).collect(Collectors.toList())));

                }

                @Override
                public void onFailure(Call<List<BookDto>> call, Throwable throwable) {

                }
            });

    }


    private Pane createBook(BookDto bookDto) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(BookComponent.class.getAnnotation(Component.class).resource()));

        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new BookComponent.Props(bookDto.getTitle(),bookDto.getAuthors(),bookDto.getPublisher(),bookDto.getGenre()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
