package sample;

import javafx.beans.property.SimpleBooleanProperty;
import sample.dto.in.AuthResponse;
import sample.utils.BorrowingCopy;
import sample.utils.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class AppState {
    private static AppState ourInstance = new AppState();

    public static AppState getInstance() {
        return ourInstance;
    }

    private String token = null;
    private Role role = Role.READER;
    private List<BorrowingCopy> borrowingsAddCopies = new ArrayList<>();

    private SimpleBooleanProperty isAuthenticated = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty isBorrowingAdd = new SimpleBooleanProperty(false);

    public void setCredential(AuthResponse auth) {
        if (auth == null) {
            isAuthenticated.set(false);
            token = null;
        } else {
            isAuthenticated.set(true);
            token = "Bearer " + auth.getToken();
            role = auth.getRole();
        }
    }

    public SimpleBooleanProperty isAuthenticatedProperty() {
        return isAuthenticated;
    }

    public String getToken() {
        return token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<BorrowingCopy> getBorrowingsAddCopies() {
        return borrowingsAddCopies;
    }

    public void setBorrowingsAddCopies(List<BorrowingCopy> borrowingsAddCopies) {
        this.borrowingsAddCopies = borrowingsAddCopies;
    }

    public void addBorrwoingCopies(BorrowingCopy borrowingCopy) {

        if (!borrowingsAddCopies.contains(borrowingCopy)) {
            this.borrowingsAddCopies.add(borrowingCopy);
        }
        isBorrowingAdd.set(true);
        isBorrowingAdd.set(false);
    }

    public boolean isIsAuthenticated() {
        return isAuthenticated.get();
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated.set(isAuthenticated);
    }

    public boolean isIsBorrowingAdd() {
        return isBorrowingAdd.get();
    }

    public SimpleBooleanProperty isBorrowingAddProperty() {
        return isBorrowingAdd;
    }

    public void setIsBorrowingAdd(boolean isBorrowingAdd) {
        this.isBorrowingAdd.set(isBorrowingAdd);
    }

    public void deleteBorrowingCopies(BorrowingCopy borrowingCopy) {

        this.borrowingsAddCopies.remove(borrowingCopy);
        isBorrowingAdd.set(true);
        isBorrowingAdd.set(false);
    }

    public void logOut(){
        token = null;
        isAuthenticated.set(false);
    }
}
