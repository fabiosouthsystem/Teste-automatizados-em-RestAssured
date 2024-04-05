package Rest.automacao.api.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MajoracaoLimitePage {


    SelenideElement botaoAvançar = $(byText("Avançar"));
    SelenideElement botaoConfirmar = $(byText("Confirmar"));
    SelenideElement confirmarSolicitacao = $(byText("Confirmar solicitação"));
    SelenideElement novolimite = $(byXpath("//input[@name='proposal']"));
    SelenideElement selecionaInformacoesAssociadoCpf = $(byText("CPF: 153.273.710-69"));
    SelenideElement selecionaInformacoesAssociadoDn = $(byText("Data de nascimento: 01/01/1978"));


    public MajoracaoLimitePage selecionarInformacoesAssociado() {
        selecionaInformacoesAssociadoCpf.shouldBe(Condition.visible, Duration.ofSeconds(8));
        selecionaInformacoesAssociadoCpf.unfocus();
        selecionaInformacoesAssociadoCpf.click();
        selecionaInformacoesAssociadoDn.click();
        botaoConfirmar.click();
        return this;
    }

    public MajoracaoLimitePage selecionarNovoLimite(Integer creditValue) {
        novolimite.shouldBe(Condition.visible, Duration.ofSeconds(8));
        novolimite.unfocus();
        novolimite.sendKeys(String.valueOf(creditValue + 100));
        botaoAvançar.click();
        confirmarSolicitacao.click();
        return this;
    }


}
