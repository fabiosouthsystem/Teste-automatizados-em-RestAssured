package Rest.automacao.frontend;

import com.codeborne.selenide.Configuration;
import Rest.automacao.api.pageobject.IsencaoAnuidadePage;
import Rest.automacao.api.pageobject.LoginPage;
import Rest.automacao.api.util.EnvironmentRuntimeLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class CriaIsencaoAnuidade {
    static final String CPF = "15327371069";
    static final String ENV = EnvironmentRuntimeLoader.get();

    @BeforeEach
    void iniciar() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        Configuration.headless = true; // Set headless mode
        open("https://backendcrm." + ENV + ".sicredi.io/isencao-anuidade/?cpf=" + CPF);
    }

    @Test
    void testarFluxoIsencaoAnuidade() {
        LoginPage login = new LoginPage();
        login.preencherCampoUsuario("bruna_msouza")
                .preencherCampoSenha("teste123")
                .clicarBotaoEntrar();


        var isencaoAnuidadePage = new IsencaoAnuidadePage();
        isencaoAnuidadePage.selecionarCartao();

        if (isencaoAnuidadePage.existeDivCancelar()) {
            isencaoAnuidadePage.irParaCancelamentoDeIsencao()
                    .cancelarIsencao();
            refresh();
            sleep(3000);
            isencaoAnuidadePage.selecionarCartao();
        }
        if (isencaoAnuidadePage.existeDivSolicitarNovaIsencao()) {
            isencaoAnuidadePage.irParaSolicitarNovaIsencao();
        }


        isencaoAnuidadePage
                .selecionarPeriodo()
                .confirmarIsencao();
        sleep(3000);

    }

    @AfterEach
    void deveriaFecharTelaeBrowser() {
        closeWindow();
        closeWebDriver();
    }
}
