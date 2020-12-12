package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CallbackTest {

    @BeforeEach
    void openSite() {
        open("http://localhost:9999");
    }
    @Test
    void shouldSubmitRequestValid() {
        SelenideElement form = $(".form");
        form. $("[data-test-id=name] input").setValue("Василий Горошкин");
        form. $("[data-test-id=phone] input").setValue("+79270000000");
        form. $("[data-test-id=agreement]").click();
        form. $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldSubmitRequestValidHyphen() {
        SelenideElement form = $(".form");
        form. $("[data-test-id=name] input").setValue("Иван Григошвили-оглы");
        form. $("[data-test-id=phone] input").setValue("+79270000000");
        form. $("[data-test-id=agreement]").click();
        form. $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldNotSubmitRequestInvalidName() {
        SelenideElement form = $(".form");
        form. $("[data-test-id=name] input").setValue("Ric Sanches");
        form. $("[data-test-id=phone] input").setValue("+79270000000");
        form. $("[data-test-id=agreement]").click();
        form. $(".button").click();
        form. $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldNotSubmitRequestEmptyName() {
        SelenideElement form = $(".form");
        form. $("[data-test-id=name] input").setValue("");
        form. $("[data-test-id=phone] input").setValue("+79270000000");
        form. $("[data-test-id=agreement]").click();
        form. $(".button").click();
        form. $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldNotSubmitRequestInvalidPhone() {
        SelenideElement form = $(".form");
        form. $("[data-test-id=name] input").setValue("Василий Горошкин");
        form. $("[data-test-id=phone] input").setValue("aaaaaaa");
        form. $("[data-test-id=agreement]").click();
        form. $(".button").click();
        form. $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldNotSubmitRequestEmptyPhone() {
        SelenideElement form = $(".form");
        form. $("[data-test-id=name] input").setValue("Василий Горошкин");
        form. $("[data-test-id=phone] input").setValue("");
        form. $("[data-test-id=agreement]").click();
        form. $(".button").click();
        form. $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldNotSubmitRequestNotAgreement() {
        SelenideElement form = $(".form");
        form. $("[data-test-id=name] input").setValue("Василий Горошкин");
        form. $("[data-test-id=phone] input").setValue("+79270000000");
        form. $(".button").click();
        form. $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
}
