package sample.pages;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.in.AuthorDto;
import sample.dto.in.PublisherBookDto;
import sample.dto.out.BookAddRequest;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;
import sample.utils.enums.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@MenuItem(name = "Dodaj książkę")
@Component(resource = "/pages/add-book-component.fxml")
public class BookAddPage extends BaseComponent {

    @FXML
    private TextField title;
    @FXML
    private ChoiceBox<Genre> genre;
    @FXML
    private ComboBox<AuthorDto> authors;
    @FXML
    private ComboBox<PublisherBookDto> publisher;
    @FXML
    private TextField authorFilter;
    @FXML
    private ListView<AuthorDto> authorsList;

    private List<AuthorDto> authorsAddList = new ArrayList<>();

    @Override
    public void init() {
        genre.setItems(FXCollections.observableArrayList(Genre.values()));
        authorFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            authorsFilter();
        });

        authorService.getAuthors(null, null).enqueue(new Callback<List<AuthorDto>>() {
            @Override
            public void onResponse(Call<List<AuthorDto>> call, Response<List<AuthorDto>> response) {
                FilteredList<AuthorDto> filteredList = new FilteredList<AuthorDto>(FXCollections.observableArrayList(response.body()));
                authors.setItems(filteredList);
            }

            @Override
            public void onFailure(Call<List<AuthorDto>> call, Throwable throwable) {

            }

        });


        publisherService.getPublishers(null).enqueue(new Callback<List<PublisherBookDto>>() {
            @Override
            public void onResponse(Call<List<PublisherBookDto>> call, Response<List<PublisherBookDto>> response) {
                FilteredList<PublisherBookDto> filteredList = new FilteredList<PublisherBookDto>(FXCollections.observableArrayList(response.body()));
                publisher.setItems(filteredList);

            }

            @Override
            public void onFailure(Call<List<PublisherBookDto>> call, Throwable throwable) {

            }

        });


    }


    public void authorsFilter() {
        authorService.getAuthors(authorFilter.getText(), "").enqueue(new Callback<List<AuthorDto>>() {
            @Override
            public void onResponse(Call<List<AuthorDto>> call, Response<List<AuthorDto>> response) {
                FilteredList<AuthorDto> filteredList = new FilteredList<AuthorDto>(FXCollections.observableArrayList(response.body()));
                authors.setItems(filteredList);
            }

            @Override
            public void onFailure(Call<List<AuthorDto>> call, Throwable throwable) {

            }

        });


    }


    @FXML
    public void choiceAuthor() {
       if (!authorsList.getItems().contains(authors.getValue())) {
            authorsAddList.add(authors.getValue());
            authorsList.setItems(FXCollections.observableArrayList(authorsAddList));
       }

    }


    @FXML
    public void acceptAdd() {
        BookAddRequest bookAddRequest = new BookAddRequest(title.getText(), genre.getValue(), authorsList.getItems().stream().map(AuthorDto::getId).collect(Collectors.toList()), publisher.getValue().getId());

        bookService.addBook(bookAddRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {

            }
        });

    }


    @FXML void deleteAuthor(){
        authorsList.getItems().remove(authorsList.getSelectionModel().getSelectedItem());
        authorsAddList.remove(authorsList.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void cancel() {
        router.accept(BookAddPage.class, null);
    }
}
