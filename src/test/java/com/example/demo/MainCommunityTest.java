package com.example.demo;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;


public class MainCommunityTest extends BaseStructure {
    private static final String COMMUNITY_PAGE_URL = "https://vk.com/club225299895";
    private CommunityPage communityPage;

    @BeforeEach
    public void setUp() {
        communityPage = new CommunityPage(COMMUNITY_PAGE_URL);
    }

    @Test
    @DisplayName("Функциональный тест Инфо Шапки сообщества")
    public void groupInfo() {
        communityPage.checkRatingStarBar()
                .checkNamePageVisible()
                .checkAvatarVisible()
                .checkSubscriberSumInRatingStarBar();

        String initialUrl = WebDriverRunner.url();
        communityPage.checkSubscribeButton();
        sleep(10000);
        String currentUrl = WebDriverRunner.url();
        Assertions.assertNotEquals(initialUrl, currentUrl);
    }

    @Test
    @DisplayName("Тест табов Товары и Услуги в сообществе")
    public void communityTabsFunctionality() {
        communityPage.checkTabsAreVisible();

        $("div.group-tab-content[data-tab='market']").shouldBe(visible);
        communityPage.checkMarketTabContent();

        communityPage.switchToServicesTab();
        communityPage.checkServicesTabContent();

        communityPage.switchToMarketTab();
        communityPage.checkMarketTabContent();
    }

    @Test
    @DisplayName("Тест функциональности блока Подробная информация")
    public void detailedInfoBlockFunctionality() {
        String initialUrl = WebDriverRunner.url();

        communityPage.checkDetailedInfoLinkIsPresent();
        communityPage.clickDetailedInfoLink();

        communityPage.verifyDetailedInfoModalOpened();
        communityPage.closeDetailedInfoModal();

        Assertions.assertFalse(WebDriverRunner.url().contains("?w=club"),
                "URL не должен содержать параметр '?w=' после закрытия модального окна. Текущий URL: " + WebDriverRunner.url());
    }

    @Test
    @DisplayName("тест отображения информации в блоке Подписчики")
    public void SubscribersBlockDisplayInfo() {
        int actualSubscriberCount = communityPage.getSubscribersCountFromHeader();
        communityPage.verifySubscriberAvatarAndNameArePresentIfCountPositive(actualSubscriberCount);
    }

}
