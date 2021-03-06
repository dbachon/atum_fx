package sample.dto.in;

public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;


    public AuthorDto(AuthorBookDto authorBookDto) {
        this.id = authorBookDto.getId();
        this.firstName = authorBookDto.getFirstName();
        this.lastName = authorBookDto.getLastName();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
