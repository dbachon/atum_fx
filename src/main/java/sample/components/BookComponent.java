package sample.components;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.dto.in.AuthorBookDto;
import sample.dto.in.PublisherBookDto;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;
import sample.utils.enums.Genre;

import java.util.List;
import java.util.stream.Collectors;


@Component(resource = "/components/book-component.fxml")
public class BookComponent extends BaseComponent {

    public static class Props extends BaseProps {
        private String title;
        private List<AuthorBookDto> authors;
        private PublisherBookDto publisher;
        private Genre genre;

        public Props(String title, List<AuthorBookDto> authors, PublisherBookDto publisher, Genre genre) {
            this.title = title;
            this.authors = authors;
            this.publisher = publisher;
            this.genre = genre;
        }
    }

    @FXML
    private Label title;

    @FXML
    private Label authors;

    @FXML
    private Label publisher;

    @FXML
    private Label genre;


    @Override
    public void init() {
        //router.accept(LoginPage.class, null);
        this.title.setText(((Props) props).title);
        this.authors.setText(((Props)props).authors.stream().map(AuthorBookDto::toString).collect(Collectors.joining(", ")));
        this.publisher.setText(((Props)props).publisher.getName());
        this.genre.setText(((Props)props).genre.name());
    }
}
