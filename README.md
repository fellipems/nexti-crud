# NEXTI-CRUD
#### Projeto em desenvolvimento para desafio Fullstack p/ Nexti. Backend Java Spring Boot e Frontend Angular.

# Resumo

- Desenvolver uma aplicação web responsável por gerenciar clientes, pedidos e produtos;
- Sistema onde possamos cadastrar os clientes, produtos e pedidos para ter maior controle sobre os mesmos;
- O analista de negócio reuniu mais informações e a seguinte demanda foi gerada para ser executada que está descrita logo abaixo;
- O projeto foi dividido em camadas controllers, Entities, DTOs, Services e Repository. Implementarei, ainda, a camada DAO.

# Demanda

- **Eu como cliente quero cadastrar clientes e produtos e após isso criar pedidos para os clientes contendo todas as informações relevantes ao pedido**
  - Cliente, Produto e Pedido:
    - Incluir, excluir, atualizar e listar.
  - Exclusivamente no Pedido:
    - Vincular e remover cliente de um pedido;
    - Vincular e remover produto(s) de um pedido;

# Tecnologias usadas

1. `Java 8`
2. `Spring Boot 2.4.5`
3. `TypeScript (Angular)`
4. `Postman`
5. `MySQL Workbench`
   > Primeiramente foi usado o MySQL, por questões de erros nele, usei o H2 para adiantar o serviço. Postgres não estava conseguindo configurar por erros no serviço.
  
# O que estudar mais?

- *Testes*;
- *Relacionamentos*(@OneToOne, OneToMany, ManyToMany, ManyToOne);
- *Documentação com Swagger*;
- *Exceptions*
- *OAuth2*
