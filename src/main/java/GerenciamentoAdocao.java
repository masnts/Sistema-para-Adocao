
import java.util.HashMap;


public class GerenciamentoAdocao implements IGerenciamentoAdocao {
    private HashMap<Integer, Animal> animais = new HashMap<>();
    private HashMap<String, Pessoa> pessoas = new HashMap<>();
    private HashMap<Integer, Adocao> adocoes = new HashMap<>();

    @Override
    public void cadastrarAnimal(Animal animalNovo) {
        animais.put(animalNovo.getCodigo(), animalNovo);
    }


    @Override
    public void cadastrarPessoa(Pessoa pessoaNova) {
        pessoas.put(pessoaNova.getCpf(), pessoaNova);
    }


    @Override
    public void realizarAdocao(Pessoa pessoa, Animal animal, String data, int codigo) {
        if(animal.isAdotado()){
            System.out.println("o animal ja foi adotado");
            return;
        }
        animal.adotar();
        Adocao novaAdocao = new Adocao(pessoa,animal,data,codigo);
        adocoes.put(codigo, novaAdocao);
        System.out.println("Adoção realizada com sucesso");

    }


    @Override
    public Animal consultarAnimal(int codigoAnimal) {
        return animais.get(codigoAnimal);
    }

    @Override
    public Pessoa consultarPessoa(String cpfPessoa) throws PessoaNaoEsxisteException {
        return pessoas.get(cpfPessoa);
    }

    @Override
    public Adocao consultarAdocao(int codigoAdocao) {
        return adocoes.get(codigoAdocao);
    }


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
}
