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

        publisherService.getPublishers(null).enqueue(new Callback<List<PublisherBookDto>>() {
            @Override
            public void onResponse(Call<List<PublisherBookDto>> call, Response<List<PublisherBookDto>> response) {
                Platform.runLater(() -> publishersList.getChildren().setAll(response.body().stream().map(it -> createPublisher(it)).filter(Objects::nonNull).collect(Collectors.toList())));
            }

            @Override
            public void onFailure(Call<List<PublisherBookDto>> call, Throwable throwable) {

            }

        });

    }


    @FXML
    private void findPublishers() {



         publisherService.getPublishers(publisherFilter.getText()).enqueue(new Callback<List<PublisherBookDto>>() {
                @Override
                public void onResponse(Call<List<PublisherBookDto>> call, Response<List<PublisherBookDto>> response) {
                    Platform.runLater(() -> publishersList.getChildren().setAll(response.body().stream().map(it -> createPublisher(it)).filter(Objects::nonNull).collect(Collectors.toList())));
                }

                @Override
                public void onFailure(Call<List<PublisherBookDto>> call, Throwable throwable) {

                }

            });



        }


    private Pane createPublisher(PublisherBookDto publisherBookDto) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PublisherComponent.class.getAnnotation(Component.class).resource()));

        try {
            Pane pane = loader.load();
            BaseComponent controller = loader.getController();
            controller.setRouter(router);
            controller.setProps(new PublisherComponent.Props(publisherBookDto.getName(),publisherBookDto.getTelephone(),publisherBookDto.getEmail()));
            controller.init();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
