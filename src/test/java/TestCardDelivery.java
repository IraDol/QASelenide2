import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class TestCardDelivery {
    @BeforeEach
    void openBrowser(){
        open("http://localhost9999/");
       Configuration.holdBrowserOpen=true;
    }

    @Test
    void checkedCard() {
       // $("a[data-test-id='Город']").click();
       // $("input[@type='Город']").setValue("Москва");
            $("[placeholder=Город]").setValue("Москва");
            String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
            SelenideElement data = $("[data-test-id=date]");
            data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
            data.$("[placeholder]").setValue(inputDate);
            $("[data-test-id=name].input_type_text .input__control").setValue("Елена Прекрасная");
            $("[data-test-id=phone]").$("[name=phone]").setValue("+71112223344");
            $("[class=checkbox__box]").click();
            $$("[class=button__text]").find(Condition.exactText("Забронировать")).click();
            $("[class=notification__content]").waitUntil(Condition.visible, 15000)
                    .shouldHave(Condition.exactTextCaseSensitive("Встреча успешно забронирована на " + inputDate));

        }

    }

