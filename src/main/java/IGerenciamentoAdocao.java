/**
 * Interface que define as operações de gerenciamento
 * do sistema de adoção de animais.
 */
public interface IGerenciamentoAdocao {

    /**Cadastra um novo animal no sistema.*/
    void cadastrarAnimal(Animal animalNovo);

    /**Cadastra uma nova pessoa no sistema*/
    void cadastrarPessoa(Pessoa pessoaNova);

    /**Realiza uma adoção de um animal.*/
    void realizarAdocao(Pessoa pessoa, Animal animal, String data, int codigo);

    /**Lista os animais disponíveis para adoção.*/
    String listarAnimaisDisponiveis();

    /**Lista as pessoas cadastradas no sistema.
     */
    String listarPessoasCadastradas();

    /**Lista todas as adoções realizadas.*/
    String listarAdocoes();

    /**Remove um animal pelo código.*/
    void removerAnimal(int codigoAnimal);

    /**Remove uma pessoa pelo CPF.*/
    void removerPessoa(String cpfPessoa);

    /**Remove uma adoção pelo código.*/
    void removerAdocao(int codigoAdocao);

    /**Consulta um animal pelo código.*/
    Animal consultarAnimal(int codigoAnimal);

    /**Consulta uma pessoa pelo CPF.*/
    Pessoa consultarPessoa(String cpfPessoa) throws PessoaNaoExisteException;

    /**Consulta uma adoção pelo código.*/
    Adocao consultarAdocao(int codigoAdocao);

}