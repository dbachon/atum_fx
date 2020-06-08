package sample.pages;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.AppState;
import sample.dto.in.UserDto;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;


@Component(resource = "/pages/my-account-page.fxml")
@MenuItem(name = "Moje Konto")
public class MyAccountPage extends BaseComponent {

    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label email;
    @FXML
    private Label role;
    @FXML
    private Label status;


    @Override
    public void init() {
        super.init();
        userService.getUser(AppState.getInstance().getToken()).enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                Platform.runLater(() -> {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            firstName.setText(" " + response.body().getFirstName());
                            lastName.setText(" " + response.body().getLastName());
                            email.setText(" " + response.body().getEmail());
                            role.setText(" " + response.body().getRole().name());
                            status.setText(" " + response.body().getUserStatus().name());
                        }
                    } else {
                        AlertsFactory.responseStatusError(response.errorBody());
                    }
                });
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
            }
        });
    }

    @FXML
    public void logOut() {
        AppState.getInstance().logOut();
        router.accept(LoginPage.class, null);
    }

}

