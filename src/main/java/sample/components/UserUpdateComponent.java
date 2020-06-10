package sample.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.out.UserChangeSettingsRequest;
import sample.pages.FindUsersPage;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;
import sample.utils.enums.Role;
import sample.utils.enums.UserStatus;


@Component(resource = "/components/user-update-component.fxml")
public class UserUpdateComponent extends BaseComponent {

    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label email;
    @FXML
    private ToggleGroup role;
    @FXML
    private ToggleGroup status;
    @FXML
    private RadioButton BANNED;
    @FXML
    private RadioButton ACTIVE;
    @FXML
    private RadioButton READER;
    @FXML
    private RadioButton LIBRARIAN;
    @FXML
    private RadioButton ADMIN;


    public static class Props extends BaseProps {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private Role role;
        private UserStatus status;

        public Props(Long id,String firstName, String lastName, String email, Role role, UserStatus status) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.role = role;
            this.status = status;
        }


    }

    @Override
    public void init() {
        this.firstName.setText(((Props) props).firstName);
        this.lastName.setText(((Props) props).lastName);
        this.email.setText(((Props) props).email);

        switch (((Props) props).role){
            case READER:
                READER.setSelected(true);
                break;
            case LIBRARIAN:
                LIBRARIAN.setSelected(true);
                break;
            case ADMIN:
                ADMIN.setSelected(true);
                break;
            default:
                break;
        }

        switch (((Props) props).status){
            case ACTIVE:
                ACTIVE.setSelected(true);
                break;
            case BANNED:
                BANNED.setSelected(true);
                break;
            default:
                break;
        }



    }

    @FXML
    public void acceptChange(){
        UserChangeSettingsRequest userChangeSettingsRequest = new UserChangeSettingsRequest();

        userChangeSettingsRequest.setId(((Props)props).id);
        if(READER.isSelected()){
            userChangeSettingsRequest.setRole(Role.READER);
        } else if(LIBRARIAN.isSelected()){
            userChangeSettingsRequest.setRole(Role.LIBRARIAN);
        } else {
            userChangeSettingsRequest.setRole(Role.ADMIN);
        }

        if(ACTIVE.isSelected()){
            userChangeSettingsRequest.setUserStatus(UserStatus.ACTIVE);
        } else if(BANNED.isSelected()){
            userChangeSettingsRequest.setUserStatus(UserStatus.BANNED);
        }
        userService.changeUserSettings(userChangeSettingsRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("succes");
                router.accept(FindUsersPage.class, null);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {

            }
        });
        //    router.accept(FindUsersPage.class,null);
    }

    @FXML
    public void goBack(){
        router.accept(FindUsersPage.class, null);
    }

}
