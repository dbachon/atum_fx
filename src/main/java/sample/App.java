package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.pages.*;
import sample.utils.BaseComponent;
import sample.utils.BaseProps;
import sample.utils.Component;
import sample.utils.MenuItem;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;


public class App extends Application implements Initializable {

    @FXML
    private Pane mainContainer;

    @FXML
    private VBox menuItemWrapper;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app.fxml"));
        primaryStage.setTitle("Atum");
        primaryStage.setScene(new Scene(root, 1500, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setView(LoginPage.class, null);
        initMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setView(Class<? extends BaseComponent> page, BaseProps props) {
        try {
            Pane pane = createPage(page, props);
            Platform.runLater(() -> this.mainContainer.getChildren().setAll(pane));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMenu() {
        Arrays.asList(LoginPage.class, RegisterPage.class)
                .forEach(this::addMenuButton);
        AppState.getInstance().isAuthenticatedProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            menuItemWrapper.getChildren().setAll(Collections.emptyList());
            if (newValue) {
                menuItemWrapper.getChildren().setAll(Collections.emptyList());
                switch (AppState.getInstance().getRole()) {
                    case READER:
                        Arrays.asList(MyAccountPage.class, FindBooksComponent.class, BorrowingBooksPage.class, MyBorrowingPage.class)
                                .forEach(this::addMenuButton);
                        break;
                    case LIBRARIAN:
                        Arrays.asList(MyAccountPage.class, FindBooksComponent.class, BorrowingBooksPage.class, MyBorrowingPage.class,
                                FindBorrowingComponent.class, FindPublishersPage.class, FindAuthorsPage.class, BookAddPage.class,
                                AuthorAddPage.class, PublisherAddPage.class, CopyAddPage.class
                        )
                                .forEach(this::addMenuButton);
                        break;
                    case ADMIN:
                        Arrays.asList(MyAccountPage.class, FindBooksComponent.class, BorrowingBooksPage.class, MyBorrowingPage.class,
                                FindBorrowingComponent.class, FindPublishersPage.class, FindAuthorsPage.class, BookAddPage.class,
                                AuthorAddPage.class, PublisherAddPage.class, CopyAddPage.class, FindUsersComponent.class
                        )
                                .forEach(this::addMenuButton);
                        break;
                }
            } else {
                Arrays.asList(LoginPage.class, RegisterPage.class)
                        .forEach(this::addMenuButton);
            }
        }));
    }


    private Pane createPage(Class<? extends BaseComponent> page, BaseProps props) throws Exception {
        Component annotation = page.getAnnotation(Component.class);
        if (annotation == null) {
            throw new Exception("Invalid page");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(annotation.resource()));
        Pane pane = loader.load();
        BaseComponent controller = loader.getController();
        controller.setRouter(this::setView);
        controller.setProps(props);
        controller.init();
        return pane;
    }

    @SuppressWarnings("unchecked")
    private void addMenuButton(Class<? extends BaseComponent> page) {
        MenuItem menuItem = page.getAnnotation(MenuItem.class);
        if (menuItem == null) {
            //  throw new Exception("Invalid page");
        }
        Button button = new Button(menuItem.name());
        VBox.setVgrow(button, Priority.ALWAYS);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setUserData(page);
        button.setStyle("-fx-background-color: #464653; -fx-text-fill: #D2D6D7; -fx-arc-height: 100; -fx-font-size: 14; -fx-font-family: 'Droid Sans'");
        button.setMinHeight(60);
        VBox.setMargin(button, new Insets(0, 0, 2, 0));
        button.setOnAction((ActionEvent event) ->
                setView((Class<? extends BaseComponent>) ((Button) event.getSource()).getUserData(), null)
        );
        menuItemWrapper.getChildren().add(button);
    }

}