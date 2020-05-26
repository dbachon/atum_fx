package sample.components;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;


@Component(resource = "/components/author-component.fxml")
public class AuthorComponent extends BaseComponent {

    public static class Props extends BaseProps {
        private String firstName;
        private String lastName;

        public Props(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;


    @Override
    public void init() {
        if (((Props) props).firstName != null) {
            this.firstName.setText(((Props) props).firstName);
        }
        if (((Props) props).lastName != null) {
            this.lastName.setText(((Props) props).lastName);
        }
    }
}
