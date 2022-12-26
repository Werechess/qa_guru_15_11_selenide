package org.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class WikiTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void selenideSoftAssertionsShouldHaveJunit5ExampleSectionTest() {
        open("https://github.com/selenide/selenide");
        $("a#wiki-tab").click();

//        поиск по тексту вики
//        $$("div#wiki-body li a").findBy(text("Soft assertions")).click();

//        поиск фильтрацией в сайд меню
//        $("input#wiki-pages-filter").setValue("SoftAssertions");

        $(".wiki-more-pages-link button").click();
        $$("div#wiki-pages-box summary a").findBy(text("SoftAssertions")).click();

        $$("div#wiki-body h4").findBy(text("Using JUnit5 extend test class"))
                .shouldBe(visible);
    }
}
