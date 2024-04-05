package Rest.automacao.api.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ReducaoLimitePage {

    SelenideElement comboboxDeCartao = $(byXpath("//input[@type='search']"));
    SelenideElement botaoAvançar = $(byText("Avançar"));
    SelenideElement botaoAvançarTf = $(byXpath("//span[text()='Avançar']"));
    SelenideElement botaoConfirmar = $(byText("Confirmar"));
    SelenideElement selecionamotivo = $(byXpath("//input[@type='search']"));
    SelenideElement novolimite = $(byXpath("//input[@type='text']"));


    public ReducaoLimitePage selecionarCartao() {
        comboboxDeCartao.shouldBe(Condition.visible, Duration.ofSeconds(2));
        comboboxDeCartao.unfocus();
        comboboxDeCartao.sendKeys("\uE015");
        comboboxDeCartao.sendKeys("\uE015");
        comboboxDeCartao.pressEnter();
        botaoAvançar.click();
        return this;
    }

    public ReducaoLimitePage selecionarMotivo() {
        selecionamotivo.shouldBe(Condition.visible, Duration.ofSeconds(8));
        selecionamotivo.unfocus();
        selecionamotivo.sendKeys("\uE015");
        selecionamotivo.pressEnter();
        return this;
    }

    public ReducaoLimitePage selecionarNovoLimite(Integer creditValue) {
        novolimite.shouldBe(Condition.visible, Duration.ofSeconds(5));
        novolimite.unfocus();
        novolimite.sendKeys(String.valueOf(creditValue - 100));
        botaoAvançar.click();
        /*botaoAvançarTf.click();*/
        botaoConfirmar.click();
        return this;
    }


}
