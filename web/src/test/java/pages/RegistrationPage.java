package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

@PageEntry(title = "Страница регистрации")
public class RegistrationPage extends BasePage{
    public RegistrationPage(){
        super();
    }

    @ElementTitle("Логин")
    @FindBy(id = "UserLogin_username")
    public WebElement loginField;

    @ElementTitle("Пароль")
    @FindBy(id = "UserLogin_password")
    public WebElement passwordField;

    @ElementTitle("Войти")
    @FindBy(xpath = "//input[@value = 'Sign in']")
    public WebElement signInButton;

    @ElementTitle("Заголовок")
    @FindBy(xpath = "//a[text() = 'Casino']")
    public WebElement title;
}
