package sample.dto.in;

public class PublisherBookDto {
    private Long id;
    private String name;
    private String telephone;
    private String email;

    @Override
    public String toString() {
        return name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PublisherBookDto() {
    }

    public PublisherBookDto(Long id, String name, String telephone, String email) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }
}
