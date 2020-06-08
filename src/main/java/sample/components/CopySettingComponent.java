package sample.components;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.in.AuthorBookDto;
import sample.dto.in.BookDto;
import sample.dto.out.CopySettingsRequest;
import sample.pages.FindCopyPage;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;
import sample.utils.enums.Availability;

import java.util.List;


@Component(resource = "/components/copy-settings-component.fxml")
public class CopySettingComponent extends BaseComponent {

    @FXML
    ChoiceBox<Availability> availability;
    @FXML
    private TextField code;
    @FXML
    private ComboBox<BookDto> books;
    @FXML
    private TextField bookFilter;

    @Override
    public void init() {
        availability.setItems(FXCollections.observableArrayList(Availability.values()));

        bookFilter.textProperty().addListener((observable, oldValue, newValue) -> booksFilter());

        bookService.getAllBooks(null).enqueue(new Callback<List<BookDto>>() {
            @Override
            public void onResponse(Call<List<BookDto>> call, Response<List<BookDto>> response) {
                if (response.isSuccessful()) {
                    FilteredList<BookDto> filteredList = new FilteredList<>(FXCollections.observableArrayList(response.body()));
                    books.setItems(filteredList);
                } else {
                    AlertsFactory.responseStatusError(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<BookDto>> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
            }
        });

        code.setText(((Props) props).code);
        availability.setValue(((Props) props).availability);
        books.setValue(new BookDto(((Props) props).bookId, ((Props) props).title, ((Props) props).authors));

    }

    private void booksFilter() {
        bookService.getAllBooks(bookFilter.getText()).enqueue(new Callback<List<BookDto>>() {
            @Override
            public void onResponse(Call<List<BookDto>> call, Response<List<BookDto>> response) {
                FilteredList<BookDto> filteredList = new FilteredList<>(FXCollections.observableArrayList(response.body()));
                books.setItems(filteredList);
            }

            @Override
            public void onFailure(Call<List<BookDto>> call, Throwable throwable) {

            }

        });


    }

    @FXML
    public void acceptAdd() {

        copyService.changeCopySettings(new CopySettingsRequest(((Props) props).id, code.getText(), books.getValue().getId(), availability.getValue())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    AlertsFactory.success("Edycja egzemplarzu się powiodła");
                } else {
                    AlertsFactory.responseStatusError(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
            }
        });

    }

    public void cancel() {
        router.accept(FindCopyPage.class, null);
    }

    public static class Props extends BaseProps {
        private Long id;
        private String code;
        private Long bookId;
        private String title;
        private List<AuthorBookDto> authors;
        private Availability availability;

        public Props(Long id, String code, Long bookId, String title, List<AuthorBookDto> authors, Availability availability) {
            this.id = id;
            this.code = code;
            this.bookId = bookId;
            this.title = title;
            this.authors = authors;
            this.availability = availability;
        }
    }
}
