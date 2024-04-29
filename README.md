# 📝 Sistema de Notas Fiscais

Sistema de notas fiscais que integra com o ERP do cliente para que seja possível receber as notas fiscais e seus respectivos dados de pagamento.

## ⚙️ Principais tecnologias
- **Java 17**
- **Git**
- **Spring Framework**
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger OpenAPI**
- **Kafka**
- **Docker**
- **Conduktor**

## 👨🏻‍🏫 Instruções de execução
Para executar a aplicação, será preciso rodar o Apache Kafka, juntamente com o Schema Registry. No arquivo docker-compose já se encontram todas as dependências necessárias para o seu funcionamento.
Na pasta ***"nota-fiscal-api/docker"*** execute o seguinte comando:
```
docker-compose up -d 
```

Você pode monitorar o Kafka usando alguma ferramenta de monitoração, neste projeto está sendo usado o [Conduktor](https://www.conduktor.io/).
Para isso, é necessário configurar o Cluster do Conduktor

1. Crie um cluster Kafka
   ![image](https://github.com/brunoabneves/nota-fiscal-api/assets/29290457/19734a32-21cc-4c87-9df6-085282074192)
   
2. Adicione as configurações do cluster como nome, Bootstrap Server, Zookeeper (o valor das propriedades estão no application.properties)
   ![image](https://github.com/brunoabneves/nota-fiscal-api/assets/29290457/1db2ede8-3f1a-4eda-82b9-065913dbb7e7)
   
3. Adicione a URL do Schema Registry e defina a Segurança como ***None***.
   ![image](https://github.com/brunoabneves/nota-fiscal-api/assets/29290457/e3d26a8a-e910-4591-9125-277125708fcb)

4. Após criado o cluster, execute ***docker-compose*** para poder usar o Conduktor.

## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente

`DATABASE_URL`

`DATABASE_PASSWORD`

`DATABASE_USERNAME`

`NF_VALIDATOR_API`

`SERVER_PORT`


