package sample.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;


@Component(resource = "/components/publisher-component.fxml")
public class PublisherComponent extends BaseComponent {

    public static class Props extends BaseProps {
        private String name;
        private String telephone;
        private String email;

        public Props(String name, String telephone, String email) {
            this.name = name;
            this.telephone = telephone;
            this.email = email;
        }
    }


    @FXML
    private Label name;
    @FXML
    private Label telephone;
    @FXML
    private Label email;


    @Override
    public void init() {
        if (((Props) props).name != null) {
            this.name.setText(((Props) props).name);
        }
        if (((Props) props).telephone != null) {
            this.telephone.setText(((Props) props).telephone);
        }
        if (((Props) props).email != null) {
            this.email.setText(((Props) props).email);
        }
    }
}
