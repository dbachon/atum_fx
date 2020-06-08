package sample.pages;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.out.AuthorAddRequest;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

@MenuItem(name = "Dodaj autora")
@Component(resource = "/pages/add-author-page.fxml")
public class AuthorAddPage extends BaseComponent {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;



    public void acceptAdd(){
        authorService.addAuthor(new AuthorAddRequest(firstName.getText(), lastName.getText())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    AlertsFactory.success("Autor został dodany");
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

    public void cancel(){
        router.accept(AuthorAddPage.class,null);
    }
}