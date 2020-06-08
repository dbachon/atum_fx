package sample.components;

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
import sample.dto.in.AuthorBookDto;
import sample.dto.in.AuthorDto;
import sample.dto.in.PublisherBookDto;
import sample.dto.out.BookSettingsRequest;
import sample.pages.FindBooksPage;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;
import sample.utils.enums.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component(resource = "/components/book-settings-component.fxml")
public class BookSettingsComponent extends BaseComponent {

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
        authorFilter.textProperty().addListener((observable, oldValue, newValue) -> authorsFilter());

        findAuthorsBy(null, null);


        publisherService.getPublishers(null).enqueue(new Callback<List<PublisherBookDto>>() {
            @Override
            public void onResponse(Call<List<PublisherBookDto>> call, Response<List<PublisherBookDto>> response) {
                if (response.isSuccessful()) {
                    FilteredList<PublisherBookDto> filteredList = new FilteredList<>(FXCollections.observableArrayList(response.body()));
                    publisher.setItems(filteredList);
                } else {
                    AlertsFactory.responseStatusError(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<PublisherBookDto>> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
            }
        });

        genre.setValue(((Props) props).genre);
        authorsAddList = ((Props) props).authors.stream().map(AuthorDto::new).collect(Collectors.toList());
        publisher.setValue(((Props) props).publisher);
        title.setText(((Props) props).title);
        authorsList.setItems(FXCollections.observableArrayList(authorsAddList));

    }

    private void authorsFilter() {
        findAuthorsBy(authorFilter.getText(), "");
    }

    private void findAuthorsBy(String names, String secondName) {
        authorService.getAuthors(names, secondName).enqueue(new Callback<List<AuthorDto>>() {
            @Override
            public void onResponse(Call<List<AuthorDto>> call, Response<List<AuthorDto>> response) {
                if (response.isSuccessful()) {
                    FilteredList<AuthorDto> filteredList = new FilteredList<>(FXCollections.observableArrayList(response.body()));
                    authors.setItems(filteredList);
                } else {
                    AlertsFactory.responseStatusError(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<AuthorDto>> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
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
        BookSettingsRequest bookSettingsRequest = new BookSettingsRequest(((Props) props).id, title.getText(), genre.getValue(), authorsList.getItems().stream().map(AuthorDto::getId).collect(Collectors.toList()), publisher.getValue().getId());

        bookService.changeBookSettings(bookSettingsRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                {
                    if (response.isSuccessful()) {
                        AlertsFactory.success("Edycja książki się powiodła");
                    } else {
                        AlertsFactory.responseStatusError(response.errorBody());
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
            }
        });

    }

    @FXML
    void deleteAuthor() {
        authorsList.getItems().remove(authorsList.getSelectionModel().getSelectedItem());
        authorsAddList.remove(authorsList.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void cancel() {
        router.accept(FindBooksPage.class, null);
    }

    public static class Props extends BaseProps {
        private Long id;
        private String title;
        private List<AuthorBookDto> authors;
        private PublisherBookDto publisher;
        private Genre genre;

        public Props(Long id, String title, List<AuthorBookDto> authors, PublisherBookDto publisher, Genre genre) {
            this.id = id;
            this.title = title;
            this.authors = authors;
            this.publisher = publisher;
            this.genre = genre;
        }
    }
}
