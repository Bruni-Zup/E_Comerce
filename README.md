Sistema de E-Commerce

Este é um sistema básico de E-Commerce desenvolvido para praticar conceitos de programação orientada a objetos, manipulação de listas, validação de dados, tratamento de exceções e construção de API's RESTful. O sistema simula o funcionamento de uma loja virtual, com funcionalidades como o cadastro de produtos, clientes e a realização de compras, incluindo validações e manipulação de estoque.
Funcionalidades 
- Cadastro de Produtos: O sistema permite cadastrar produtos com nome, preço e quantidade. Produtos com o mesmo nome não podem ser cadastrados, o preço deve ser maior que 0 e a quantidade não pode ser negativa.
- Cadastro de Clientes: O sistema permite cadastrar clientes com nome, CPF e e-mail. O CPF e o e-mail devem ser únicos, e o CPF deve ser válido (com 11 dígitos).
- Realização de Compras: O cliente pode realizar compras enviando uma requisição JSON com o CPF e os produtos a serem comprados. O sistema verifica a disponibilidade do estoque e atualiza a quantidade dos produtos após a compra. Se algum produto estiver em falta, o sistema retorna um erro detalhado com a lista dos produtos indisponíveis.

 Endpoints da API
 1. Produtos
- GET /produtos  
  Retorna a lista de todos os produtos cadastrados.

- POST /produtos  
  Cadastra um novo produto.  
  Corpo da requisição:
  json
  {
    "nome": "Produto Exemplo",
    "preco": 100.0,
    "quantidade": 10
  }
  

 2. Clientes

- POST /clientes  
  Cadastra um novo cliente.  
  Corpo da requisição:
  json
  {
    "nome": "Cliente Exemplo",
    "cpf": "12345678900",
    "email": "cliente@exemplo.com"
  }
  

- GET /cliente/{cpf}  
  Retorna os dados de um cliente específico pelo CPF.

 3. Compras
- POST /compras  
  Registra uma nova compra.  
  Corpo da requisição:
  json
  {
    "cpf": "12345678900",
    "produtos": [
      { "nome": "Produto Exemplo" },
      { "nome": "Outro Produto" }
    ]
  }
  

- GET /compras  
  Retorna todas as compras realizadas.

- GET /compras/{cpf}  
  Retorna todas as compras realizadas por um cliente específico.

 Validações de Dados
 Produtos:
- O preço do produto deve ser maior que 0.
- A quantidade de produto deve ser maior ou igual a 0.
- Não é permitido cadastrar produtos com o mesmo nome.

 Clientes:
- O CPF deve ser válido (11 dígitos numéricos).
- O e-mail deve ser único e válido.
- Não é permitido cadastrar clientes com o mesmo CPF ou mesmo e-mail.

 Compras:
- Não será permitido comprar produtos com quantidade igual a 0 no estoque.
- Caso um produto esteja em falta, será retornado um erro 400 com a lista de produtos indisponíveis.

 Como Executar o Projeto
1. Clone este repositório para o seu computador:
   git clone https://github.com/seu-usuario/ecommerce.git
 2. Certifique-se de ter o Java (versão 11 ou superior) e o Maven instalados.
3. Compile e execute o projeto com o Maven:
    mvn spring-boot:run
 4. A aplicação será iniciada na porta padrão `8080`.

Exemplo de Uso:
1. Cadastrar Produto:  
   Envie uma requisição POST para `/produtos` com o seguinte corpo:
   json
   {
     "nome": "Produto 1",
     "preco": 50.0,
     "quantidade": 20
   }
   

2. Cadastrar Cliente:  
   Envie uma requisição POST para `/clientes` com o seguinte corpo:
   json
   {
     "nome": "João da Silva",
     "cpf": "12345678900",
     "email": "joao@exemplo.com"
   }
   

3. Realizar Compra:  
   Envie uma requisição POST para `/compras` com o seguinte corpo:
   json
   {
     "cpf": "12345678900",
     "produtos": [
       { "nome": "Produto 1" }
     ]
   }
   
 Tratamento de Erros

Quando ocorre um erro de validação, o sistema retorna uma resposta com o status HTTP 400 e uma mensagem detalhada explicando o erro. Por exemplo:
- Erro ao tentar comprar produto em falta:
  json
  {
    "erro": "Produto(s) em falta: Produto 1, Produto 2"
  }
  
