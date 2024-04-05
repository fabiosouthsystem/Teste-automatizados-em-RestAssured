package Rest.automacao.api.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class IsencaoAnuidadePage {
    SelenideElement comboboxDeCartao = $(byXpath("//input[@type='search']"));
    SelenideElement divPeriodo = $(byXpath("//*[@aria-labelledby='Radio']//div"));

    SelenideElement botaoAvançar = $(byText("Avançar"));

    SelenideElement botaoCancelar = $(byText("Cancelar isenção"));
    SelenideElement botaoConfirmar = $(byText("Confirmar"));

    SelenideElement divSolicitarNovaIsencao = $(byText("Solicitar nova isenção"));

    SelenideElement botaoConfirmarCancelamento = $(byText("Confirmar cancelamento"));

    SelenideElement divAcompanhaOuCancelaIsencao = $(byText("Acompanhar ou cancelar um pedido de isenção de anuidade"));

    public IsencaoAnuidadePage selecionarCartao() {
        comboboxDeCartao.shouldBe(Condition.visible, Duration.ofSeconds(4));
        comboboxDeCartao.unfocus();
        comboboxDeCartao.sendKeys("\uE015");
        comboboxDeCartao.pressEnter();
        botaoAvançar.click();
        return this;
    }

    public IsencaoAnuidadePage selecionarPeriodo() {
        divPeriodo.shouldBe(Condition.visible, Duration.ofSeconds(4));
        divPeriodo.click();
        botaoAvançar.click();
        return this;
    }


    public IsencaoAnuidadePage confirmarIsencao() {
        botaoConfirmar.shouldBe(Condition.visible, Duration.ofSeconds(8));
        botaoConfirmar.click();
        return this;
    }


    public boolean existeDivCancelar() {

        return divAcompanhaOuCancelaIsencao.is(Condition.visible, Duration.ofSeconds(3));

    }

    public boolean existeDivSolicitarNovaIsencao() {

        return divSolicitarNovaIsencao.is(Condition.visible, Duration.ofSeconds(3));

    }

    public IsencaoAnuidadePage irParaCancelamentoDeIsencao() {
        divAcompanhaOuCancelaIsencao.shouldBe(Condition.visible, Duration.ofSeconds(5));
        divAcompanhaOuCancelaIsencao.click();
        botaoAvançar.click();
        return this;

    }

    public IsencaoAnuidadePage cancelarIsencao() {
        botaoCancelar.shouldBe(Condition.visible, Duration.ofSeconds(3));
        botaoCancelar.click();
        botaoConfirmarCancelamento.shouldBe(Condition.visible, Duration.ofSeconds(3));
        botaoConfirmarCancelamento.click();
        return this;

    }

    public IsencaoAnuidadePage irParaSolicitarNovaIsencao() {

        divSolicitarNovaIsencao.shouldBe(Condition.visible, Duration.ofSeconds(6));
        divSolicitarNovaIsencao.click();
        botaoAvançar.click();

        return this;
    }
}
