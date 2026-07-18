import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}