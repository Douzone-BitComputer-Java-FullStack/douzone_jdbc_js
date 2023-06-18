package bitedu.bipa.quiz.dto;

import java.util.Date;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class RentalBookDto {
    private int bookSeq;
    private String bookIsbn;
    private String bookTitle;
    private String bookAuthor;
    private Date borrowStart;
    private Date borrowEnd;
    private Date returnDate;

    public RentalBookDto(int bookSeq, String bookIsbn, String bookTitle, String bookAuthor,
                         Date borrowStart, Date borrowEnd, Date returnDate) {
        this.bookSeq = bookSeq;
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.borrowStart = borrowStart;
        this.borrowEnd = borrowEnd;
        this.returnDate = returnDate;
    }

    public int getBookSeq() {
        return bookSeq;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public Date getBorrowStart() {
        return borrowStart;
    }

    public Date getBorrowEnd() {
        return borrowEnd;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "{" +
            "bookSeq=" + bookSeq +
            ", bookIsbn='" + bookIsbn + '\'' +
            ", bookTitle='" + bookTitle + '\'' +
            ", bookAuthor='" + bookAuthor + '\'' +
            ", borrowStart=" + borrowStart +
            ", borrowEnd=" + borrowEnd +
            ", returnDate=" + returnDate +
            '}';
    }
}
