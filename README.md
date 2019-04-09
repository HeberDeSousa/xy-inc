# xy-inc
Teste BackEnd

Ao executar os comandos para geração de pacote no Maven (clean install) será gerado o pacote poi.jar na pasta target do projeto.
O pacote pode ser executado através do comando: $ java -jar target/poi.jar

A aplicação também pode ser executada através de uma IDE ou do Maven

Para mais informações acesse: https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html

Ao ser executada, a aplicação inicializa um pequeno banco de dados que pode ser acessado em http://localhost:8080/h2
Os serviços estarão disponíveis em:
http://localhost:8080/poi/v1/cadastrar (POST)
http://localhost:8080/poi/v1/listar (GET)
http://localhost:8080/poi/v1/listarPorProximidade (POST)

Os testes podem ser executados pelo Maven (test) ou no JUnit por uma IDE.
