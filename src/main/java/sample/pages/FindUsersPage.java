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
import sample.components.UserComponent;
import sample.dto.in.UserDto;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component(resource = "/pages/find-users-page.fxml")
@MenuItem(name = "Użytkownicy")
public class FindUsersPage extends BaseComponent {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;

    @FXML
    private VBox usersList;


    @Override
    public void init() {
        super.init();
        findUsersBy(null, null, null);
    }

    @FXML
    private void findUsers() {
        findUsersBy(firstName.getText(), lastName.getText(), null);
    }

    @FXML
    private void findUserByEmail() {
        findUsersBy(null, null, email.getText());
    }

    private void findUsersBy(String firstName, String lastName, String email) {
        userService.getUsers(firstName, lastName, email).enqueue(new Callback<List<UserDto>>() {
            @Override
            public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> {
                        if (response.body() != null) {
                            usersList.getChildren().setAll(response.body().stream().map(it -> createUser(it)).filter(Objects::nonNull).collect(Collectors.toList()));
                        }
                    });
                } else {
                    AlertsFactory.responseStatusError(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<UserDto>> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
            }
        });
    }


    private Pane createUser(UserDto userDto) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(UserComponent.class.getAnnotation(Component.class).resource()));
        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new UserComponent.Props(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getRole(), userDto.getUserStatus()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
