import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CartTest {

    @BeforeMethod
    public void setUp() {
        // Задайте URL приложения Wildberries
        open("https://www.wildberries.ru/");
    }

    @Test
    public void testAddItemToCart() {
        // Пример: Поиск товара и добавление в корзину
        SelenideElement searchBox = $("input[placeholder='Поиск']");
        searchBox.setValue("телевизор").pressEnter();
        $("div.product-card").first().$("button.add-to-cart").click();

        // Проверка, что товар добавлен в корзину
        $("a.cart").click();
        SelenideElement cartItem = $("div.cart-item");
        assert(cartItem.exists());
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}