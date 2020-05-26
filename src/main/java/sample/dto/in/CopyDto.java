package sample.dto.in;

import sample.utils.enums.Availability;
public class CopyDto {
    private Long id;
    private String code;
    private Availability availability;
    private BookDto book;

    public CopyDto() {
    }

    public CopyDto(Long id, String code, Availability availability, BookDto book) {
        this.id = id;
        this.code = code;
        this.availability = availability;
        this.book = book;
    }


    @Override
    public String toString() {
        return "Kod: " + code + "   Tytuł:    "+ book;


    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
