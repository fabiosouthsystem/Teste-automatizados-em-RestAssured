package Rest.automacao.frontend;

import com.codeborne.selenide.Configuration;
import io.sicredi.cartoeslimite.automacao.api.client.ContaCartaoClient;
import Rest.automacao.api.pageobject.LoginPage;
import Rest.automacao.api.pageobject.MajoracaoLimitePage;
import Rest.automacao.api.util.EnvironmentRuntimeLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class CriaMajoracaodeLimite {

    static final Integer ID_CONTA = 913405;

    static final String CPF = "15327371069";
    static final String ENV = EnvironmentRuntimeLoader.get();
    static final Integer LIMITRESERVED_VALUE = 900000;

    @BeforeEach
    void iniciar() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        Configuration.headless = true; // Set headless mode
        open("https://backendcrm." + ENV + ".sicredi.io/cartoes/majoracao-limite?cpf=" + CPF);


    }

    @Test
    void testarFluxoMajoracaodeLimite() {
        String environment = EnvironmentRuntimeLoader.get();

        // mocka o valor do limite total da conta para sempre iniciar um valor fixo//
        var contaCartaoClient = new ContaCartaoClient(environment);
        contaCartaoClient.alterarLimiteTotalConta(ID_CONTA, LIMITRESERVED_VALUE);
        // testando o fluxo de majoracao de limite
        LoginPage login = new LoginPage();
        login
                .preencherCampoUsuario("hanna_gomes")
                .preencherCampoSenha("teste123")
                .clicarBotaoEntrar();


        var majoracaoLimitePage = new MajoracaoLimitePage();
        majoracaoLimitePage

                .selecionarInformacoesAssociado();
        sleep(3000);
        majoracaoLimitePage
                .selecionarNovoLimite(LIMITRESERVED_VALUE);

    }

    @AfterEach
    void deveriaFecharTelaeBrowser() {
        closeWindow();
        closeWebDriver();
    }
}
