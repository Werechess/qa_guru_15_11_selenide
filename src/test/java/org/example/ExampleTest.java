package org.example;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class ExampleTest {

    @Test
    @Disabled("example, not an actual test")
    void exampleTest() {
        $("span").closest("div")    // finds matching element up the dom tree, same as ancestor
                .sibling(0)                    // finds first following sibling of element with any tag
                .preceding(1);                 // finds second preceding sibling of element with any tag

        $("div")                           // finds element with tag name
                .$(".class")               // finds element with class
                .$("#id")                  // finds element with id
                .$("div > span")           // finds element with first span child of div
                .$("div span")             // finds element with any span child of div
                .$(".class1.class2")       // finds element that contains two classes
                .$(".class1:not(.class2)") // finds element that contains class1, but not class2
                .$("*:not([style='a=b;'])") // finds element that does not contain attribute
                .shouldHave(text("any matching substring"))
                .shouldHave(exactText("specific full string"))
                .shouldHave(textCaseSensitive("cAsE sEnSiTiVe substring"))
                .shouldHave(exactTextCaseSensitive("cAsE sEnSiTiVe full string"));

        $$("div");                        // finds all matching elements, command works in console tab of F12

        SelenideElement button = $("button");    // element not searched here, only declared
        button.shouldHave(attribute("test"));    // element searched with assertion

        // F8 to stop js scripts execution in browser
        // or setTimeout(function(){debugger},3000) in console tab to pause after time (3 seconds in example)
    }

    @Test
    void shouldFindSelenideRepositoryAtFirstPlaceTest() {

        // ARRANGE (подготовка, необязательна если не требуется)
        open("https://github.com/");

        // ACT (действие, может повторяться)
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        $$(".repo-list > li").first().$("a").click();

        // ASSERT (проверка, обязательна в конце теста, но может встречаться несколько раз на разные действия)
        $("#repository-container-header").shouldHave(text("selenide /\nselenide"));
    }

    @Test
    void andreiSolntsevShouldBeTheFirstContributor() {
//        Configuration.browserSize = "800x400";
        open("https://github.com/selenide/selenide");
        $(".Layout-sidebar").$(byText("Contributors"))
                .ancestor(".BorderGrid-cell").$$("ul > li").first().hover();
        $$(".Popover-message").findBy(visible).shouldHave(text("Andrei Solntsev"));
        // or .filterBy(visible).first(), but that will work slower
    }
}
