import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.*;

/**
 * Classe de teste automático de GerenciamentoAdocao.
 * Exercita todos os métodos definidos em IGerenciamentoAdocao:
 * cadastro, consulta, listagem (incluindo os métodos que usam Streams),
 * remoção e persistência (salvarDados/recuperarDados).
 */
public class GerenciamentoAdocaoTest {


    @Test
    public void deveCadastrarEConsultarAnimal(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Animal animal = new Animal(
                "Rex",
                Animal.Sexo.MACHO,
                1,
                Animal.Especie.CACHORRO,
                "10/05/2020",
                true,
                "Vira-lata",
                false
        );
        sistema.cadastrarAnimal(animal);
        Animal encontrado = sistema.consultarAnimal(1);
        assertEquals("Rex", encontrado.getNome());
    }

    @Test
    public void deveRealizarAdocao(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Pessoa pessoa = new Pessoa(
                "12345678900",
                "Maria",
                "Rua A",
                "999999999"
        );
        Animal animal = new Animal(
                "Mimi",
                Animal.Sexo.FEMEA,
                2,
                Animal.Especie.GATO,
                "01/01/2022",
                false,
                "Siamês",
                false
        );
        sistema.cadastrarPessoa(pessoa);
        sistema.cadastrarAnimal(animal);
        sistema.realizarAdocao(
                pessoa,
                animal,
                "18/07/2026",
                1
        );
        assertTrue(animal.isAdotado());
    }

    @Test
    public void naoDeveAdotarAnimalJaAdotado(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Pessoa pessoa = new Pessoa("11122233344", "João", "Rua B", "988888888");
        Animal animal = new Animal("Bidu", Animal.Sexo.MACHO, 3, Animal.Especie.CACHORRO,
                "01/01/2020", true, "SRD", false);

        sistema.cadastrarPessoa(pessoa);
        sistema.cadastrarAnimal(animal);
        sistema.realizarAdocao(pessoa, animal, "01/01/2026", 10);

        assertThrows(AnimalJaAdotadoException.class, () ->
                sistema.realizarAdocao(pessoa, animal, "02/01/2026", 11));
    }

    @Test
    public void naoDeveCadastrarAnimalComCodigoDuplicado(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Animal animal1 = new Animal("Rex", Animal.Sexo.MACHO, 5, Animal.Especie.CACHORRO,
                "01/01/2020", true, "SRD", false);
        Animal animal2 = new Animal("Totó", Animal.Sexo.MACHO, 5, Animal.Especie.CACHORRO,
                "01/01/2021", false, "SRD", false);

        sistema.cadastrarAnimal(animal1);

        assertThrows(AnimalCodigoJaExisteException.class, () ->
                sistema.cadastrarAnimal(animal2));
    }

    @Test
    public void naoDeveCadastrarPessoaComCpfDuplicado(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Pessoa pessoa1 = new Pessoa("22233344455", "Ana", "Rua C", "977777777");
        Pessoa pessoa2 = new Pessoa("22233344455", "Ana Paula", "Rua D", "966666666");

        sistema.cadastrarPessoa(pessoa1);

        assertThrows(PessoaJaCadastradaException.class, () ->
                sistema.cadastrarPessoa(pessoa2));
    }

    @Test
    public void naoDeveRegistrarAdocaoComCodigoDuplicado(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Pessoa pessoa = new Pessoa("33344455566", "Carlos", "Rua E", "955555555");
        Animal animal1 = new Animal("Bob", Animal.Sexo.MACHO, 6, Animal.Especie.CACHORRO,
                "01/01/2020", true, "SRD", false);
        Animal animal2 = new Animal("Lulu", Animal.Sexo.FEMEA, 7, Animal.Especie.GATO,
                "01/01/2020", true, "SRD", false);

        sistema.cadastrarPessoa(pessoa);
        sistema.cadastrarAnimal(animal1);
        sistema.cadastrarAnimal(animal2);
        sistema.realizarAdocao(pessoa, animal1, "01/01/2026", 20);

        assertThrows(AdocaoCodigoJaExisteException.class, () ->
                sistema.realizarAdocao(pessoa, animal2, "01/01/2026", 20));
    }

    @Test
    public void deveLancarExcecaoAoConsultarAnimalInexistente(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        assertThrows(AnimalNaoEncontradoException.class, () ->
                sistema.consultarAnimal(999));
    }

    @Test
    public void deveLancarExcecaoAoConsultarPessoaInexistente(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        assertThrows(PessoaNaoExisteException.class, () ->
                sistema.consultarPessoa("00000000000"));
    }

    @Test
    public void deveLancarExcecaoAoConsultarAdocaoInexistente(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        assertThrows(AdocaoNaoEncontradaException.class, () ->
                sistema.consultarAdocao(999));
    }

    @Test
    public void deveRemoverAnimalPessoaEAdocao(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Pessoa pessoa = new Pessoa("44455566677", "Fernanda", "Rua F", "944444444");
        Animal animal = new Animal("Amora", Animal.Sexo.FEMEA, 8, Animal.Especie.GATO,
                "01/01/2020", true, "SRD", false);

        sistema.cadastrarPessoa(pessoa);
        sistema.cadastrarAnimal(animal);
        sistema.realizarAdocao(pessoa, animal, "01/01/2026", 30);

        sistema.removerAdocao(30);
        sistema.removerAnimal(8);
        sistema.removerPessoa("44455566677");

        assertThrows(AnimalNaoEncontradoException.class, () -> sistema.consultarAnimal(8));
        assertThrows(AdocaoNaoEncontradaException.class, () -> sistema.consultarAdocao(30));
        assertThrows(PessoaNaoExisteException.class, () -> sistema.consultarPessoa("44455566677"));
    }

    /**
     * Testa listarAnimaisDisponiveis (implementado com Streams: filter + map + collect).
     * Um animal já adotado não deve aparecer na listagem de disponíveis.
     */
    @Test
    public void deveListarApenasAnimaisDisponiveis(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Pessoa pessoa = new Pessoa("55566677788", "Paulo", "Rua G", "933333333");
        Animal disponivel = new Animal("Thor", Animal.Sexo.MACHO, 9, Animal.Especie.CACHORRO,
                "01/01/2021", true, "Labrador", false);
        Animal adotado = new Animal("Nina", Animal.Sexo.FEMEA, 10, Animal.Especie.GATO,
                "01/01/2021", true, "Persa", false);

        sistema.cadastrarPessoa(pessoa);
        sistema.cadastrarAnimal(disponivel);
        sistema.cadastrarAnimal(adotado);
        sistema.realizarAdocao(pessoa, adotado, "01/01/2026", 40);

        String listagem = sistema.listarAnimaisDisponiveis();

        assertTrue(listagem.contains("Thor"));
        assertFalse(listagem.contains("Nina"));
    }

    /**
     * Testa listarPessoasCadastradas (implementado com Streams: map + collect).
     */
    @Test
    public void deveListarPessoasCadastradas(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Pessoa pessoa = new Pessoa("66677788899", "Rita", "Rua H", "922222222");
        sistema.cadastrarPessoa(pessoa);

        String listagem = sistema.listarPessoasCadastradas();

        assertTrue(listagem.contains("Rita"));
    }

    /**
     * Testa listarAdocoes (implementado com Streams: map + collect).
     */
    @Test
    public void deveListarAdocoesRealizadas(){
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Pessoa pessoa = new Pessoa("77788899900", "Bruno", "Rua I", "911111111");
        Animal animal = new Animal("Bella", Animal.Sexo.FEMEA, 11, Animal.Especie.CACHORRO,
                "01/01/2021", true, "Poodle", false);

        sistema.cadastrarPessoa(pessoa);
        sistema.cadastrarAnimal(animal);
        sistema.realizarAdocao(pessoa, animal, "01/01/2026", 50);

        String listagem = sistema.listarAdocoes();

        assertTrue(listagem.contains("Bella"));
        assertTrue(listagem.contains("Bruno"));
    }

    /**
     * Testa salvarDados/recuperarDados (persistência em arquivo via GravadorDeDados).
     * Salva o estado de um sistema e recupera em outra instância, verificando
     * que os dados batem.
     */
    @Test
    public void deveSalvarERecuperarDados() throws Exception {
        GerenciamentoAdocao sistema = new GerenciamentoAdocao();
        Pessoa pessoa = new Pessoa("88899900011", "Camila", "Rua J", "900000000");
        Animal animal = new Animal("Duke", Animal.Sexo.MACHO, 12, Animal.Especie.CACHORRO,
                "01/01/2021", true, "SRD", false);

        sistema.cadastrarPessoa(pessoa);
        sistema.cadastrarAnimal(animal);
        sistema.salvarDados();

        GerenciamentoAdocao sistemaRecuperado = new GerenciamentoAdocao();
        sistemaRecuperado.recuperarDados();

        assertEquals("Camila", sistemaRecuperado.consultarPessoa("88899900011").getNome());
        assertEquals("Duke", sistemaRecuperado.consultarAnimal(12).getNome());
    }
}