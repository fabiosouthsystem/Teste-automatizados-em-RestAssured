package Rest.automacao.api.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {


    SelenideElement botaoSicrediLogin = $(byText("Sicredi Login"));
    SelenideElement campoUsuario = $(byXpath("//input[@name='username']"));
    SelenideElement campoSenha = $(byXpath("//input[@name='password']"));
    SelenideElement botaoEntrar = $(byXpath("//input[@name='login']"));

    public LoginPage clicarBotaoSicrediLogin() {
        botaoSicrediLogin.shouldBe(visible).click();
        return page(this);
    }

    public LoginPage preencherCampoUsuario(String usuario) {
        campoUsuario.shouldBe(visible).setValue(usuario);
        return page(this);
    }

    public LoginPage preencherCampoSenha(String senha) {
        campoSenha.shouldBe(visible).setValue(senha);
        return page(this);
    }

    public void clicarBotaoEntrar() {
        botaoEntrar.shouldBe(visible).click();
    }
}
