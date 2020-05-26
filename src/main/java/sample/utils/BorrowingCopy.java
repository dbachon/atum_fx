package sample.utils;

import sample.dto.in.AuthorBookDto;
import sample.utils.enums.Availability;

import java.util.List;

public class BorrowingCopy {
    private Long copyId;
    private String code;
    private String title;
    private List<AuthorBookDto> authors;
    private Availability availability;


    public BorrowingCopy() {
    }

    public BorrowingCopy(Long copyId, String code, String title, List<AuthorBookDto> authors, Availability availability) {
        this.copyId = copyId;
        this.code = code;
        this.title = title;
        this.authors = authors;
        this.availability = availability;
    }

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorBookDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorBookDto> authors) {
        this.authors = authors;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object obj) {
        return copyId == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(this.copyId);
    }
}
