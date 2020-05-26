package sample.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;
import sample.utils.enums.Role;
import sample.utils.enums.UserStatus;


@Component(resource = "/components/user-component.fxml")
public class UserComponent extends BaseComponent {

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
        this.role.setText(((Props) props).role.name());
        this.status.setText(((Props) props).status.name());
    }

    @FXML
    public void userUpdate(){
        router.accept(UserUpdateComponent.class,new UserUpdateComponent.Props(((Props) props).id, ((Props) props).firstName, ((Props) props).lastName, ((Props) props).email, ((Props) props).role, ((Props) props).status));
    }
}


