package sample.dto.out;


import sample.utils.enums.Status;

public class BorrowingAcceptRequest {
    private Long id;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
