package sample.components;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.dto.in.CopyDto;
import sample.dto.out.BorrowingReturnRequest;
import sample.pages.MyBorrowingPage;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;
import sample.utils.enums.Status;

import java.util.Date;
import java.util.List;


@Component(resource = "/components/borrowing-return-component.fxml")
public class BorrowingReturnComponent extends BaseComponent {

    @FXML
    private Label date;
    @FXML
    private Label returnedDate;
    @FXML
    private Label email;
    @FXML
    private Label status;
    @FXML
    ListView<CopyDto> copiesList;

    @FXML
    private ToggleGroup borrowingStatus;
    @FXML
    private RadioButton WAITING;
    @FXML
    private RadioButton none;

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

    @Override
    public void init() {
        this.email.setText(((Props) props).email);
        this.date.setText(((Props)props).date.toString());
        if(((Props)props).returnedDate == null) {
            this.returnedDate.setText("-----------------------");
        } else {
            this.returnedDate.setText(((Props) props).returnedDate.toString());
        }
        this.status.setText(((Props)props).status.name());

        copiesList.setItems(FXCollections.observableArrayList(((Props)props).copies));

        switch (((Props) props).status){
            case WAITING:
                WAITING.setSelected(true);
                break;
            default:
                break;
        }
    }

    @FXML
    public void acceptReturn(){
        BorrowingReturnRequest borrowingReturnRequest = new BorrowingReturnRequest();
        borrowingReturnRequest.setId(((Props)props).id);
        if(WAITING.isSelected()){


            borrowingService.returnBorrowing(borrowingReturnRequest).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    router.accept(MyBorrowingPage.class,null);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable throwable) {

                }
            });

        }




    }

    @FXML
    public void goBack(){
        router.accept(MyBorrowingPage.class,null);
    }

}
