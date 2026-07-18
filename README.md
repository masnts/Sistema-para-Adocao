# Sistema de Adoção de Animais
## Descrição
Os dados são armazenados em estruturas `HashMap`, proporcionando buscas rápidas por código do animal, CPF da pessoa e código da adoção.
## Funcionalidades:

- Cadastro de animais.
- Cadastro de pessoas.
- Realização de adoções.
- Consulta de animais por código.
- Consulta de pessoas por CPF.
- Consulta de adoções por código.
- Listagem de animais disponíveis para adoção.
- Listagem de pessoas cadastradas.
- Listagem das adoções realizadas.

## Tecnologias utilizadas

- Java
- Programação Orientada a Objetos (POO)
- HashMap
- Enum
- Interface
- Tratamento de exceções

## Estrutura do projeto

O sistema é composto pelas seguintes classes:

- `Animal` – representa os animais disponíveis para adoção.
- `Pessoa` – representa os adotantes.
- `Adocao` – registra uma adoção realizada.
- `GerenciamentoAdocao` – responsável pelas operações do sistema.
- `IGerenciamentoAdocao` – interface com os métodos do gerenciamento.
- `PessoaNaoExisteException` – exceção personalizada para consultas de pessoas.

