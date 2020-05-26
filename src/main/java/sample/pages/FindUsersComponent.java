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
import sample.components.BookComponent;
import sample.components.UserComponent;
import sample.dto.in.BookDto;
import sample.dto.in.UserDto;
import sample.services.UserService;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component(resource = "/pages/find-users-page.fxml")
@MenuItem(name = "Użytkownicy")
public class FindUsersComponent extends BaseComponent {
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
        userService.getUsers(null, null,null).enqueue(new Callback<List<UserDto>>() {
            @Override
            public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response) {
                Platform.runLater(() -> usersList.getChildren().setAll(response.body().stream().map(it -> createUser(it)).filter(Objects::nonNull).collect(Collectors.toList())));
            }

            @Override
            public void onFailure(Call<List<UserDto>> call, Throwable throwable) {

            }
        });
    }


    @FXML
    private void findUsers() {

        userService.getUsers(firstName.getText(), lastName.getText(),null).enqueue(new Callback<List<UserDto>>() {
            @Override
            public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response) {
                Platform.runLater(() -> usersList.getChildren().setAll(response.body().stream().map(it -> createUser(it)).filter(Objects::nonNull).collect(Collectors.toList())));

            }

            @Override
            public void onFailure(Call<List<UserDto>> call, Throwable throwable) {

            }
        });

    }

    @FXML
    private void findUserByEmail() {

        userService.getUsers(null, null,email.getText()).enqueue(new Callback<List<UserDto>>() {
            @Override
            public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response) {
                Platform.runLater(() -> usersList.getChildren().setAll(response.body().stream().map(it -> createUser(it)).filter(Objects::nonNull).collect(Collectors.toList())));

            }

            @Override
            public void onFailure(Call<List<UserDto>> call, Throwable throwable) {

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
