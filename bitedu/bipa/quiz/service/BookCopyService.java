package bitedu.bipa.quiz.service;

import bitedu.bipa.quiz.dao.BookCopyDao;
import bitedu.bipa.quiz.dto.BookCopyDto;
import bitedu.bipa.quiz.util.FileUtils;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookCopyService {

    private final BookCopyDao bookCopyDao;

    public BookCopyService(BookCopyDao bookCopyDao) {
        this.bookCopyDao = bookCopyDao;
    }

    public void createBookCopy() {
        List<BookCopyDto> bookCopyDtos = FileUtils.readBookCopyCsv();

        bookCopyDao.insertBookInfo(bookCopyDtos);
    }
}
