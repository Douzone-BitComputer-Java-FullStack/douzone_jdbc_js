package bitedu.bipa.quiz.service;

import bitedu.bipa.quiz.dao.BookInfoDao;
import bitedu.bipa.quiz.dto.BookInfoDto;
import bitedu.bipa.quiz.util.FileUtils;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookInfoService {
    private final BookInfoDao bookInfoDao;

    public BookInfoService(BookInfoDao bookInfoDao) {
        this.bookInfoDao = bookInfoDao;
    }

    public void createBookInfo() {
        List<BookInfoDto> bookInfoDtos = FileUtils.readBookInfoCsv();

        bookInfoDao.insertBookInfo(bookInfoDtos);
    }
}
