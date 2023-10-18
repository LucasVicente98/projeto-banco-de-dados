## Sistema de Gerenciamento de Armazéns Logísticos

### Introdução

O Sistema de Gerenciamento de Armazéns Logísticos é um projeto desenvolvido em Java, utilizando um banco de dados Oracle para persistência de dados. O sistema tem como objetivo facilitar o controle e organização de armazéns, produtos e responsáveis associados.

O projeto não faz uso de frameworks para operações CRUD, sendo implementadas manualmente, e utiliza blocos try-catch para tratamento de exceções durante as interações com o banco de dados.

**O projeto foi desenvolvido para a matéria de Banco de Dados da faculdade FAESA, Professor Howard Roatti.**

**Semestre 02/2023**

### Tecnologias Utilizadas

- **Linguagem**: Java
- **Banco de Dados**: Oracle
- **Framework**: Nenhum (Operações CRUD foram implementadas manualmente)
- **Manipulação de Banco de Dados**: JDBC (Java Database Connectivity)

### Estrutura do Projeto

O projeto é composto por classes Java que representam as entidades principais, como Armazém, Produto e Responsável. Além disso, foram implementadas classes DAO (Data Access Object) para interação com o banco de dados Oracle.

### Operações CRUD e Funcionalidades Específicas

O sistema oferece operações básicas de criação, leitura, atualização e exclusão para Armazéns, Produtos e Responsáveis. Além disso, foram implementadas funcionalidades específicas, como a vinculação de responsáveis a armazéns e consultas especiais para exibir informações detalhadas.



**Diagrama ER**

![Tela Inicial](C:\Users\lucas\Desktop\projeto-banco-de-dados\DIAGRAMA_ER_PROJETO.jpeg)



### Armazém

A classe `Armazem` representa um armazém no sistema. Possui atributos como ID, nome, localização, capacidade e uma lista de produtos associados. Além disso, há um relacionamento com a classe `Responsavel`, indicando o responsável pelo armazém.

- `armazem_id`: ID único do armazém.
- `nome`: Nome do armazém.
- `localizacao`: Localização física do armazém.
- `capacidade`: Capacidade de armazenamento do armazém.
- `responsavel`: Objeto da classe `Responsavel` que representa o responsável pelo armazém.
- `produtos`: Lista de objetos da classe `Produto` associados ao armazém.

### Produto

A classe `Produto` representa um produto armazenado no sistema. Cada produto possui um ID único, nome, quantidade em estoque, valor unitário e está associado a um armazém específico.

- `produto_id`: ID único do produto.
- `nome`: Nome do produto.
- `quantidade`: Quantidade disponível em estoque.
- `valorUnitario`: Valor unitário do produto.
- `armazem`: Objeto da classe `Armazem` que indica o armazém ao qual o produto está associado.

### Responsável

A classe `Responsavel` representa um responsável no sistema, como um gerente de armazém. Cada responsável possui um ID único, nome e telefone de contato.

- `responsavel_id`: ID único do responsável.
- `nome`: Nome do responsável.
- `telefone`: Número de telefone de contato do responsável.

Essas classes formam a base do sistema de gerenciamento de armazéns, proporcionando a estrutura necessária para representar armazéns, produtos e responsáveis, bem como os relacionamentos entre eles. O uso de classes Java e o mapeamento de objetos para o banco de dados facilitam a manipulação e persistência dos dados no contexto do projeto.



### BaseDAO

A classe `BaseDAO` é responsável por fornecer uma conexão com o banco de dados Oracle. Ela implementa um método protegido chamado `getConnection`, que utiliza o driver JDBC do Oracle para estabelecer uma conexão com o banco de dados local. As credenciais de conexão (URL, usuário e senha) estão hardcoded no método para simplificar o exemplo.

- `getConnection()`: Método protegido que retorna uma conexão com o banco de dados Oracle. Lança uma exceção SQLException em caso de falha na obtenção da conexão ou carregamento do driver.

### FactoryDAO

A interface `FactoryDAO` define um conjunto de operações básicas que devem ser implementadas por classes que desejam interagir com o banco de dados. Essas operações incluem a obtenção de um objeto por ID, a obtenção de todos os objetos, a criação, atualização e exclusão de objetos.

- `getById(int id)`: Obtém um objeto do tipo T (genérico) com base no ID fornecido.
- `getAll()`: Retorna uma lista de todos os objetos do tipo T.
- `create(T entity)`: Cria um novo registro no banco de dados com base no objeto fornecido.
- `update(T entity)`: Atualiza um registro no banco de dados com base no objeto fornecido.
- `delete(int id)`: Exclui um registro do banco de dados com base no ID fornecido.

Essa interface é genérica, permitindo que seja parametrizada com diferentes tipos de entidades, como `Armazem`, `Produto`, ou `Responsavel`. As classes que implementam essa interface devem fornecer a lógica específica para interagir com o banco de dados para cada operação mencionada.



### ArmazemDAO

- `getById(int id)`: Obtém um objeto `Armazem` pelo ID no banco de dados.
- `getAll()`: Retorna uma lista de todos os objetos `Armazem` no banco de dados, ordenados por ID.
- `create(Armazem entity)`: Cria um novo registro de `Armazem` no banco de dados.
- `update(Armazem entity)`: Atualiza as informações de um `Armazem` existente no banco de dados.
- `delete(int id)`: Exclui um `Armazem` do banco de dados com base no ID.
- `vincularResponsavel(int armazemId, int responsavelId)`: Vincula um responsável a um armazém no banco de dados.
- `mapResultSetToArmazem(ResultSet resultSet)`: Converte um resultado do banco de dados (`ResultSet`) para um objeto `Armazem`.
- `countArmazens()`: Retorna o número total de registros na tabela `armazem` no banco de dados.

### ResponsavelDAO

- `getById(int id)`: Obtém um objeto `Responsavel` pelo ID no banco de dados.
- `getAll()`: Retorna uma lista de todos os objetos `Responsavel` no banco de dados, ordenados por ID.
- `getResponsaveisComArmazens()`: Retorna uma lista de objetos `ResponsavelArmazemDTO`, representando responsáveis e os armazéns associados a eles.
- `create(Responsavel entity)`: Cria um novo registro de `Responsavel` no banco de dados.
- `update(Responsavel entity)`: Atualiza as informações de um `Responsavel` existente no banco de dados.
- `delete(int id)`: Exclui um `Responsavel` do banco de dados com base no ID.
- `mapResultSetToResponsavel(ResultSet resultSet)`: Converte um resultado do banco de dados (`ResultSet`) para um objeto `Responsavel`.
- `countResponsaveis()`: Retorna o número total de registros na tabela `responsavel` no banco de dados.

### ProdutoDAO

- `getById(int id)`: Obtém um objeto `Produto` pelo ID no banco de dados.
- `getAll()`: Retorna uma lista de todos os objetos `Produto` no banco de dados, ordenados por ID.
- `create(Produto entity)`: Cria um novo registro de `Produto` no banco de dados.
- `update(Produto entity)`: Atualiza as informações de um `Produto` existente no banco de dados.
- `delete(int id)`: Exclui um `Produto` do banco de dados com base no ID. Antes da exclusão, verifica se o produto está associado a um armazém.
- `mapResultSetToProduto(ResultSet resultSet)`: Converte um resultado do banco de dados (`ResultSet`) para um objeto `Produto`.
- `countProdutos()`: Retorna o número total de registros na tabela `produto` no banco de dados.
- `exibirValorTotalPorArmazem()`: Exibe o valor total dos itens em cada armazém.

### ResponsavelArmazemDTO

- `getResponsavelId()`: Obtém o ID do responsável.
- `getNomeResponsavel()`: Obtém o nome do responsável.
- `getTelefoneResponsavel()`: Obtém o telefone do responsável.
- `getNomesArmazens()`: Obtém os nomes dos armazéns associados ao responsável.





## Main Class: Sistema de Gerenciamento de Armazéns Logísticos

A classe `Main` é o ponto de entrada para o Sistema de Gerenciamento de Armazéns Logísticos. Ela coordena a interação com o usuário por meio de um menu interativo, fornecendo funcionalidades para manipulação de armazéns, responsáveis e produtos. Vamos explorar detalhadamente os aspectos dessa classe:

### Atributos

- **`armazemDAO`**: Uma instância de `ArmazemDAO` que lida com operações no banco de dados relacionadas a armazéns.
- **`responsavelDAO`**: Uma instância de `ResponsavelDAO` responsável pelas operações no banco de dados relacionadas a responsáveis.
- **`produtoDAO`**: Uma instância de `ProdutoDAO` encarregada das operações no banco de dados relacionadas a produtos.
- **Contadores de Registros (`countArmazens`, `countResponsaveis`, `countProdutos`)**: Armazenam o número total de armazéns, responsáveis e produtos no banco de dados, respectivamente.

### Métodos Principais

- **`main(String[] args)`**: Este é o método principal que inicia o programa. Ele inicializa os DAOs, exibe o menu principal e gerencia as escolhas do usuário até que ele decida sair do programa.
- **Métodos de Menu (`menuArmazem`, `menuResponsavel`, `menuProduto`)**: Cada um desses métodos representa um submenu específico para operações relacionadas a armazéns, responsáveis e produtos, respectivamente. Eles chamam outros métodos conforme necessário.

### Métodos de Operações CRUD

#### Métodos de Criação e Listagem

- **Cadastro de Armazém (`cadastrarArmazem`)**: Permite ao usuário cadastrar um novo armazém, solicitando informações como nome, localização e capacidade.
- **Listagem de Armazéns (`listarArmazens`)**: Recupera e exibe a lista de todos os armazéns cadastrados.
- **Cadastro de Responsável (`cadastrarResponsavel`)**: Permite ao usuário cadastrar um novo responsável, solicitando informações como nome e telefone.
- **Listagem de Responsáveis (`listarResponsaveis`)**: Recupera e exibe a lista de todos os responsáveis cadastrados.
- **Cadastro de Produto (`cadastrarProduto`)**: Permite ao usuário cadastrar um novo produto, solicitando informações como nome, quantidade, valor unitário e associando-o a um armazém existente.
- **Listagem de Produtos (`listarProdutos`)**: Recupera e exibe a lista de todos os produtos cadastrados, incluindo informações sobre o armazém de armazenamento.

#### Métodos de Atualização e Exclusão

- **Atualização de Armazém (`atualizarArmazem`)**: Permite ao usuário atualizar informações de um armazém existente, como nome, localização e capacidade.
- **Exclusão de Armazém (`excluirArmazem`)**: Permite ao usuário excluir um armazém existente.
- **Atualização de Responsável (`atualizarResponsavel`)**: Permite ao usuário atualizar informações de um responsável existente, como nome e telefone.
- **Exclusão de Responsável (`excluirResponsavel`)**: Permite ao usuário excluir um responsável existente.
- **Atualização de Produto (`atualizarProduto`)**: Permite ao usuário atualizar informações de um produto existente, como nome, quantidade, valor unitário e associá-lo a um armazém diferente.
- **Exclusão de Produto (`excluirProduto`)**: Permite ao usuário excluir um produto existente.

#### Operações Específicas

- **Vinculação de Responsável a Armazém (`vincularResponsavelAoArmazem`)**: Permite ao usuário vincular um responsável a um armazém específico.
- **Consulta de Responsáveis com Armazéns (`exibirConsultaResponsaveisComArmazens`)**: Exibe uma consulta especial listando responsáveis e os armazéns associados a cada um.
- **Consulta de Valor Total por Armazém (`exibirConsulta`)**: Exibe uma consulta especial que mostra o valor total dos produtos agrupados por armazém.

### Funcionalidades Adicionais

- **Atualização de Contadores (`countArmazens`, `countResponsaveis`, `countProdutos`)**: Os contadores são atualizados dinamicamente para refletir o número atual de registros no banco de dados, proporcionando uma visão geral do sistema.
- **Internacionalização (`Locale.setDefault(Locale.US)`)**: A internacionalização é configurada para o formato dos números, garantindo consistência em diferentes locais.

### Utilização de Scanner

O código utiliza a classe `Scanner` para interagir com o usuário, permitindo a entrada de dados a partir da linha de comando.

### Estrutura Modular

A classe `Main` segue uma estrutura modular, dividindo a lógica em métodos específicos para facilitar a leitura, manutenção e expansão do código. Cada método aborda uma funcionalidade específica, promovendo um design claro e organizado.



Esta classe representa o núcleo do sistema, oferecendo uma interface intuitiva para os usuários interagirem com o Sistema de Gerenciamento de Armazéns Logísticos. Sua estrutura modular, manipulação eficaz de entradas do usuário e operações CRUD completas fazem dela uma peça fundamental para a eficiência e robustez do sistema.



## Considerações Finais

O Sistema de Gerenciamento de Armazéns Logísticos representa uma solução eficaz para o controle e organização de armazéns, produtos e responsáveis. Desenvolvido em Java e utilizando um banco de dados Oracle para persistência, o projeto oferece operações CRUD e funcionalidades específicas para a gestão logística.

Ao longo do desenvolvimento, destacou-se a simplicidade e a clareza do código, possibilitando uma compreensão rápida das operações realizadas. A ausência de frameworks para operações CRUD e a utilização de blocos try-catch para tratamento de exceções durante o estabelecimento das conexões são características que contribuíram para a eficiência e confiabilidade do sistema.

### Desafios e Aprendizados

Durante o desenvolvimento, enfrentei desafios significativos, como a implementação de operações CRUD sem o uso de frameworks e a gestão de exceções em interações com o banco de dados. Esses desafios proporcionaram uma oportunidade valiosa para aprimorar minhas habilidades em Java e fortalecer o entendimento sobre manipulação de dados em um ambiente persistente.

A internacionalização foi uma escolha acertada para garantir a consistência nos formatos de números, tornando o sistema mais adaptável a diferentes localidades.

### Melhorias Futuras

Como em qualquer projeto em constante evolução, identifiquei áreas para melhorias e expansões. A implementação do padrão MVC, a adição de consultas específicas, a capacidade de atualização seletiva de atributos e a inclusão de testes unitários são melhorias que podem aprimorar significativamente a qualidade e a flexibilidade do sistema.

### Considerações Acadêmicas

Este projeto foi desenvolvido como parte de um trabalho acadêmico, proporcionando uma oportunidade única para aplicar os conhecimentos adquiridos em sala de aula na prática. A experiência prática enriqueceu meu entendimento sobre o desenvolvimento de sistemas em Java e a integração com bancos de dados Oracle.

Em conclusão, o Sistema de Gerenciamento de Armazéns Logísticos é uma ferramenta funcional que atende aos requisitos propostos. Com as melhorias sugeridas e um compromisso contínuo com a qualidade, o sistema está preparado para enfrentar os desafios e demandas futuras da gestão logística.

## Imagens de Execução do Sistema

### Tela Inicial
![Tela Inicial](C:\Users\lucas\Desktop\projeto-banco-de-dados\imagens_projeto\tela_inicial.png)

### Menu de Armazém
![Menu de Armazém](C:\Users\lucas\Desktop\projeto-banco-de-dados\imagens_projeto\menu_armazem.png)

### Listagem de Armazéns
![Listagem de Armazéns](C:\Users\lucas\Desktop\projeto-banco-de-dados\imagens_projeto\listagem_armazem.png)

### Menu de Responsável
![Menu de Responsável](C:\Users\lucas\Desktop\projeto-banco-de-dados\imagens_projeto\menu_responsavel.png)

### Listagem de Responsáveis e Armazéns Associados
![Listagem de Responsáveis e Armazéns Associados](C:\Users\lucas\Desktop\projeto-banco-de-dados\imagens_projeto\listagem_responsavel_armazem.png)

### Menu de Produto
![Menu de Produto](C:\Users\lucas\Desktop\projeto-banco-de-dados\imagens_projeto\menu_produto.png)

### Listagem de Valor Total por Armazém
![Listagem de Valor Total por Armazém](C:\Users\lucas\Desktop\projeto-banco-de-dados\imagens_projeto\listage_valor_total_por_armazem.png)

### Fim da Execução
![Fim da Execução](C:\Users\lucas\Desktop\projeto-banco-de-dados\imagens_projeto\fim_execucao.png)



**Acesse o meu perfil no** [Linkedin](https://www.linkedin.com/in/lucas-leite-vicente-136568207/).