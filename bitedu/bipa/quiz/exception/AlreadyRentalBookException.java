package bitedu.bipa.quiz.exception;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class AlreadyRentalBookException extends RuntimeException {
    private static final String MESSAGE = "이미 대출중인 책 입니다.";

    public AlreadyRentalBookException() {
        super(MESSAGE);
    }
}
