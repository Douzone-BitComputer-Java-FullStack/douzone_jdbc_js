package bitedu.bipa.quiz.exception;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookUseStatusNotFountException extends RuntimeException {
    private static final String MESSAGE = "해당 책을 빌리지 않거나 반납이 완료되었습니다.";

    public BookUseStatusNotFountException() {
        super(MESSAGE);
    }
}
