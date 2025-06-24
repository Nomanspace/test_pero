package com.example.demo;

import com.codeborne.selenide.WebDriverConditions;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import com.codeborne.selenide.Selenide;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductPageTest extends BaseStructure {
    private static final String PRODUCT_PAGE_URL = "https://vk.com/club225299895?w=product-225299895_10044406";
    private ProductPage productPage;

    @BeforeEach
    public void setUp() {
        productPage = new ProductPage(PRODUCT_PAGE_URL);
    }

    @Test
    @DisplayName("Кнопка Поделиться: кликабельна и открывает модальное окно для неавторизованных")
    public void testShareButtonOpensUnauthModal() {
        productPage.clickShareButton();
        productPage.getShareUnauthModal().shouldBe(visible.because("Модальное окно Поделиться для неавторизованных должно быть видимо"));
        productPage.getShareUnauthModalHeader().shouldHave(text("Зарегистрируйтесь, чтобы делиться интересными материалами"));
        productPage.getShareUnauthModalLoginButton().shouldBe(visible, enabled);
        productPage.getShareUnauthModalJoinButton().shouldBe(visible, enabled);
        productPage.getShareUnauthModalCloseButton().shouldBe(visible, enabled).click();
        productPage.getShareUnauthModal().shouldNotBe(visible.because("Модальное окно должно закрыться"));
    }

    @Test
    @DisplayName("Кнопка Написать: кликабельна и для неавторизованных ведет на страницу логина с параметром")
    public void testWriteButtonRedirectsToLoginForUnauth() {
        String initialUrl = WebDriverRunner.url();
        productPage.clickWriteButton();
        webdriver().shouldHave(urlContaining("/?to="),Duration.ofSeconds(10));
        String newUrl = WebDriverRunner.url();
        assertNotEquals(initialUrl, newUrl);
    }

    @Test
    @DisplayName("Кнопка Добавить в избранное: кликабельна и открывает выпадающее меню")
    public void testBookmarkActionsButtonOpensDropdown() {
        productPage.clickBookmarkActionsButton();
        sleep(10000);
        productPage.getBookmarkDropdown().shouldBe(visible.because("Выпадающее меню закладок должно быть видимо"));
        productPage.getBookmarkDropdownAddToBookmarksItem().shouldBe(visible).shouldHave(text("Сохранить в закладках"));
        productPage.getBookmarkDropdownAddToWishlistItem().shouldBe(visible).shouldHave(text("Добавить в желания"));
        sleep(10000);
        productPage.clickBookmarkActionsButton();
        sleep(10000);
        productPage.getBookmarkDropdown().shouldNotBe(visible.because("Выпадающее меню должно закрыться"));
    }

    @Test
    @DisplayName("Аватар продавца: кликабелен и открывает страницу сообщества в новой вкладке")
    public void testSellerAvatarLinkNavigation() {
        productPage.getSellerAvatarLinkElement().shouldBe(visible, enabled);
        String initialUrl = WebDriverRunner.url();
        productPage.clickSellerAvatarLink();
        Selenide.switchTo().window(1);
        webdriver().shouldHave(urlContaining("/club225299895"),Duration.ofSeconds(10));
        String newTabUrl = WebDriverRunner.url();
        assertNotEquals(initialUrl, newTabUrl, "URL в новой вкладке не должен совпадать с исходным");
        Selenide.closeWindow();
        Selenide.switchTo().window(0);
    }

    @Test
    @DisplayName("Имя продавца: кликабельно, содержит текст и открывает страницу сообщества в новой вкладке")
    public void testSellerNameLinkNavigationAndText() {
        productPage.getSellerNameLinkElement().shouldBe(visible, enabled).shouldHave(text("Test public for test"));
        String initialUrl = WebDriverRunner.url();
        productPage.clickSellerNameLink();

        Selenide.switchTo().window(1);
        webdriver().shouldHave(urlContaining("/club225299895"), Duration.ofSeconds(10));
        String newTabUrl = WebDriverRunner.url();
        assertNotEquals(initialUrl, newTabUrl);
        Selenide.closeWindow();
    }

    @Test
    @DisplayName("Кнопка 'Подписаться': видна, содержит текст и (для неавторизованных) ведет на страницу логина")
    public void testSubscribeButtonTextAndRedirectsForUnauth() {
        productPage.getSubscribeToSellerButtonElement().shouldBe(visible, enabled).shouldHave(text("Подписаться"));
        String initialUrl = WebDriverRunner.url();
        productPage.clickSubscribeToSellerButton();
        webdriver().shouldHave(urlContaining("/?to="), Duration.ofSeconds(10));
        webdriver().shouldHave(urlContaining("bWFya2V0L3Byb2R1Y3QvZnl2YWYtMjI1Mjk5ODk1LTEwMDQ0NDA2P2RlbGF5ZWRBY3Rpb249c3Vic2NyaWJlR3JvdXA"));
        String newUrl = WebDriverRunner.url();
        assertNotEquals(initialUrl, newUrl);
    }

    @Test
    @DisplayName("Ссылка 'Пожаловаться на услугу': кликабельна и открывает модальное окно жалобы")
    public void testReportProductLinkOpensReportModal() {
        productPage.clickReportProductLink();

        productPage.getReportModal().shouldBe(visible.because("Модальное окно жалобы должно быть видимо"), Duration.ofSeconds(10));
        productPage.getReportModalIframe().shouldBe(visible.because("Iframe в модальном окне жалобы должен быть видим"));

        String iframeSrc = productPage.getReportModalIframe().getAttribute("src");
        assertNotNull(iframeSrc, "Атрибут src у iframe не должен быть null");
        assertTrue(iframeSrc.contains("reports?type=market"), "Атрибут src должен содержать 'reports?type=market'");
        assertTrue(iframeSrc.contains("owner_id=-225299895"), "Атрибут src должен содержать 'owner_id=-225299895'");
        assertTrue(iframeSrc.contains("item_id=10044406"), "Атрибут src должен содержать 'item_id=10044406'");

        productPage.getReportModalCloseButton().shouldBe(visible, enabled).click();
        productPage.getReportModal().shouldNotBe(visible.because("Модальное окно жалобы должно закрыться"), Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Проверка видимости основной текстовой информации о товаре в карточке")
    public void testMainProductTextInfoVisibility() {
        productPage.getProductTitle().shouldBe(visible).shouldHave(text("фываф"));
        productPage.getProductPrice().shouldBe(visible).shouldHave(text("бесплатно"));
        productPage.getDisclaimerTextElement().shouldBe(visible)
                .shouldHave(text("Перед покупкой уточняйте характеристики и комплектацию у продавца"));
        productPage.getDescriptionTitleElement().shouldBe(visible);
        productPage.getProductDescription().shouldBe(visible).shouldHave(text("testing product"));
    }


}



