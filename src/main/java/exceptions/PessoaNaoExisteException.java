package exceptions;

public class PessoaNaoExisteException extends Exception {
    public PessoaNaoExisteException(String mensagem){
        super(mensagem);
    }
}
