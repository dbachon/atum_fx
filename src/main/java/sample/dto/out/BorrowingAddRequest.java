package sample.dto.out;

import java.util.List;

public class BorrowingAddRequest{
    private List<Long> copiesId;


    public List<Long> getCopiesId() {
        return copiesId;
    }

    public void setCopiesId(List<Long> copiesId) {
        this.copiesId = copiesId;
    }

    public BorrowingAddRequest(List<Long> copiesId) {
        this.copiesId = copiesId;
    }

    public BorrowingAddRequest() {
    }
}
