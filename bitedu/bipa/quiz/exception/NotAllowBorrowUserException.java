package bitedu.bipa.quiz.exception;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class NotAllowBorrowUserException extends RuntimeException {
    private static final String MESSAGE = "빌릴 수 없는 유저입니다.";

    public NotAllowBorrowUserException() {
        super(MESSAGE);
    }
}
