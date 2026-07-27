import javax.swing.*;
import java.io.IOException;
import exceptions.*;

public class ControllerAdocao {

    private GerenciamentoAdocao sistema;


    public ControllerAdocao(){

        sistema = new GerenciamentoAdocao();

        try{

            sistema.recuperarDados();

        }catch(Exception e){

            // Não há dados salvos ainda, ou o arquivo está corrompido/incompatível.
            // Registramos o motivo para facilitar o diagnóstico e seguimos com um sistema novo.
            System.out.println("Não foi possível carregar dados salvos (" + e.getMessage() + "). Iniciando sistema vazio.");

        }

    }
    public void carregarDados(){

        try{

            sistema.recuperarDados();

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


            sistema.salvarDados();


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