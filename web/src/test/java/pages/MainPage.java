package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

@PageEntry(title = "Главная страница")
public class MainPage extends BasePage {
    public MainPage() {
        super();
    }

    @ElementTitle("Пользователи")
    @FindBy(xpath = "//span[text()= 'Users']")
    public WebElement users;

    @ElementTitle("Игроки")
    @FindBy(xpath = "//a[text()= 'Players']")
    public WebElement players;

    @ElementTitle("Дата регистрации")
    @FindBy(xpath = "//a[text() = 'Registration date']")
    public WebElement sortedByRegistrationsDate;

    @ElementTitle("Таблица Игроков")
    @FindBy(id = "payment-system-transaction-grid")
    public WebElement playersTable;

    @ElementTitle("Ссылки на игроков")
    public String playerLinks = "//tbody/tr/td[2]/a";
}
