package com.example.demo;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.WebDriverRunner.url;

import com.codeborne.selenide.*;


public class CommunityPage {


    public CommunityPage(String url) {
        Selenide.open(url);
    }


    public CommunityPage checkUrl(String url) {
        url().contains(url);
        return this;
    }

    public CommunityPage checkAvatarVisible() {
        $x("//div[@id='wrap3']")
                .shouldBe(visible)
                .$x(".//div[@class='redesigned-group-info__avatar']")
                .shouldBe(visible)
                .$x(".//img[@class='AvatarRich__img']")
                .shouldBe(visible)
                .shouldHave(attributeMatching("src", "^" + "https://sun9-61.userapi.com/impg/hV2sUZg3H8_i7YovgNIe3wWzVyeH0obdWHIPLw/lj-AKosjN2o.jpg" + ".*"));
        return this;
    }

    public CommunityPage checkNamePageVisible() {
        $x("//h1[@class='page_name']")
                .shouldBe(visible)
                .shouldHave(text("Test public for test"));
        return this;
    }

    public CommunityPage checkRatingStarBar() {
        SelenideElement parrentElement = $x("//a[@data-testid='community-review-open']");
        parrentElement
                .shouldBe(visible)
                .shouldHave(href("/reviews-225299895"))
                .shouldHave(attribute("aria-label", "Рейтинг сообщества 5,0"));


        $x("//span[@class='vkitRatingLayout__afterInner--qSCiI']")
                .shouldBe(visible)
                .shouldHave(text("2 отзыва"));

        return this;
    }

    public CommunityPage checkSubscriberSumInRatingStarBar() {
        $x("//div[@id='page_subscribers']")
                .shouldHave(matchText("\\d+\\s+(подписчик|подписчика|подписчиков)"));
        return this;
    }

    public CommunityPage checkSubscribeButton() {
        $x("//button[@class='FlatButton FlatButton--primary FlatButton--size-m redesigned-group-action']")
                .shouldBe(visible).click();
        return this;
    }

    public CommunityPage checkTabsAreVisible() {
        SelenideElement tabsContainer = $("#group_tabs").shouldBe(visible);
        tabsContainer.$x(".//a[contains(@onclick, \"switchContentTab(this, 'market'\")]")
                .shouldBe(visible).shouldHave(text("Товары"));
        tabsContainer.$x(".//a[contains(@onclick, \"switchContentTab(this, 'services'\")]")
                .shouldBe(visible).shouldHave(text("Услуги"));
        return this;
    }

    public CommunityPage switchToMarketTab() {
        SelenideElement marketTabLink = $("#group_tabs").shouldBe(visible)
                .$x(".//a[contains(@onclick, \"switchContentTab(this, 'market'\")]");
        marketTabLink.click();

        marketTabLink.shouldHave(cssClass("ui_tab_sel"));
        $("div.group-tab-content[data-tab='market']").shouldBe(visible);

        $("#group_tabs").$x(".//a[contains(@onclick, \"switchContentTab(this, 'services'\")]")
                .shouldNotHave(cssClass("ui_tab_sel"));
        $("div.group-tab-content[data-tab='services']").shouldNotBe(visible);
        return this;
    }

    public CommunityPage checkMarketTabContent() {
        SelenideElement marketTabContent = $("div.group-tab-content[data-tab='market']").shouldBe(visible);

        SelenideElement firstMarketItem = marketTabContent.$x(".//div[@class='MarketItemPreview']").shouldBe(visible);

        firstMarketItem.$(".MarketItemPreview__title.EcommPreviewProductCardProductTitle").shouldHave(text("Тест"));
        firstMarketItem.$x(".//div[contains(@class, 'EcommPreviewProductCardProductPrice__main')]").shouldHave(text("300 ₽"));
        firstMarketItem.$x(".//span[contains(@class, 'EcommPreviewProductCardProductReviewRating__mark')]").shouldHave(text("5,0"));
        firstMarketItem.$x(".//span[contains(@class, 'EcommPreviewProductCardProductReviewRating__reviewCount')]").shouldHave(text("1 отзыв"));

        marketTabContent.$x(".//a[@data-role='show-all' and contains(@href, '/market-225299895')]")
                .shouldBe(visible).shouldHave(text("Показать все 1"));
        return this;
    }

    public CommunityPage switchToServicesTab() {
        SelenideElement servicesTabLink = $("#group_tabs").shouldBe(visible)
                .$x(".//a[contains(@onclick, \"switchContentTab(this, 'services'\")]");
        servicesTabLink.click();

        servicesTabLink.shouldHave(cssClass("ui_tab_sel"));
        $("div.group-tab-content[data-tab='services']").shouldBe(visible);

        $("#group_tabs").$x(".//a[contains(@onclick, \"switchContentTab(this, 'market'\")]")
                .shouldNotHave(cssClass("ui_tab_sel"));
        $("div.group-tab-content[data-tab='market']").shouldNotBe(visible);
        return this;
    }

    public CommunityPage checkServicesTabContent() {
        SelenideElement servicesTabContent = $("div.group-tab-content[data-tab='services']").shouldBe(visible);

        SelenideElement firstServiceItem = servicesTabContent.$x(".//div[@class='MarketItemPreview']").shouldBe(visible);

        firstServiceItem.$(".MarketItemPreview__title.EcommPreviewProductCardProductTitle").shouldHave(text("фываф"));

        firstServiceItem.$x(".//div[contains(@class, 'EcommPreviewProductCardProductPrice__main')]").shouldHave(text("бесплатно"));

        servicesTabContent.$x(".//a[@data-role='show-all' and contains(@href, '/uslugi-225299895')]")
                .shouldBe(visible).shouldHave(text("Показать все 1"));
        return this;

    }

    public CommunityPage checkDetailedInfoLinkIsPresent() {
        SelenideElement detailedInfoBlock = $("#page_group_info_block").shouldBe(visible);
        SelenideElement link = detailedInfoBlock.$x(".//a[@class='groups-redesigned-info-more']");

        link.shouldBe(visible)
                .shouldHave(text("Подробная информация"))
                .shouldHave(attribute("href", "https://vk.com/club225299895?w=club225299895"));
        return this;
    }

    public CommunityPage clickDetailedInfoLink() {
        $("#page_group_info_block").$x(".//a[@class='groups-redesigned-info-more']").click();

        $("#wk_content").shouldBe(visible, java.time.Duration.ofSeconds(10));
        return this;
    }

    public CommunityPage verifyDetailedInfoModalOpened() {

        checkUrl("?w=club225299895");

        SelenideElement modalContent = $("#wk_content").shouldBe(visible);

        modalContent.$x(".//span[@class='group-info-box__title']")
                .shouldBe(visible)
                .shouldHave(text("Подробная информация"));

        modalContent.$x(".//div[@class='box_x_button box_x_tabs']")
                .shouldBe(visible)
                .shouldHave(attribute("aria-label", "Закрыть"));

        SelenideElement descriptionRow = modalContent.$x(".//div[contains(@class, 'group_info_row') and @title='Описание']");
        descriptionRow.shouldBe(visible);
        descriptionRow.$x(".//div[@class='line_value']")
                .shouldHave(text("Test public for test"));

        modalContent.$x(".//span[@class='header_label fl_l' and text()='История сообщества']")
                .shouldBe(visible);

        SelenideElement historyContent = modalContent.$("#react_rootGroups_render_name_history");
        historyContent.$x(".//div[contains(@class, 'NameHistoryShortCell__textMain')]")
                .shouldHave(text("Сообщество Test public for test создано"));
        historyContent.$x(".//div[contains(@class, 'NameHistoryShortCell__textMain')]/following-sibling::div")
                .shouldHave(text("28 марта 2024 года"));
        return this;
    }

    public CommunityPage closeDetailedInfoModal() {
        $("#wk_content").shouldBe(visible)
                .$x(".//div[@class='box_x_button box_x_tabs']")
                .click();
        $("#wk_content").shouldNotBe(visible, java.time.Duration.ofSeconds(5));
        return this;
    }


    public int getSubscribersCountFromHeader() {
        SelenideElement followersBlock = $("#group_followers").shouldBe(visible);
        SelenideElement headerLink = followersBlock.$x("./a[@class='module_header']").shouldBe(visible);
        SelenideElement headerTop = headerLink.$x(".//div[@class='header_top clear_fix']");

        headerTop.$x(".//span[@class='header_label fl_l']")
                .shouldBe(visible)
                .shouldHave(text("Подписчики"));

        String countText = headerTop.$x(".//span[@class='header_count fl_l']")
                .shouldBe(visible)
                .getText();
        try {
            return Integer.parseInt(countText);
        } catch (NumberFormatException e) {
            throw new AssertionError("Не удалось преобразовать количество подписчиков '" + countText + "' в число.", e);
        }
    }

    public CommunityPage verifySubscriberAvatarAndNameArePresentIfCountPositive(int actualSubscriberCount) {
        SelenideElement followersBlock = $("#group_followers").shouldBe(visible);
        SelenideElement moduleBody = followersBlock.$x(".//div[@class='module_body clear_fix']");

        if (actualSubscriberCount >= 1) {
            moduleBody.shouldBe(visible);
            SelenideElement firstPeopleCell = moduleBody.$x(".//div[@class='people_cell']");
            firstPeopleCell.shouldBe(visible);

            firstPeopleCell.$x(".//div[contains(@class, 'people_cell_ava')]/a/img[@class='AvatarRich__img']")
                    .shouldBe(visible)
                    .shouldHave(attribute("src"));

            firstPeopleCell.$x(".//div[@class='people_cell_name']/a")
                    .shouldBe(visible)
                    .shouldNotBe(empty);
        } else {

            moduleBody.$$x(".//div[@class='people_cell']").shouldHave(CollectionCondition.size(0));
        }
        return this;
    }

}
