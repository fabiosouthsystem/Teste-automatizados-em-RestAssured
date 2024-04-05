package Rest.automacao.api.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

public class EnvironmentRuntimeLoader {

    /**
     * <p>Obtém o ambiente para executar na seguinte ordem, sendo o ultimo mais prioritário:
     * <ul>
     *     <li>Variável de ambiente</li>
     *     <li>Argumento de linha de comando</li>
     * </ul>
     * </p>
     * <p>
     *     Caso não informado, assume o ambiente "tst" por default.
     * </p>
     *
     * @return Ambiente para execução, default "tst"
     */
    public static String get() {

        String argument = System.getProperty("env");
        String envVar = System.getenv("env");
        String envVarUpper = System.getenv("ENV");

        return Optional.ofNullable(argument)
                .filter(StringUtils::isNotBlank)
                .or(() ->
                        Stream.of(envVar, envVarUpper)
                                .filter(StringUtils::isNotBlank)
                                .findAny())
                // garante que está em lower case
                .map(String::toLowerCase)
                .orElse("uat");
    }
}
