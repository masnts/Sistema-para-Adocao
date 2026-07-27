package exceptions;

public class AnimalCodigoJaExisteException extends RuntimeException {
    public AnimalCodigoJaExisteException(String message) {
        super(message);
    }
}