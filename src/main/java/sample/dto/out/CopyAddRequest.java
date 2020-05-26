package sample.dto.out;

public class CopyAddRequest {
    private String code;
    private Long bookId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public CopyAddRequest() {
    }

    public CopyAddRequest(String code, Long bookId) {
        this.code = code;
        this.bookId = bookId;
    }
}
