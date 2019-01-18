Plano de publicacao
    
    Sobre o projeto:
    Todo o procedimento foi feito considerando uma máquina rodando o Ubuntu como sistema operacional.
    O serviço foi desenvolvido na linguagem JAVA e utilizando o SpringBoot.
    Para armazenar os dados, utilizamos o MySql. Optei por ele por ser um banco de dados muito utilizado, com uma grande comunidade e muita documentação disponível.

    Pre-requisitos:
        Ter o Java 8 instalado
        Ter o git instalado: 
            - Comando: sudo apt-get install git

        Ter o maven instalado:
            - Comando: sudo apt-get install maven

        Ter o mysql 5 instalado:
            - Para instalar: sudo apt-get install mysql-server
            - Para configurar: sudo mysql_secure_installation
            - Para iniciar o serviço caso já não esteja: sudo systemctl start mysql

    Configurações da publicação:
        Banco:
        Executar os scripts abaixo:
            - Criar o database: CREATE DATABASE client_admin;
            - Criar o usuario: CREATE USER 'appClientRegister'@'localhost' IDENTIFIED BY 'passAppClient2132';
            - Dar as permissões: GRANT ALL ON client_admin.* TO 'appClientRegister'@'localhost';

        Estrutura de pastas
            - Executar o comando sudo mkdir /projects/log/clientRegister
            - Dar permissão full para a pasta: sudo chmod 777 -R /projects

        Aplicação:

            O projeto se encontra em : https://github.com/BrunoHenriqueSouza/uoltest.git

            - Baixar o projeto do git em /home/<user>/projects
            - Acessar o diretório /home/<user>/projects/uoltest/clientRegister
            - Executar o comando do maven: mvn clean install
            - Após o build, executar: java -jar /home/<user>/projects/uoltest/clientRegister/target/clientRegister-0.0.1-SNAPSHOT.jar

    Informações sobre os retornos dos serviços:

        /new :
            201 caso o servico de tudo certo
            422 caso tenhamos algum problema na criacao do cliente
            400 se o json for invalido

        /update
            200 caso a atualizacao ocorra com sucesso
            422 caso ocorra algum problema na atualizacao
            400 se o json for invalido

        /{id}
            200 caso encontre o cliente solicitado
            422 caso nao encontre

        /all
            200 caso encontre o cliente solicitado
            422 caso nao encontre

        /delete
            200 caso delete o cliente com sucesso
            422 caso ocorra algum erro

    Testando a Aplicação
        Nesse ponto deixarei abaixo um conjuto de chamadas que podem ser executadas no console, mas também há um projeto do postman no diretório *PostmanProject* do projeto para facilitar os testes e deixa-los mais visuais.

        Teste 1 -   Consultando todos os clientes cadastrados:
        
                    Chamada:    curl  -X GET \
                                    http://localhost:8080/client/all \
                                    -H 'cache-control: no-cache' \
                                    -H 'postman-token: 993bcf69-c781-52c8-4484-2ed7ea56d98e'

                    Resultado esperado: O status 201 com o json conforme abaixo:
                                {"errorCode":null,"message":null,"clients":[]}


        Teste 2 -   Vamos inserir o cliente João da Silva tendo 25 anos de idade:

                    Chamada:    curl -X POST \
                                    http://localhost:8080/client/new \
                                    -H 'cache-control: no-cache' \
                                    -H 'content-type: application/json' \
                                    -H 'postman-token: d2954d6b-1f24-b7d7-f55f-90a37c020bc1' \
                                    -H 'x-forwarded-for: 8.8.8.8' \
                                    -d '{
                                        "client": {
                                            "name": "Joao da Silva",
                                            "age": 25
                                        }
                                    }'

                    Resultado esperado: O status 200 com o json conforme abaixo:
                                {"errorCode":null,"message":null,"client":{"clientId":1,"name":"Joao da Silva","age":25,"weather":{"minTemp":"8.796666666666667","maxTemp":"15.729999999999999"}}}

        Teste 3 -   Vamos consultar novamente todos os clientes cadastrados.

                    Chamada:    curl -X GET \
                                    http://localhost:8080/client/all \
                                    -H 'cache-control: no-cache' \
                                    -H 'postman-token: ef51ec00-337f-7245-0743-7b5a06f93546'

                    Resultado esperado: O status 200 com o json conforme abaixo:
                                {"errorCode":null,"message":null,"clients":[{"clientId":1,"name":"Joao da Silva","age":25,"weather":{"minTemp":"8.796666666666667","maxTemp":"15.729999999999999"}}]}

        Teste 4 -   Vamos executar a api que busca por id do cliente.

                    Chamada:    curl -X GET \
                                    http://localhost:8080/client/1 \
                                    -H 'cache-control: no-cache' \
                                    -H 'postman-token: f3bf143a-4a9f-eec6-4874-b98b221ed3eb'

                    Resultado esperado: O status 200 com o json conforme abaixo:
                                {"errorCode":null,"message":null,"client":{"clientId":1,"name":"Joao da Silva","age":25,"weather":{"minTemp":"8.796666666666667","maxTemp":"15.729999999999999"}}}

        Teste 5 -   Vamos cadastrar a Maria Aparecida com 19 anos de idade.

                    Chamada:    curl -X POST \
                                    http://localhost:8080/client/new \
                                    -H 'cache-control: no-cache' \
                                    -H 'content-type: application/json' \
                                    -H 'postman-token: d2954d6b-1f24-b7d7-f55f-90a37c020bc1' \
                                    -H 'x-forwarded-for: 8.8.8.8' \
                                    -d '{
                                        "client": {
                                            "name": "Maria Aparecida",
                                            "age": 19
                                        }
                                    }'

                    Resultado esperado: O status 201 com o json conforme abaixo:
                                {"errorCode":null,"message":null,"client":{"clientId":3,"name":"Maria Aparecida","age":19,"weather":{"minTemp":"8.796666666666667","maxTemp":"15.729999999999999"}}}

        Teste 6 -   Vamos consultar novamente todos os clientes cadastrados.

                    Chamada:    curl -X GET \
                                    http://localhost:8080/client/all \
                                    -H 'cache-control: no-cache' \
                                    -H 'postman-token: ef51ec00-337f-7245-0743-7b5a06f93546'

                    Resultado esperado: Um json conforme o abaixo:
                                {"errorCode":null,"message":null,"clients":[{"clientId":1,"name":"Joao da Silva","age":25,"weather":{"minTemp":"8.796666666666667","maxTemp":"15.729999999999999"}},{"clientId":3,"name":"Maria Aparecida","age":19,"weather":{"minTemp":"8.796666666666667","maxTemp":"15.729999999999999"}}]}

        Teste 7 -   Vamos deletar o João da Silva.

                    Chamada:    curl -X DELETE \
                                    http://localhost:8080/client/remove/1 \
                                    -H 'cache-control: no-cache' \
                                    -H 'postman-token: 65ef47b3-1aca-9d6e-7ba7-1d80d0f58f5d'

                    Resultado esperado: O status 200 com o json conforme abaixo:
                                {"errorCode": null,"message": null,"client": null}
        
    Rollback da publicação

        Banco de dados:
            - Conectar no banco e executar o script que irá excluir o novo database:
                DROP DATABASES client_admin.

        Aplicação:
            - Para parar sua execução basta encerrar o processo do java.
            
   

    Instruções para publicação no ambiente de produção:

        Disponibilizar inicialmente, três dockers com sistema operacional Ubuntu.
            - Uma máquina terá a configuração de 4gb de memória e 20gb de espaço em disco.
                - Esta máquina deverá conter o mysql 5.7 instalado e já deve ter a configuração de backup dos dados.
                - Deverá estar configurada toda a rede para que as aplicações consigam acessar o banco.

            - Duas máquinas terão uma configuração de 2gb de memória e 6gb de espaço em disco.
                - Esta máquina será onde publicaremos o nosso serviço, então já deve ter instalado o java 8 e o maven.
                - Também deve-se mapear os ips que proverão o acesso aos serviços da aplicação.
                - Deve conter o mapeamento do banco de dados.
                - Essas máquinas deverão ser registradas em um load balancer e caso a quantidade de acessos seja muito alta, deveremos subir mais instâncias do nosso serviço para subir a demanda. 
                  Quando os acessos foram normalizados, voltamos ao estado inicial de dois conteiners.
