package sample.dto.out;

import sample.utils.enums.Genre;


import java.util.List;

public class BookAddRequest {
    private String title;
    private Genre genre;
    private List<Long> authorsId;
    private Long publisher;


    public BookAddRequest() {
    }

    public BookAddRequest(String title, Genre genre, List<Long> authorsId, Long publisher) {
        this.title = title;
        this.genre = genre;
        this.authorsId = authorsId;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "BookAddRequest{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", authorsId=" + authorsId +
                ", publisher=" + publisher +
                '}';
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
}
