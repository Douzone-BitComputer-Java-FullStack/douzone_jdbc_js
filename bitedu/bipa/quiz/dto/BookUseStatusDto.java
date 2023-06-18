package bitedu.bipa.quiz.dto;

import java.time.LocalDate;
import java.util.Date;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookUseStatusDto {
    private int bookSeq;
    private String userId;
    private Date borrowStart;
    private Date borrowEnd;

    private Date returnDate;

    public BookUseStatusDto(int bookSeq, String userId, Date borrowStart, Date borrowEnd,
                            Date returnDate) {
        this.bookSeq = bookSeq;
        this.userId = userId;
        this.borrowStart = borrowStart;
        this.borrowEnd = borrowEnd;
        this.returnDate = returnDate;
    }

    public int getBookSeq() {
        return bookSeq;
    }

    public String getUserId() {
        return userId;
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
            "\"bookSeq\":" + bookSeq +
            ", \"userId\":\"" + userId + "\"" +
            ", \"borrowStar\":" + "\""+ borrowStart +"\""+
            ", \"borrowEnd\":" +  "\"" + borrowEnd + "\"" +
            ", \"returnDate\":" + "\"" + returnDate + "\"" +
            '}';
    }
}
