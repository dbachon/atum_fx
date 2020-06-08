package sample.dto.out;

import sample.utils.enums.Genre;

import java.util.List;

public class BookSettingsRequest {
    private Long id;
    private String title;
    private Genre genre;
    private List<Long> authorsId;
    private Long publisher;

    public BookSettingsRequest(Long id, String title, Genre genre, List<Long> authorsId, Long publisher) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.authorsId = authorsId;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Long> getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(List<Long> authorsId) {
        this.authorsId = authorsId;
    }

    public Long getPublisher() {
        return publisher;
    }

    public void setPublisher(Long publisher) {
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
