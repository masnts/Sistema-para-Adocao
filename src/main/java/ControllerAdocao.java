import javax.swing.*;
import java.io.IOException;

public class ControllerAdocao {

    private GerenciamentoAdocao sistema;
    private GravadorDeDados gravador;


    public ControllerAdocao(){

        gravador = new GravadorDeDados();

        try{

            sistema = (GerenciamentoAdocao) gravador.recuperar();

        }catch(Exception e){

            sistema = new GerenciamentoAdocao();

        }

    }
    public void carregarDados(){

        try{

            sistema = (GerenciamentoAdocao) gravador.recuperar();

        }catch(IOException | ClassNotFoundException e){

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao carregar dados: " + e.getMessage()
            );

        }

    }

    // ==========================
    // ANIMAL
    // ==========================

    public void cadastrarAnimal(Animal novoAnimal){

        sistema.cadastrarAnimal(novoAnimal);

    }


    public Animal pesquisarAnimal(int codigo){

        return sistema.consultarAnimal(codigo);

    }


    public void removerAnimal(int codigo){

        sistema.removerAnimal(codigo);

    }


    public String listarAnimais(){

        return sistema.listarAnimaisDisponiveis();

    }



    // ==========================
    // PESSOA
    // ==========================

    public void cadastrarPessoa(Pessoa pessoa){

        sistema.cadastrarPessoa(pessoa);

    }


    public Pessoa pesquisarPessoa(String cpf) throws PessoaNaoExisteException{

        return sistema.consultarPessoa(cpf);

    }


    public void removerPessoa(String cpf){

        sistema.removerPessoa(cpf);

    }


    public String listarPessoas(){

        return sistema.listarPessoasCadastradas();

    }



    // ==========================
    // ADOÇÃO
    // ==========================

    public void realizarAdocao(Pessoa pessoa, Animal animal, String data, int codigo)
            throws AnimalJaAdotadoException {


        sistema.realizarAdocao(
                pessoa,
                animal,
                data,
                codigo
        );

    }



    public Adocao pesquisarAdocao(int codigo){

        return sistema.consultarAdocao(codigo);

    }



    public void removerAdocao(int codigo){

        sistema.removerAdocao(codigo);

    }



    public String listarAdocoes(){

        return sistema.listarAdocoes();

    }



    // ==========================
    // PERSISTÊNCIA
    // ==========================

    public void salvarDados(){


        try {


            gravador.gravar(sistema);


            JOptionPane.showMessageDialog(
                    null,
                    "Dados salvos com sucesso!"
            );


        } catch(IOException e){


            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao salvar dados: " + e.getMessage()
            );


        }


    }

}