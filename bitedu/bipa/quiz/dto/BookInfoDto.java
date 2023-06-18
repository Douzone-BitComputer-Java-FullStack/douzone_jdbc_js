package bitedu.bipa.quiz.dto;

import java.util.Date;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookInfoDto {
    private String bookIsbn;
    private String bookTitle;
    private String bookAuthor;
    private Date bookPublishedDate;

    public BookInfoDto(String bookIsbn, String bookTitle, String bookAuthor,
                       Date bookPublishedDate) {
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublishedDate = bookPublishedDate;
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

    public Date getBookPublishedDate() {
        return bookPublishedDate;
    }

    @Override
    public String toString() {
        return "BookInfoDto{" +
            "bookIsbn='" + bookIsbn + '\'' +
            ", bookTitle='" + bookTitle + '\'' +
            ", bookAuthor='" + bookAuthor + '\'' +
            ", bookPublishedDate=" + bookPublishedDate +
            '}';
    }
}
