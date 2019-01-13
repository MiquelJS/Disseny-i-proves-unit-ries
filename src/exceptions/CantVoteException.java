package exceptions;

public class CantVoteException extends Throwable {
    public CantVoteException() {
    }

    public CantVoteException(String message) {
        super(message);
    }
}
