# Instalação da aplicação

Primeiramente, faça o clone do repositório:
```
git clone -b Daniel-Pereira https://github.com/rafaellarosa07/desafio-tecnico.git
```
Na branches ``Daniel-Pereira``

Feito isso, acesse o projeto:
```
cd desafio-tecnico
```
É preciso compilar o código e baixar as dependências do projeto:
```
mvn clean package
```
Finalizado esse passo, vamos iniciar a aplicação:
```
mvn spring-boot:run
```
Pronto. A aplicação está disponível em http://localhost:8080/transacao
```
Tomcat started on port(s): 8080 (http)
Started AppConfig in xxxx seconds (JVM running for xxxx)
```

# Setup da aplicação com docker

## Pré-requisito

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:

```
Java 17
Docker
Maven
```

Para fazer o docker build executar o comando abaixo:
```
docker build -t estatiticas-transacao:lastest .
```
Para dar o start da aplicação no docker executar o comando abaixo:
```
docker run -p 8080:8080 estatiticas-transacao:lastest
```

# Para testar os Endpoints seguir os parametros abaixo:

Para Salvar:

POST : http://localhost:8080/transacao

No parametros em JSON

```
{
    "valor": 300.00,
    "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```

Para Buscar:

GET : http://localhost:8080/transacao

Ele retorna em JSON:

```
{
    "count": 1,
    "sum": 300.0,
    "avg": 300.0,
    "min": 300.0,
    "max": 300.0
}
```

Para deletar todos os dados de transações:

DELETE : http://localhost:8080/transacao

