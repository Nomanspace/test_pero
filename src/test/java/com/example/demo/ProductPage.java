package com.example.demo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

// page_url = https://vk.com/club225299895?w=product-225299895_10044406

public class ProductPage {

    // Основная информация о товаре
    private final SelenideElement productTitle = $x("//h1[@data-testid='market_item_page_title']");
    private final SelenideElement productPrice = $x("//h2[@data-testid='market_item_page_price']");
    private final SelenideElement productDescription = $x("//div[@data-testid='market_item_page_description']/div/div");
    private final SelenideElement disclaimerTextElement = $x("//span[@data-testid='market_item_disclaimer']");
    // Локатор для заголовка Описание
    private final SelenideElement descriptionTitle = $x("//span[text()='Описание']");

    // Информация о продавце
    private final SelenideElement sellerNameLink = $x("//span[@data-testid='market_item_page_shop_text']/ancestor::a");
    private final SelenideElement sellerAvatarLink = $x("//a[@data-testid='market_item_page_group_avatar']");
    private final SelenideElement sellerCategoryLink = $x("//a[@data-testid='market_item_page_group_link']");
    private final SelenideElement subscribeToSellerButton = $x("//button[@data-testid='market_item_page_subscribe_button']");

    // Основные кнопки действий
    private final SelenideElement writeButton = $x("//button[@data-testid='market_item_page_primary_button']");
    private final SelenideElement shareButton = $x("//button[@data-testid='market_item_page_share']");
    private final SelenideElement bookmarkActionsButton = $x("//button[@data-testid='market_item_page_actions_opener_button_not_checked']");

    // Галерея изображений
    private final SelenideElement imageGalleryContainer = $x("//div[@data-testid='market_item_gallery_container']");

    // Ссылка Пожаловаться
    private final SelenideElement reportProductLink = $x("//div[@role='button' and @data-testid='market_item_page_report']");

    // Модальное окно Поделиться. для неавторизованных.
    private final SelenideElement shareUnauthModal = $x("//div[contains(@class, 'UnauthActionBoxContainer')]");
    private final SelenideElement shareUnauthModalHeader = shareUnauthModal.$x(".//div[contains(@class, 'UnauthActionBox__header') and contains(text(), 'Зарегистрируйтесь, чтобы делиться')]");
    private final SelenideElement shareUnauthModalLoginButton = shareUnauthModal.$x(".//button[.//span[contains(text(), 'Войти')]]");
    private final SelenideElement shareUnauthModalJoinButton = shareUnauthModal.$x(".//button[.//span[contains(text(), 'Создать аккаунт')]]");


    // Модальное окно Добавить в закладки. выпадающее меню.
    private final SelenideElement bookmarkDropdown = $x("//div[@data-testid='dropdownactionsheet']");
    private final SelenideElement bookmarkDropdownAddToBookmarksItem = bookmarkDropdown.$x(".//div[@data-testid='dropdownactionsheet-item-bookmark']//span[contains(text(), 'Сохранить в закладках')]");
    private final SelenideElement bookmarkDropdownAddToWishlistItem = bookmarkDropdown.$x(".//div[@data-testid='dropdownactionsheet-item-wishlist']//span[contains(text(), 'Добавить в желания')]");
    private final SelenideElement bookmarkDropdownAddIcon = bookmarkDropdown.$x(".//div[@data-testid='dropdownactionsheet-item-bookmark']//svg[contains(@class, 'add_circle_outline_28')]");
    private final SelenideElement wishlistDropdownAddIcon = bookmarkDropdown.$x(".//div[@data-testid='dropdownactionsheet-item-wishlist']//svg[contains(@class, 'add_circle_outline_28')]");

    // Модальное окно Пожаловаться
    private final SelenideElement reportModal = $x("//div[@data-testid='modalbox' and @aria-label='Форма отправки жалобы']");
    private final SelenideElement reportModalIframe = reportModal.$x(".//iframe[@id='reports_iframe']");
    private final SelenideElement reportModalCloseButton = reportModal.$x(".//div[contains(@class, 'vkuiModalDismissButton__host')]");


    public ProductPage(String url) {
        Selenide.open(url);
    }

    // Методы для взаимодействия
    public ProductPage clickShareButton() {
        shareButton.shouldBe(visible, enabled).click();
        return this;
    }

    public ProductPage clickWriteButton() {
        writeButton.shouldBe(visible, enabled).click();
        return this;
    }

    public ProductPage clickBookmarkActionsButton() {
        bookmarkActionsButton.shouldBe(visible, enabled).click();
        return this;
    }

    public ProductPage clickSellerAvatarLink() {
        sellerAvatarLink.shouldBe(visible, enabled).click();
        return this;
    }

    public ProductPage clickSellerNameLink() {
        sellerNameLink.shouldBe(visible, enabled).click();
        return this;
    }

    public ProductPage clickSubscribeToSellerButton() {
        subscribeToSellerButton.shouldBe(visible, enabled).click();
        return this;
    }

    public ProductPage clickReportProductLink() {
        reportProductLink.shouldBe(visible).click();
        return this;
    }

    // Геттеры для элементов и проверок
    public SelenideElement getProductTitle() { return productTitle; }
    public SelenideElement getProductPrice() { return productPrice; }
    public SelenideElement getProductDescription() { return productDescription; }
    public SelenideElement getDisclaimerTextElement() { return disclaimerTextElement; }
    public SelenideElement getDescriptionTitleElement() { return descriptionTitle; }


    public SelenideElement getSellerAvatarLinkElement() { return sellerAvatarLink; }
    public SelenideElement getSellerNameLinkElement() { return sellerNameLink; }
    public SelenideElement getSubscribeToSellerButtonElement() { return subscribeToSellerButton; }


    public SelenideElement getShareUnauthModal() { return shareUnauthModal; }
    public SelenideElement getShareUnauthModalHeader() { return shareUnauthModalHeader; }
    public SelenideElement getShareUnauthModalLoginButton() { return shareUnauthModalLoginButton; }
    public SelenideElement getShareUnauthModalJoinButton() { return shareUnauthModalJoinButton; }
    public SelenideElement getShareUnauthModalCloseButton() {
        return shareUnauthModal.$x(".//button[@aria-label='Закрыть']");
    }

    public SelenideElement getBookmarkDropdown() { return bookmarkDropdown; }
    public SelenideElement getBookmarkDropdownAddToBookmarksItem() { return bookmarkDropdownAddToBookmarksItem; }
    public SelenideElement getBookmarkDropdownAddToWishlistItem() { return bookmarkDropdownAddToWishlistItem; }
    public SelenideElement getBookmarkDropdownAddIcon() { return bookmarkDropdownAddIcon; }
    public SelenideElement getWishlistDropdownAddIcon() { return wishlistDropdownAddIcon; }

    public SelenideElement getReportModal() { return reportModal; }
    public SelenideElement getReportModalIframe() { return reportModalIframe; }
    public SelenideElement getReportModalCloseButton() { return reportModalCloseButton; }

}