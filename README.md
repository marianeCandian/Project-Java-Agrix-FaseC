# Agrix - Fase C
Este projeto em Java é mais uma ampliação do projeto Agrix, parte do desenvolvimento de um sistema para gestão e monitoramento de fazendas. Esta é a fase C do projeto, que acrescenta novas funcionalidades e coloca em prática conhecimentos sobre autenticação e autorização com Spring Security.

## Funcionalidades
Em primeiro lugar, este projeto coloquei em prática os conhecimentos sobre persistência com Spring Data e sobre testes que adquiriu durante a seção.

Exercitar minhas habilidades na definição da arquitetura de um projeto. Este projeto foi desenvolvido focando no comportamento da sua aplicação, sem restringir tanto a forma com que ele será construído e implementado. Por isso, maior liberdade (e responsabilidade) nas decisões de como estruturá-lo.

O que foi desenvolvido?

Aplicar o conhecimento do ecossistema Spring para criar rotas da API.
Aplicar a injeção de dependência para conectar as camadas de controle, serviço e persistência.
Utilizar o Spring Data JPA para implementar entidades e repositórios para a persistência em banco de dados, bem como implementar buscas customizadas.
Utilizar o Spring Security para implementar autenticação e autorização de pessoas usuárias.
Utilizar campos de data nas rotas da API e no banco de dados.

## Conceitos Abordados
Desenvolvimento de rotas da API com Spring Boot.</p>
Injeção de dependência para conectar as camadas de controle, serviço e persistência.</p>
Utilização do Spring Data JPA para persistência em bancos de dados.</p>
Implementação de buscas customizadas com Spring Data.</p>
Utilização do Spring Security para autenticação e autorização de usuários.</p>
Utilização de campos de data com LocalDataTime.</p>

## Requisitos Desenvolvidos

### 1. Migre seu código da Fase B para este projeto (Fase C)

<details>
  <summary>Migre seu código que implementou no "Agrix - Fase B" para este projeto (Fase C)</summary><br />

Neste requisito, você deverá trazer todo o código que você implementou durante o "Agrix - Fase B" para este projeto (Agrix - Fase C).

Tome cuidado especial com:
 - `pom.xml`: o `pom.xml` inicial das Fase C não é igual ao `pom.xml` da Fase B, então você não pode simplesmente substituílo. Cuide para transferir apenas as dependências que você incluiu, sem alterar as outras configurações do projeto.
 
Durante os testes deste requisito, serão validadas as seguintes rotas:
 - GET `/farms`
 - GET `/crops`
 - GET `/fertilizers`

Você precisará trazer todo o código que você implementou na fase anterior para conseguir finalizar esta fase.

</details>

### 2. Crie a rota POST /persons

<details>
  <summary>Crie a rota POST /persons para salvar novas pessoas no banco</summary><br />

Neste requisito você vai criar uma rota para integrar a API com o código que foi adquirido e testado na fase anterior, localizado no pacote `com.betrybe.agrix.ebytr.staff`.

Se quiser, nesta fase você já pode refatorar o código desse pacote e mover ele para seguir a organização do restante da sua aplicação.

A definição da rota é:
- `/persons` (`POST`)
    - deve receber no corpo da requisição:
      - `username`
      - `password`
      - `roles` (conforme definido no enum `Role`, disponibilizado com o código)
    - deve criar a pessoa com os dados passados
    - deve responder com os campos `id`, `username` e `role` (mas não `password`)

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota POST `/persons`:

```json
{
  "username": "zerocool",
  "password": "senhasecreta",
  "role": "ADMIN"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "username": "zerocool",
  "role": "ADMIN"
}
```

</details>

</details>

### 3. Adicione autenticação no projeto

<details>
  <summary>Adicione autenticação no projeto, incluindo uma rota para login que retorna um JWT</summary><br />

Neste requisito você deverá configurar o Spring Security e implementar no seu projeto a autenticação por usuário e senha.

Você deverá:
1. Garantir acesso público (ou seja, desprotegido) aos endpoints:
    - POST `/persons` (criado acima, para permitir cadastro de novas pessoas)
    - POST `/auth/login` (será criado abaixo, para permitir login) 
2. Criar a rota POST `/auth/login`:
    - deve receber o `username` e `password` no corpo da requisição
    - deve validar os dados passados utilizando as pessoas que foram criadas pela rota `/persons`
    - caso os dados estejam incorretos, deve retornar status 403
    - caso os dados estejam corretos, deve retornar um campo `token` contendo um JWT gerado

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota POST `/auth/login` (suppondo que os dados estejam corretos):

```json
{
  "username": "zerocool",
  "password": "senhasecreta"
}
```

Exemplo de resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZ3JpeCIsInN1YiI6Im1ycm9ib3QiLCJleHAiOjE2ODk5ODY2NTN9.lyha4rMcMhFd_ij-farGCXuJy-1Tun1IpJd5Ot6z_5w"
}
```

</details>

</details>

### 4. Limitar acesso à rota GET /farms

<details>
  <summary>Limitar acesso à rota GET /farms para pessoa autenticada com role correto</summary><br />

Neste requisito você deve limitar o acesso à rota GET `/farms` para que apenas uma pessoa autenticada com role `USER`, `MANAGER` ou `ADMIN` possa acessar.

Você deve retornar status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota deve retornar a resposta usual.

</details>

### 5. Limitar acesso à rota GET /crops

<details>
  <summary>Limitar acesso à rota GET /crops para pessoa autenticada com role correto</summary><br />

Neste requisito você deve limitar o acesso à rota GET `/crops` para que apenas uma pessoa autenticada com role `MANAGER` ou `ADMIN` possa acessar.

Você deve retornar status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota deve retornar a resposta usual.

</details>

### 6. Limitar acesso à rota GET /fertilizers

<details>
  <summary>Limitar acesso à rota GET /fertilizers para pessoa autenticada com role correto</summary><br />

Neste requisito você deve limitar o acesso à rota GET `/fertilizers` para que apenas uma pessoa autenticada com role `ADMIN` possa acessar.

Você deve retornar status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota deve retornar a resposta usual.

</details>

## Por que isso é importante?
Este projeto expande ainda mais o conhecimento sobre o ecossistema Spring, especialmente no que diz respeito à implementação de autenticação e autorização com Spring Security. Além disso, permite praticar a criação de rotas da API, injeção de dependências e persistência de dados.

## Como usar
1-Clone o repositório para o seu ambiente local:
git clone https://github.com/marianeCandian/Project-Java-Agrix-FaseC.git

2-Abra o projeto em sua IDE Java preferida.

3-Execute o programa principal para iniciar a aplicação.

4-Utilize ferramentas como Postman ou curl para enviar requisições para as rotas disponíveis na API.

5-Consulte a documentação da API ou os testes unitários para obter informações detalhadas sobre como usar cada rota.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests para melhorar este projeto.
