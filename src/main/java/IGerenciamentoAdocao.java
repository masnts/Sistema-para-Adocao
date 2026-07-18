public interface IGerenciamentoAdocao {
    void cadastrarAnimal(Animal animalNovo);
    void cadastrarPessoa(Pessoa pessoaNova);
    void realizarAdocao(Pessoa pessoa, Animal animal, String data, int codigo);

    Animal consultarAnimal(int codigoAnimal);
    Pessoa consultarPessoa(String cpfPessoa) throws PessoaNaoEsxisteException;
    Adocao consultarAdocao(int codigoAdocao);



}
