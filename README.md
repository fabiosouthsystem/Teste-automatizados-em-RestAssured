# Geral

Projeto base para criação de novos projetos Springboot via devConsole.
Este projeto entrega uma aplicação java+spring funcional.

`Esta subção deve ser reescrita com base no projeto desenvolvido`

## Regras de versionamento

O projeto deve utilizar [Semantic Versioning](https://semver.org/) para definir o número de versão.
A versão deverá possuir três números inteiros divididos por '.', no seguinte formato:

MAJOR.MINOR.PATCH

* Se alterações de API que quebram a compatibilidade forem realizadas, a versão MAJOR deve ser incrementada.
* Quando novas funcionalidades que mantém a retrocompatibilidade forem adicionadas, a versão MINOR deve ser
  incrementada.
* Quando bugfixes ou hotfixes forem realizados mantendo a retrocompatibilidade, a versão PATCH deve ser incrementada.

# .gitlab-ci.yml

Arquivo responsável por orquestar a pipeline do projeto.<br>
Por padrão, a pipeline vem com as seguintes stages:<br>

1. **build e test**: O passo de build agora também é responsável pela criação da imagem Docker e execução dos testes
   unitários, possuindo configurações específicas.
    - Caso queira alterar a versão do Java, basta substituir a variável da `image` no arquivo gitlab-ci, as versões
      diponiveis estão
      em [imagens disponiveis](https://gitlab.sicredi.net/devconsole/dev-console-ci/-/blob/master/exemplos/image-disponiveis.md)
      .
    - Caso queira executar o build sem os testes unitários, veja
      o [exemplo sem testes](https://gitlab.sicredi.net/devconsole/dev-console-ci/-/blob/master/exemplos/build-notest-java17-gradle-jib.md)
2. **gate-qualidade-codigo**: Responsável por analisar a cobertura de testes do código utilizando o sonar.
3. **deploy**: Não ha necessida de alteracao, sendo assim foi encurtado e padronizado pelo arquivo do
   sicredi-devconsole-ci.yaml<br>
   deploy é realizado em:
    - Quando um commit é aplicado na `Branch Default` do projeto ocorre o deploy automática no ambiente de **
      Develop/Teste**.<br>
    - Quando um commit é aplicado em uma `Feature Branch`, a pipeline criada aguarda o play manual para o deploy no
      ambiente de **Develop/Teste**.<br>
    - Quando uma nova `Tag` é criada, por padrão ocorre o deploy automática no ambiente de **UAT/Homolog**, e a pipeline
      criada aguarda o play manual para os ambientes de **STRESS/Pre-Prod** e **Producao**.<br>







A documentação das configurações que podem ser adicionadas para a criação do container, podem ser encontradas no github
do plugin: https://github.com/GoogleContainerTools/jib

# Dockerfile

Por padrão é entregue com Plugin para GoogleJib, mas projeto pode ser migrado para utilizar Docker.

Funcionamento:
*https://docs.docker.com/engiine/reference/builder/*

Por questões de segurança não recomendamos utilizar imagens não oficiais do container.<br>
O dockerfile tem a customização feita pelos Mustaches para otimizar o tamanho da imagem com algumas alterações do
Java.<br>
*https://git.sicredi.net/gitlab/java-container-template*

# pom.xml

Para gerenciamento de dependências e build é utilizado o Maven, entregando um POM com todas as dependências mínimas para
que a API rode em todas as tecnologias com sucesso. Caso o time opte pela utilização do Gradle, é muito importante que
dependências hoje existentes no POM sejam mantidas.


Funcionamento:
*https://spring.io/projects/spring-cloud-consul*
*https://www.consul.io/*

