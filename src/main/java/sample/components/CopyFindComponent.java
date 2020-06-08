package sample.components;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.dto.in.AuthorBookDto;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;
import sample.utils.enums.Availability;

import java.util.List;
import java.util.stream.Collectors;


@Component(resource = "/components/copy-find-component.fxml")
public class CopyFindComponent extends BaseComponent {

    @FXML
    private Label code;
    @FXML
    private Label title;
    @FXML
    private Label authors;
    @FXML
    private Label availability;

    @Override
    public void init() {
        this.title.setText(((Props) props).title);
        this.authors.setText(((Props) props).authors.stream()
                .map(AuthorBookDto::toString).collect(Collectors.joining(", ")));
        this.code.setText(((Props) props).code);
        this.availability.setText(((Props) props).availability.name());
    }

    @FXML
    public void moreSettings() {
        router.accept(CopySettingComponent.class, new CopySettingComponent.Props(((Props) props).id,
                ((Props) props).code, ((Props) props).bookId, ((Props) props).title, ((Props) props).authors, ((Props) props).availability));

    }

    public static class Props extends BaseProps {
        private Long id;
        private String code;
        private Long bookId;
        private String title;
        private List<AuthorBookDto> authors;
        private Availability availability;

        public Props(Long id, String code, Long bookId, String title, List<AuthorBookDto> authors, Availability availability) {
            this.id = id;
            this.code = code;
            this.bookId = bookId;
            this.title = title;
            this.authors = authors;
            this.availability = availability;
        }
    }
}
