package sample.pages;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.out.AuthorAddRequest;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

@MenuItem(name = "Dodaj autora")
@Component(resource = "/pages/add-author-component.fxml")
public class AuthorAddPage extends BaseComponent {
    @FXML
    private TextField fristName;
    @FXML
    private TextField lastName;



    public void acceptAdd(){
        authorService.addAuthor(new AuthorAddRequest(fristName.getText(),lastName.getText())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {

            }
        });
    }

    public void cancel(){
        router.accept(AuthorAddPage.class,null);
    }
}