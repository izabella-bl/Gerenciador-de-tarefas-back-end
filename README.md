# Gerenciador de Tarefas

O Gerenciador de Tarefas é uma aplicação web desenvolvida em Java com Spring Boot e PostgreSQL, projetada para ajudar os usuários a organizar suas atividades e planejar suas tarefas de maneira eficiente.

## Funcionalidades Principais

- Adicionar usuários.
- Adicionar, visualizar, atualizar e excluir tarefas.
- Utilização do método To-do para gerenciamento de tarefas pendentes.
- API REST para interação com o frontend.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 2.6.4
- PostgreSQL 13
- Maven

## Requisitos do Sistema

- Java 17 ou superior instalado
- PostgreSQL 13 instalado
- Maven instalado

## Configuração do Ambiente

- **Java**: [Download e Instalação](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **PostgreSQL**: [Download e Instalação](https://www.postgresql.org/download/)
- **Maven**: [Download e Instalação](https://maven.apache.org/install.html)

## Configuração do Projeto

1. Clone este repositório para o seu ambiente local.

2. Configure as propriedades do banco de dados no arquivo `application.properties`.

3. Nota: O Spring Boot pode gerar automaticamente as tabelas do banco de dados com base nas entidades JPA definidas no projeto. Portanto, você não precisa criar scripts SQL manualmente para criar as tabelas. Certifique-se de que as configurações do banco de dados estão corretas no arquivo   application.properties ou application.yml.Se precisar personalizar a criação das tabelas, você pode optar por criar seus próprios scripts SQL. Para isso, deixei os possíveis scripts na pasta scripts do projeto, onde você pode encontrar exemplos de criação de tabelas e inserção de dados iniciais.

4. Importe o projeto em sua IDE preferida.

## Execução do Projeto

1. Na raiz do projeto, execute o seguinte comando para compilar o projeto:
   ```bash
   mvn clean install
   ```
2. Em seguida, execute o seguinte comando para iniciar o aplicativo:
   ```bash
   java -jar target/tarefas.jar
   ```

ou por alguma IDE:

Para executar o projeto através de sua IDE, siga os passos abaixo:

1. Importe o Projeto: Abra sua IDE (por exemplo, IntelliJ IDEA, Eclipse, NetBeans) e importe o projeto clonado do repositório. Normalmente, você pode fazer isso selecionando a opção "Importar Projeto" ou "Abrir Projeto" no menu da IDE e navegando até o diretório onde o projeto está localizado.

2. Configurações do Banco de Dados: Certifique-se de que as configurações do banco de dados estão corretas no arquivo application.properties ou application.yml. Isso inclui o URL do banco de dados, nome de usuário e senha.

3. Execução do Projeto: Dentro da IDE, localize a classe principal do projeto, que geralmente é anotada com @SpringBootApplication. Esta classe é responsável por iniciar o aplicativo Spring Boot. No IntelliJ IDEA, por exemplo, você pode clicar com o botão direito do mouse na classe principal e selecionar "Run" ou "Debug" para iniciar o aplicativo.

4. Acompanhamento da Execução: Após iniciar o aplicativo, você poderá ver as mensagens de log na janela de console da IDE, indicando que o aplicativo está sendo executado. Verifique se não há erros no log e aguarde até que o aplicativo seja iniciado completamente.

5. Parada do Aplicativo: Para parar a execução do aplicativo, você pode interrompê-lo diretamente na IDE, clicando no botão "Stop" ou "Terminate" na barra de ferramentas da IDE. Isso encerrará o processo do aplicativo.

O aplicativo estará disponível em `http://localhost:8099`.

## Documentação da API

A documentação da API está disponível em `http://localhost:8099/swagger-ui.html`. Esta documentação fornece detalhes sobre os endpoints disponíveis, parâmetros de solicitação, respostas e exemplos de uso.




