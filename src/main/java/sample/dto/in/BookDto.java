package sample.dto.in;


import sample.utils.enums.Genre;

import java.util.List;

public class BookDto {
    private Long id;
    private String title;
    private List<AuthorBookDto> authors;
    private PublisherBookDto publisher;
    private Genre genre;


    @Override
    public String toString() {
        return title + " " + authors;
    }

    public List<AuthorBookDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorBookDto> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PublisherBookDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherBookDto publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
