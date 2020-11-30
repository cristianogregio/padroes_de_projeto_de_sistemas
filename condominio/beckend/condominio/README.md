# Condomínio:Backend

Esta pasta contem os arquivos relacionados ao backend da aplicação Condominio

### Tecnologia empregadas
- Linguagem Java
- Maven
- Spring Boot:
    - Spring Data (Com MySQL Connector)
    - Spring Security (Tokenização)
    - Spring Web (Padrão MVC)
- Banco de Dados MySQL

### Como rodar a aplicação Backend ?
- Na pasta deste projeto, execute a linha de comando: 
```sh
mvn install
```

- Após baixar as dependências, é necessário que uma instância do Banco de Dados MySQL esteja rodando.
- Execute então o script de [BaselineSetup](https://github.com/cristianogregio/padroes_de_projeto_de_sistemas/blob/master/condominio/beckend/condominio/BaselineSetup.sql)

- Execute o seguinte comando para rodar a aplicação:
```sh
mvn exec:java -Dexec.mainClass="br.gov.sp.fatec.condominio.CondominioApplication"
```
