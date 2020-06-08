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
import sample.components.PublisherComponent;
import sample.dto.in.PublisherBookDto;
import sample.utils.AlertsFactory;
import sample.utils.BaseComponent;
import sample.utils.Component;
import sample.utils.MenuItem;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@MenuItem(name = "Wyszukaj wydawnictwo")
@Component(resource = "/pages/find-publishers-page.fxml")
public class FindPublishersPage extends BaseComponent {

    @FXML
    private TextField publisherFilter;

    @FXML
    private VBox publishersList;


    @Override
    public void init() {
        super.init();
        findPublisherBy(null);
    }


    @FXML
    private void findPublishers() {
        findPublisherBy(publisherFilter.getText());
    }


    private void findPublisherBy(String name) {
        publisherService.getPublishers(name).enqueue(new Callback<List<PublisherBookDto>>() {
            @Override
            public void onResponse(Call<List<PublisherBookDto>> call, Response<List<PublisherBookDto>> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> {
                        if (response.body() != null) {
                            publishersList.getChildren().setAll(response.body().stream().map(it -> createPublisher(it)).filter(Objects::nonNull).collect(Collectors.toList()));
                        }
                    });
                } else {
                    AlertsFactory.responseStatusError(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<PublisherBookDto>> call, Throwable throwable) {
                AlertsFactory.apiCallError(throwable);
            }
        });

    }


    private Pane createPublisher(PublisherBookDto publisherBookDto) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PublisherComponent.class.getAnnotation(Component.class).resource()));

        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new PublisherComponent.Props(publisherBookDto.getId(), publisherBookDto.getName(), publisherBookDto.getTelephone(), publisherBookDto.getEmail()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
