package bitedu.bipa.quiz.dto;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookCopyDto {
    private int bookSeq;
    private String bookPosition;
    private String bookStatus;
    private String bookIsbn;

    public BookCopyDto(int bookSeq, String bookPosition, String bookStatus, String bookIsbn) {
        this.bookSeq = bookSeq;
        this.bookPosition = bookPosition;
        this.bookStatus = bookStatus;
        this.bookIsbn = bookIsbn;
    }

    public int getBookSeq() {
        return bookSeq;
    }

    public String getBookPosition() {
        return bookPosition;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    @Override
    public String toString() {
        return "BookCopyDto{" +
            "bookSeq=" + bookSeq +
            ", bookPosition='" + bookPosition + '\'' +
            ", bookStatus='" + bookStatus + '\'' +
            ", bookIsbn='" + bookIsbn + '\'' +
            '}';
    }
}
