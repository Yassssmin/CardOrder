package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CallbackTest {

    @Test
    void shouldSubmitRequestValid() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form. $("[data-test-id=name] input").setValue("Василий Горошкин");
        form. $("[data-test-id=phone] input").setValue("+79270000000");
        form. $("[data-test-id=agreement]").click();
        form. $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldSubmitRequestValidHyphen() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form. $("[data-test-id=name] input").setValue("Иван Григошвили-оглы");
        form. $("[data-test-id=phone] input").setValue("+79270000000");
        form. $("[data-test-id=agreement]").click();
        form. $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
