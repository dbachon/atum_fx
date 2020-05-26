package sample.utils;

import sample.services.*;

import java.util.function.BiConsumer;

public class BaseComponent {
    protected BiConsumer<Class<? extends BaseComponent>, BaseProps> router;
    protected BaseProps props;


    protected BookService bookService = RetrofitInstance.getInstance().create(BookService.class);
    protected UserService userService = RetrofitInstance.getInstance().create(UserService.class);
    protected PublisherService publisherService = RetrofitInstance.getInstance().create(PublisherService.class);
    protected AuthorService authorService = RetrofitInstance.getInstance().create(AuthorService.class);
    protected AuthService authService = RetrofitInstance.getInstance().create(AuthService.class);
    protected CopyService copyService = RetrofitInstance.getInstance().create(CopyService.class);
    protected BorrowingService borrowingService = RetrofitInstance.getInstance().create(BorrowingService.class);

    public void init(){}

    public void setRouter(BiConsumer<Class<? extends BaseComponent>, BaseProps> router) {
        this.router = router;
    }

    public void setProps(BaseProps props) {
        this.props = props;
    }
}
