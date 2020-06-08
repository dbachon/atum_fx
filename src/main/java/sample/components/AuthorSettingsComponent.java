package sample.components;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.out.AuthorSettingRequest;
import sample.pages.FindAuthorsPage;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;


@Component(resource = "/components/author-settings-component.fxml")
public class AuthorSettingsComponent extends BaseComponent {
    @FXML
    private TextField firstName;
    @FXML

    private TextField lastName;

    @Override
    public void init() {
        if (((Props) props).firstName != null) {
            this.firstName.setText(((Props) props).firstName);
        }
        if (((Props) props).lastName != null) {
            this.lastName.setText(((Props) props).lastName);
        }
    }

    public void acceptAdd() {
        authorService.changeAuthorSettings(new AuthorSettingRequest(((Props) props).id, firstName.getText(), lastName.getText())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    AlertsFactory.success("Edycja autora się powiodłą");
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

    public void cancel() {
        router.accept(FindAuthorsPage.class, null);
    }

    public static class Props extends BaseProps {
        private Long id;
        private String firstName;
        private String lastName;

        public Props(Long id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}