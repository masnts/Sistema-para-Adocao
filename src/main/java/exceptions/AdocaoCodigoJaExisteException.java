package exceptions;

public class AdocaoCodigoJaExisteException extends RuntimeException {
    public AdocaoCodigoJaExisteException(String message) {
        super(message);
    }
}
