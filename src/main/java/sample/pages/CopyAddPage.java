package sample.pages;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.in.BookDto;
import sample.dto.out.CopyAddRequest;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

import java.util.List;


@MenuItem(name = "Dodaj egzeplarz")
@Component(resource = "/pages/add-copy-component.fxml")
public class CopyAddPage extends BaseComponent {

    @FXML
    private TextField code;

    @FXML
    private ComboBox<BookDto> books;
    @FXML
    private TextField bookFilter;

    @Override
    public void init() {

        bookFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            booksFilter();
        });

        bookService.getAllBooks(null).enqueue(new Callback<List<BookDto>>() {
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


    public void booksFilter() {
        bookService.getAllBooks(bookFilter.getText()).enqueue(new Callback<List<BookDto>>() {
            @Override
            public void onResponse(Call<List<BookDto>> call, Response<List<BookDto>> response) {
                FilteredList<BookDto> filteredList = new FilteredList<BookDto>(FXCollections.observableArrayList(response.body()));
                books.setItems(filteredList);
            }

            @Override
            public void onFailure(Call<List<BookDto>> call, Throwable throwable) {

            }

        });


    }


    @FXML
    public void acceptAdd() {



            copyService.addCopy(new CopyAddRequest(code.getText(),books.getValue().getId())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {

            }
        });

    }


    public void cancel(){
        router.accept(CopyAddPage.class,null);
    }
}
