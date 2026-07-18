
import java.io.Serializable;
import java.util.HashMap;
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
        animais.put(animalNovo.getCodigo(), animalNovo);
    }

    /**Método que cadastra uma nova pessoa */

    @Override
    public void cadastrarPessoa(Pessoa pessoaNova) {
        pessoas.put(pessoaNova.getCpf(), pessoaNova);
    }

    /**Método que realiza uma nova adoção */
    @Override
    public void realizarAdocao(Pessoa pessoa, Animal animal, String data, int codigo) throws AnimalJaAdotadoException {
        if(animal.isAdotado()){
            throw new AnimalJaAdotadoException("O animal ja foi adotado");
        }
        animal.adotar();
        Adocao novaAdocao = new Adocao(pessoa,animal,data,codigo);
        adocoes.put(codigo, novaAdocao);
        System.out.println("Adoção realizada com sucesso");

    }

    /**Método para consultar animais cadastrados*/

    @Override
    public Animal consultarAnimal(int codigoAnimal) throws AnimalNaoEcontradoException{
        Animal animal= animais.get(codigoAnimal);
        if(animal==null){
            throw new AnimalNaoEcontradoException("Animal não encontrado");
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
        return adocoes.get(codigoAdocao);
    }

    /**Método listar animais disponíveis*/
    @Override
    public String listarAnimaisDisponiveis() {
        if(animais.isEmpty()){
            return "Nenhum animal encontrado";
        }
        StringBuilder sb = new StringBuilder();
        for(Animal a : animais.values()){
            if(!a.isAdotado()){
                sb.append(a.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**Método listar pessoas cadrastradas*/
    @Override
    public String listarPessoasCadastradas() {
        if(pessoas.isEmpty()){
            return "Nenhuma pessoa cadastrada";
        }
        StringBuilder sb= new StringBuilder();
        for(Pessoa p : pessoas.values()){
            sb.append(p.getNome()+"\n");
        }
        return sb.toString();
    }



    /**Método listar adoções realizadas*/
    @Override
    public String listarAdocoes() {
        if(adocoes.isEmpty()){
            return "Nenhuma Adoção realizada";
        }
        StringBuilder sb= new StringBuilder();
        for(Adocao a : adocoes.values()){
            sb.append("Código: ").append(a.getCodAdocao())
                    .append(" | Adotante: ").append(a.getAdotante().getNome())
                    .append(" | br.com.Adocao.Animal: ").append(a.getAnimalAdotado().getNome())
                    .append(" | Data: ").append(a.getDataDeAdocao())
                    .append("\n");

        }
        return sb.toString();
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



}
