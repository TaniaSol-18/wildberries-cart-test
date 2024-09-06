# wildberries-cart-test

# Проект для проверки добавления товара в корзину Wildberries

## Описание

Этот проект содержит автотест для проверки функциональности добавления товара в корзину приложения Wildberries для Android. Проект использует Java и Selenide для автоматизации тестирования.

### Содержание репозитория

    pom.xml - Файл конфигурации Maven с зависимостями для Selenide и TestNG.
    src/test/java/ - Папка с тестами на Java.
    README.md - Этот файл с инструкциями.

### Создание репозитория на GitHub

    Перейдите на GitHub и войдите в свою учетную запись.
    Нажмите на кнопку New (Создать новый репозиторий).
    Введите имя репозитория, например, wildberries-cart-test.
    Убедитесь, что выбрана опция Public или Private, в зависимости от ваших предпочтений.
    Нажмите Create repository (Создать репозиторий).

### Загрузка проекта в GitHub

    Откройте терминал и перейдите в корневую папку вашего проекта:

    cd путь/к/вашему/проекту

    Инициализируйте Git-репозиторий, если это еще не сделано:

    git init

    Добавьте все файлы в репозиторий:

    git add .

    Сделайте первый коммит:

    git commit -m "Initial commit"

    Добавьте удаленный репозиторий GitHub:

    git remote add origin https://github.com/ваш-логин/wildberries-cart-test.git

    Отправьте изменения в удаленный репозиторий:

    git push -u origin master

# Написание автотеста с использованием Selenide
Установка зависимостей

Убедитесь, что в вашем файле pom.xml указаны следующие зависимости:

<dependency>
    <groupId>com.codeborne</groupId>
    <artifactId>selenide</artifactId>
    <version>6.6.2</version>
</dependency>
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.7.1</version>
    <scope>test</scope>
</dependency>

## Пример автотеста

Создайте файл CartTest.java в папке src/test/java/ со следующим содержимым:

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

public class CartTest {

    @BeforeClass
    public void setUp() {
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        // Убедитесь, что ChromeDriver установлен и настроен
    }

    @Test
    public void testAddItemToCart() {
        open("https://wildberries.ru"); // Замените на правильный URL для мобильного тестирования

        // Найдите элемент для поиска товара
        SelenideElement searchBox = $(By.name("search")); 
        searchBox.setValue("Пример товара").pressEnter();

        // Найдите и выберите товар
        SelenideElement item = $(By.xpath("//div[contains(text(),'Пример товара')]"));
        item.click();

        // Добавьте товар в корзину
        SelenideElement addToCartButton = $(By.xpath("//button[contains(text(),'Добавить в корзину')]"));
        addToCartButton.click();

        // Проверьте, что товар добавлен в корзину
        SelenideElement cartIcon = $(By.xpath("//a[@href='/cart']"));
        cartIcon.click();
        
        SelenideElement cartItem = $(By.xpath("//div[contains(text(),'Пример товара')]"));
        assertTrue(cartItem.exists(), "Товар не был добавлен в корзину");
    }
}

# Запуск тестов

Для запуска тестов используйте команду Maven:

mvn test
