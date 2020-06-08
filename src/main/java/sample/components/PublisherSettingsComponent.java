package sample.components;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.out.PublisherSettingsRequest;
import sample.pages.FindPublishersPage;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;


@Component(resource = "/components/publisher-settings-component.fxml")
public class PublisherSettingsComponent extends BaseComponent {

    @FXML
    private TextField name;
    @FXML
    private TextField telephone;
    @FXML
    private TextField email;

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

    public void acceptAdd() {
        publisherService.changePublisherSettings(new PublisherSettingsRequest(((Props) props).id, name.getText(), telephone.getText(), email.getText())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    AlertsFactory.success("Edycja wydawnictwa powiodła się.");
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
        router.accept(FindPublishersPage.class, null);
    }

    public static class Props extends BaseProps {
        private Long id;
        private String name;
        private String telephone;
        private String email;

        public Props(Long id, String name, String telephone, String email) {
            this.id = id;
            this.name = name;
            this.telephone = telephone;
            this.email = email;
        }
    }
}