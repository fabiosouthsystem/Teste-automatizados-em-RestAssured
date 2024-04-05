package Rest.automacao.integracao;


import Rest.automacao.api.client.Dados;
import org.junit.jupiter.api.Test;




class RetornaDadosDeApi {


    static final Integer PAGE = 2;

    @Test
    void obterDados() throws Exception {
        var response = Dados.obterDados(PAGE);

    }

}

