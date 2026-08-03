import exceptions.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Classe responsável por implementar as operações
 * de gerenciamento do sistema de adoção de animais.
 *
 * Utiliza HashMap para armazenar animais, pessoas e adoções.
 */

public class GerenciamentoAdocao implements IGerenciamentoAdocao, Serializable {
    private HashMap<Integer, Animal> animais = new HashMap<>();
    private HashMap<String, Pessoa> pessoas = new HashMap<>();
    private HashMap<Integer, Adocao> adocoes = new HashMap<>();
    private static final long serialVersionUID = 1L;

    /**Método que cadastra um novo animal */
    @Override
    public void cadastrarAnimal(Animal animalNovo) {
        if (animais.containsKey(animalNovo.getCodigo())) {
            throw new AnimalCodigoJaExisteException(
                    "Já existe um animal cadastrado com o código: " + animalNovo.getCodigo());
        }
        animais.put(animalNovo.getCodigo(), animalNovo);
    }

    /**Método que cadastra uma nova pessoa */

    @Override
    public void cadastrarPessoa(Pessoa pessoaNova) {
        if (pessoas.containsKey(pessoaNova.getCpf())) {
            throw new PessoaJaCadastradaException(
                    "Já existe uma pessoa cadastrada com o CPF: " + pessoaNova.getCpf());
        }
        pessoas.put(pessoaNova.getCpf(), pessoaNova);
    }

    /**Método que realiza uma nova adoção */
    @Override
    public void realizarAdocao(Pessoa pessoa, Animal animal, String data, int codigo) throws AnimalJaAdotadoException {
        if(animal.isAdotado()){
            throw new AnimalJaAdotadoException("O animal ja foi adotado");
        }
        if (adocoes.containsKey(codigo)) {
            throw new AdocaoCodigoJaExisteException("Já existe uma adoção registrada com o código: " + codigo);
        }
        animal.adotar();
        Adocao novaAdocao = new Adocao(pessoa,animal,data,codigo);
        adocoes.put(codigo, novaAdocao);
        System.out.println("Adoção realizada com sucesso");

    }

    /**Método para consultar animais cadastrados*/

    @Override
    public Animal consultarAnimal(int codigoAnimal) throws AnimalNaoEncontradoException{
        Animal animal= animais.get(codigoAnimal);
        if(animal==null){
            throw new AnimalNaoEncontradoException("Animal não encontrado");
        }
        return animal;
    }

    /**Método para consultar pessoas cadastradas*/

    @Override
    public Pessoa consultarPessoa(String cpfPessoa) throws PessoaNaoExisteException {
        Pessoa pessoa = pessoas.get(cpfPessoa);
        if (pessoa == null) {
            throw new PessoaNaoExisteException("Pessoa não encontrada com o CPF: " + cpfPessoa);
        }
        return pessoa;
    }

    /**Método para consultar adoções realizadas*/
    @Override
    public Adocao consultarAdocao(int codigoAdocao) {
        Adocao adocao = adocoes.get(codigoAdocao);
        if (adocao == null) {
            throw new AdocaoNaoEncontradaException("Adoção não encontrada com o código: " + codigoAdocao);
        }
        return adocao;
    }

    /**
     * Método listar animais disponíveis.
     * Usa Streams (filter + map + collect) para filtrar apenas os animais
     * ainda não adotados e montar o texto de saída.
     */
    @Override
    public String listarAnimaisDisponiveis() {
        if(animais.isEmpty()){
            return "Nenhum animal encontrado";
        }
        String resultado = animais.values().stream()
                .filter(animal -> !animal.isAdotado())
                .map(Animal::toString)
                .collect(Collectors.joining("\n"));

        return resultado.isEmpty() ? "Nenhum animal disponível para adoção" : resultado;
    }

    /**
     * Método listar pessoas cadastradas.
     * Usa Streams (map + collect) para extrair apenas o nome de cada pessoa.
     */
    @Override
    public String listarPessoasCadastradas() {
        if(pessoas.isEmpty()){
            return "Nenhuma pessoa cadastrada";
        }
        return pessoas.values().stream()
                .map(Pessoa::toString)
                .collect(Collectors.joining("\n"));
    }



    /**
     * Método listar adoções realizadas.
     * Usa Streams (map + collect) para montar a linha de texto de cada adoção.
     */
    @Override
    public String listarAdocoes() {
        if(adocoes.isEmpty()){
            return "Nenhuma Adoção realizada";
        }
        return adocoes.values().stream()
                .map(a -> "Código: " + a.getCodAdocao()
                        + "\n Adotante: " + a.getAdotante().getNome()
                        + "\n Animal: " + a.getAnimalAdotado().getNome()
                        + "\n Data: " + a.getDataDeAdocao())
                .collect(Collectors.joining("\n"));
    }


    /**Método para remover animal*/
    @Override
    public void removerAnimal(int codigoAnimal) {
        animais.remove(codigoAnimal);
    }


    /**Método para remover pessoa*/
    @Override
    public void removerPessoa(String cpfPessoa) {
        pessoas.remove(cpfPessoa);
    }


    /**Método para remover adoção*/
    @Override
    public void removerAdocao(int codigoAdocao) {
        adocoes.remove(codigoAdocao);
    }

    /**
     * Salva o estado atual do sistema em arquivo, delegando a gravação
     * propriamente dita para o GravadorDeDados.
     */
    @Override
    public void salvarDados() throws IOException {
        GravadorDeDados gravador = new GravadorDeDados();
        gravador.gravar(this);
    }

    /**
     * Recupera o estado do sistema a partir do arquivo salvo, substituindo
     * os dados atualmente carregados em memória pelos dados recuperados.
     */
    @Override
    public void recuperarDados() throws IOException, ClassNotFoundException {
        GravadorDeDados gravador = new GravadorDeDados();
        GerenciamentoAdocao sistemaRecuperado = gravador.recuperar();
        this.animais = sistemaRecuperado.animais;
        this.pessoas = sistemaRecuperado.pessoas;
        this.adocoes = sistemaRecuperado.adocoes;
    }

}