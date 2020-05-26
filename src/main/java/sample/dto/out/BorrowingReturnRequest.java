package sample.dto.out;

public class BorrowingReturnRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BorrowingReturnRequest() {
    }

    public BorrowingReturnRequest(Long id) {
        this.id = id;
    }
}
