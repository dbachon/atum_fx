package sample.dto.out;

public class AuthorAddRequest {
    private String firstName;
    private String lastName;


    public AuthorAddRequest() {
    }

    public AuthorAddRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
