import exceptions.*;

import java.io.IOException;

/**
 * Interface que define as operações de gerenciamento
 * do sistema de adoção de animais.
 *
 * Funciona como uma fachada (Façade): concentra todas as
 * operações que o sistema oferece, escondendo dos usuários
 * da interface como os dados são armazenados internamente.
 */
public interface IGerenciamentoAdocao {

    /**
     * Cadastra um novo animal no sistema.
     * @throws AnimalCodigoJaExisteException se já existir um animal com o mesmo código.
     */
    void cadastrarAnimal(Animal animalNovo);

    /**
     * Cadastra uma nova pessoa no sistema.
     * @throws PessoaJaCadastradaException se já existir uma pessoa com o mesmo CPF.
     */
    void cadastrarPessoa(Pessoa pessoaNova);

    /**
     * Realiza uma adoção de um animal.
     * @throws AnimalJaAdotadoException se o animal já tiver sido adotado.
     * @throws AdocaoCodigoJaExisteException se já existir uma adoção com o mesmo código.
     */
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

    /**
     * Consulta um animal pelo código.
     * @throws AnimalNaoEncontradoException se não existir animal com esse código.
     */
    Animal consultarAnimal(int codigoAnimal);

    /**Consulta uma pessoa pelo CPF.*/
    Pessoa consultarPessoa(String cpfPessoa) throws PessoaNaoExisteException;

    /**
     * Consulta uma adoção pelo código.
     * @throws AdocaoNaoEncontradaException se não existir adoção com esse código.
     */
    Adocao consultarAdocao(int codigoAdocao);

    /**
     * Salva o estado atual do sistema (animais, pessoas e adoções) em arquivo.
     * @throws IOException se ocorrer um erro de escrita no arquivo.
     */
    void salvarDados() throws IOException;

    /**
     * Recupera o estado do sistema previamente salvo em arquivo,
     * substituindo os dados atualmente carregados em memória.
     * @throws IOException se ocorrer um erro de leitura no arquivo.
     * @throws ClassNotFoundException se a classe salva no arquivo não for encontrada.
     */
    void recuperarDados() throws IOException, ClassNotFoundException;

}