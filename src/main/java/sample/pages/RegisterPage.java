package sample.pages;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.AppState;
import sample.dto.in.AuthResponse;
import sample.dto.in.RegisterRequest;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;


@MenuItem(name = "Rejestracja")
@Component(resource = "/pages/register-page.fxml")
public class RegisterPage extends BaseComponent {


    @FXML
    private void toLoginPage() {
        router.accept(LoginPage.class, null);
    }

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label registerFalied;

    @FXML
    private void register(){

        authService.register(new RegisterRequest(firstName.getText(),lastName.getText(),email.getText(),password.getText())).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful()){
                    AppState.getInstance().setCredential(response.body());
                } else {

                    Platform.runLater(()->{
                        password.clear();
                        registerFalied.setText("Niepoprawne dane");
                    });
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {

            }
        });


    }

}
