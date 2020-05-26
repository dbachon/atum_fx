package sample.components;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.dto.in.CopyDto;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;
import sample.utils.enums.Status;

import java.util.Date;
import java.util.List;


@Component(resource = "/components/returned-borrowing-component.fxml")
public class ReturnedBorrowingComponent extends BaseComponent {

    public static class Props extends BaseProps {
        private String email;
        private Long id;
        private Date date;
        private Date returnedDate;
        private Status status;
        private List<CopyDto> copies;

        public Props(String email,Long id, Date date, Date returnedDate, Status status, List<CopyDto> copies) {
            this.email = email;
            this.id = id;
            this.date = date;
            this.returnedDate = returnedDate;
            this.status = status;
            this.copies = copies;

        }
    }

    @FXML
    private Label email;

    @FXML
    private Label date;

    @FXML
    private Label returnedDate;

    @FXML
    private Label status;


    @Override
    public void init() {
        this.email.setText(((Props)props).email);
        this.date.setText(((Props)props).date.toString());
        if(((Props)props).returnedDate == null) {
            this.returnedDate.setText("-----------------------");
        } else {
            this.returnedDate.setText(((Props) props).returnedDate.toString());
        }

        this.status.setText(((Props)props).status.name());
    }

    @FXML
    public void moreSettings(){
        router.accept(BorrowingReturnComponent.class,new BorrowingReturnComponent.Props(((Props) props).email,((Props) props).id, ((Props) props).date, ((Props) props).returnedDate,  ((Props) props).status, ((Props) props).copies));
    }
}
