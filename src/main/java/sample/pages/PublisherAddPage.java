package sample.pages;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.out.PublisherAddRequest;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;


@MenuItem(name = "Dodaj wydawnictwo")
@Component(resource = "/pages/add-publisher-page.fxml")
public class PublisherAddPage extends BaseComponent {

    @FXML
    private TextField name;
    @FXML
    private TextField telephone;
    @FXML
    private TextField email;


    public void acceptAdd(){
            publisherService.addPublisher(new PublisherAddRequest(name.getText(),telephone.getText(),email.getText())).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    AlertsFactory.success("Wydawca został dodany");
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

    public void cancel(){
        router.accept(PublisherAddPage.class,null);
    }
}