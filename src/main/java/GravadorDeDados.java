import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GravadorDeDados {

    private static final String ARQUIVO = "dados.dat";

    // Salva o sistema no arquivo
    public void gravar(GerenciamentoAdocao sistema) throws IOException {

        try (ObjectOutputStream obj = new ObjectOutputStream(
                new FileOutputStream(ARQUIVO))) {

            obj.writeObject(sistema);

        }

    }

    // Recupera o sistema salvo
    public GerenciamentoAdocao recuperar() throws IOException, ClassNotFoundException {

        try (ObjectInputStream obj = new ObjectInputStream(
                new FileInputStream(ARQUIVO))) {

            return (GerenciamentoAdocao) obj.readObject();

        }

    }

}