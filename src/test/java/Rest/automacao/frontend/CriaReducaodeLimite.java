package Rest.automacao.frontend;

import io.sicredi.cartoeslimite.automacao.api.client.ContaCartaoClient;
import Rest.automacao.api.pageobject.LoginPage;
import Rest.automacao.api.pageobject.ReducaoLimitePage;
import Rest.automacao.api.util.EnvironmentRuntimeLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class CriaReducaodeLimite {

    static final Integer ID_CONTA = 913405;

    static final String CPF = "15327371069";
    static final String ENV = EnvironmentRuntimeLoader.get();
    static final Integer CREDIT_VALUE = 900000;

    @BeforeEach
    void iniciar() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        /*Configuration.headless = true; // Set headless mode*/
        open("https://backendcrm." + ENV + ".sicredi.io/reducao-limite-cartao/selecao-cartao?coop=0119&caseId=50002000007sJosAAE&cpf=" + CPF);

    }

    @Test
    void testarFluxoReducaodeLimite() {
        String environment = EnvironmentRuntimeLoader.get();

        // mocka o valor do limite total da conta para sempre iniciar um valor fixo//
        var contaCartaoClient = new ContaCartaoClient(environment);
        contaCartaoClient.alterarLimiteTotalConta(ID_CONTA, CREDIT_VALUE);
        // testando o fluxo de reducao de limite
        LoginPage login = new LoginPage();
        login
                .preencherCampoUsuario("hanna_gomes")
                .preencherCampoSenha("teste123")
                .clicarBotaoEntrar();


        var reducaoLimitePage = new ReducaoLimitePage();
        reducaoLimitePage
                .selecionarCartao();
        reducaoLimitePage
                .selecionarMotivo();
        reducaoLimitePage
                .selecionarNovoLimite(CREDIT_VALUE);

    }


    @AfterEach
    void deveriaFecharTelaeBrowser() {
        closeWindow();
        closeWebDriver();
    }
}
