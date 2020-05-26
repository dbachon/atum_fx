package sample.dto.in;

import sample.utils.enums.Role;

public class AuthResponse {
    private String email;
    private String token;
    private Role role;

    public AuthResponse(String email, String token, Role role) {
        this.email = email;
        this.token = token;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
