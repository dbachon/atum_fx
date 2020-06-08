package sample.dto.out;


import sample.utils.enums.Availability;

public class CopySettingsRequest {
    private Long id;
    private String code;
    private Long bookId;
    private Availability availability;

    public CopySettingsRequest(Long id, String code, Long bookId, Availability availability) {
        this.id = id;
        this.code = code;
        this.bookId = bookId;
        this.availability = availability;
    }

    public CopySettingsRequest() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
