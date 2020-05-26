package sample.dto.in;

import sample.utils.enums.Status;
import java.util.Date;
import java.util.List;

public class BorrowingDto {
    private String email;
    private Long id;
    private Date date;
    private Date returnedDate;
    private Status status;
    private List<CopyDto> copies;

    public BorrowingDto() {
    }


    public BorrowingDto(String email ,Long id, Date date, Date returnedDate, Status status, List<CopyDto> copies) {
        this.email = email;
        this.id = id;
        this.date = date;
        this.returnedDate = returnedDate;
        this.status = status;
        this.copies = copies;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<CopyDto> getCopies() {
        return copies;
    }

    public void setCopies(List<CopyDto> copies) {
        this.copies = copies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
