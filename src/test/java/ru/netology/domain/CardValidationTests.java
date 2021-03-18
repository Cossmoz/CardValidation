package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardValidationTests {
    @Test
    void shouldVisibleValidationName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ben");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
    @Test
    void shouldVisibleValidationPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Константин");
        $("[data-test-id=phone] input").setValue("Привет");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }
    @Test
    void shouldVisibleValidationAgreement() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Константин");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldVisibleValidationNameIfEmptyFields() {
        open("http://localhost:9999");
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldVisibleValidationPhoneIfNameEmptyFields() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Константин");
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}
