package sample.components;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.AppState;
import sample.dto.in.AuthorBookDto;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.BorrowingCopy;
import sample.utils.Component;
import sample.utils.enums.Availability;

import java.util.List;
import java.util.stream.Collectors;


@Component(resource = "/components/copy-component.fxml")
public class CopyComponent extends BaseComponent {

    public static class Props extends BaseProps {
        private Long id;
        private String code;
        private String title;
        private List<AuthorBookDto> authors;
        private Availability availability;

        public Props(Long id, String code, String title, List<AuthorBookDto> authors, Availability availability) {
            this.id = id;
            this.code = code;
            this.title = title;
            this.authors = authors;
            this.availability = availability;
        }
    }

    @FXML
    private Label code;

    @FXML
    private Label title;

    @FXML
    private Label authors;

    @FXML
    private Label availability;

    @FXML
    private Button addButton;

    @Override
    public void init() {
        this.title.setText(((Props) props).title);
        this.authors.setText(((Props)props).authors.stream().map(AuthorBookDto::toString).collect(Collectors.joining(", ")));
        this.code.setText(((Props)props).code);
        this.availability.setText(((Props)props).availability.name());
        if(AppState.getInstance().getBorrowingsAddCopies().contains(
                new BorrowingCopy(((Props)props).id,((Props) props).code,((Props) props).title,((Props) props).authors,((Props) props).availability)) || ((Props)props).availability == Availability.UNAVAILABLE){
            addButton.setDisable(true);
        } else {
            addButton.setDisable(false);
        }
    }

    @FXML
    public void add(){
        addButton.setDisable(true);
        AppState.getInstance().addBorrwoingCopies(new BorrowingCopy(((Props)props).id,((Props) props).code,((Props) props).title,((Props) props).authors,((Props) props).availability ));
    }
}
