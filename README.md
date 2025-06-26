# Atividade04_REST
Atividade 04 de REST pra Sistemas Distribuídos 

 Dog Lover Hub API

## Funcionalidades
- Consome dados da Dog API (https://dog.ceo/dog-api/)
- Sistema de favoritos (adicionar, listar, remover)
- Suporte a JSON, XML e Protocol Buffers

## Execução
```bash
mvn spring-boot:run
```
Acesse: http://localhost:8080/swagger-ui.html

## Requisições
- GET /api/breeds → lista de raças
- GET /api/breeds/{breed}/images → imagens da raça
- POST /api/favorites?imageUrl=URL
- GET /api/favorites
- DELETE /api/favorites?imageUrl=URL
- GET /api/breeds.proto → retorno em Protocol Buffers
