# Sistema de Adoção de Animais

## Descrição

Sistema de gerenciamento de adoção de animais desenvolvido em Java com
interface gráfica Swing. Permite cadastrar animais e pessoas, realizar
adoções, consultar e remover registros, além de salvar e recuperar todo o
estado do sistema em arquivo.

Os dados são armazenados em estruturas `HashMap`, proporcionando buscas
rápidas por código do animal, CPF da pessoa e código da adoção. O acesso a
todas as funcionalidades é feito por uma interface (`IGerenciamentoAdocao`)
que funciona como uma fachada (padrão **Façade**), escondendo da interface
gráfica os detalhes de como os dados são armazenados internamente.

## Funcionalidades

- Cadastro de animais.
- Cadastro de pessoas.
- Realização de adoções.
- Consulta de animais por código.
- Consulta de pessoas por CPF.
- Consulta de adoções por código.
- Listagem de animais disponíveis para adoção (implementada com **Streams**).
- Listagem de pessoas cadastradas (implementada com **Streams**).
- Listagem das adoções realizadas (implementada com **Streams**).
- Remoção de animais, pessoas e adoções.
- Salvar dados do sistema em arquivo.
- Recuperar dados do sistema a partir do arquivo salvo.

## Interface com o usuário

A interface (Swing) apresenta:
- Uma **barra de menu** (`JMenuBar`) no topo da janela, com um menu para
  cada área do sistema (Animais, Pessoas, Adoções, Dados) e seus respectivos
  itens de menu — incluindo a opção de salvar dados.
- Um painel inicial com "cards" que reúnem os mesmos atalhos em botões, para
  facilitar o uso.

## Persistência

A persistência é feita por meio da classe `GravadorDeDados`, que grava e
recupera o objeto `GerenciamentoAdocao` inteiro (com todos os animais,
pessoas e adoções) usando serialização nativa do Java
(`ObjectOutputStream`/`ObjectInputStream`). Seus métodos (`gravar` e
`recuperar`) lançam `IOException`. A classe `GerenciamentoAdocao` expõe essa
funcionalidade como parte da fachada, através dos métodos `salvarDados()` e
`recuperarDados()`.

## Tecnologias utilizadas

- Java
- Programação Orientada a Objetos (POO)
- Swing (interface gráfica, com `JMenuBar`)
- HashMap
- Streams (`filter`, `map`, `collect`)
- Enum
- Interface (padrão Façade)
- Tratamento de exceções personalizadas
- Serialização de objetos (persistência em arquivo)
- JUnit 5 (testes automatizados)

## Estrutura do projeto

O sistema é composto pelas seguintes classes:

- `Animal` – representa os animais disponíveis para adoção.
- `Pessoa` – representa os adotantes.
- `Adocao` – registra uma adoção realizada.
- `GerenciamentoAdocao` – implementa `IGerenciamentoAdocao`; responsável
  pelas operações e regras de negócio do sistema, incluindo persistência.
- `IGerenciamentoAdocao` – interface (fachada) com os métodos do
  gerenciamento.
- `GravadorDeDados` – responsável pela gravação/recuperação de dados em
  arquivo.
- `ControllerAdocao` – intermedia as chamadas entre a interface gráfica e
  `GerenciamentoAdocao`.
- `SistemaAdocaoPrincipal` – janela principal (Swing), com barra de menu e
  cards de atalho.
- `AdocaoPrincipal` – classe com o método `main`.
- Exceções personalizadas: `AnimalJaAdotadoException`,
  `AnimalNaoEncontradoException`, `AnimalCodigoJaExisteException`,
  `PessoaNaoExisteException`, `PessoaJaCadastradaException`,
  `AdocaoCodigoJaExisteException`, `AdocaoNaoEncontradaException`.

## Testes

A classe `GerenciamentoAdocaoTest` (em `src/test/java`) usa JUnit 5 e
exercita todos os métodos de `IGerenciamentoAdocao`: cadastro, consulta,
listagem (incluindo os métodos implementados com Streams), remoção e
persistência (salvar/recuperar dados).

## Diagrama de classes

O diagrama de classes UML do sistema, incluindo a interface, a classe que a
implementa e as principais entidades, está em
[`diagrama-classes.md`](./diagrama-classes.md), junto com uma análise de
acoplamento e coesão.