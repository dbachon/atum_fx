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
import sample.dto.in.LoginRequest;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;



@Component(resource = "/pages/login-page.fxml")
@MenuItem(name = "Logowanie")
public class LoginPage extends BaseComponent {
    @FXML
    private void goToRegister() {
        router.accept(RegisterPage.class, null);
    }

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginFailed;




    @FXML
    private void login(){

        authService.login(new LoginRequest(email.getText(),password.getText())).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful()){
                    AppState.getInstance().setCredential(response.body());
                    loginFailed.setText("");
                    router.accept(MyAccountPage.class,null);
                } else {
                    Platform.runLater(()->{
                        password.clear();
                        loginFailed.setText("Błędny login lub hasło");
                        AlertsFactory.responseStatusError(response.errorBody());
                    });
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
            }
        });


    }
}
