package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

class HoverAndActionsTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldOpenSolutionsEnterpriseFromMainPageTest() {
        open("https://github.com/");
        $$("li button").findBy(text("Solutions")).hover();
        $$("a.HeaderMenu-dropdown-link").findBy(text("Enterprise")).click();
        $("div.enterprise-hero h1").shouldHave(text("Build like the best"));
    }

    @Test
    void shouldSwitchRectanglesTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        SelenideElement right = $("#column-a");
        SelenideElement left = $("#column-b");

        right.shouldHave(text("A"));
        left.shouldHave(text("B"));

//        not working
//        Selenide.actions().dragAndDrop(right,left);

        right.dragAndDropTo(left);

        right.shouldHave(text("B"));
        left.shouldHave(text("A"));
    }
}
