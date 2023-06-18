package bitedu.bipa.quiz.service;

import bitedu.bipa.quiz.dao.LibraryDAO;
import bitedu.bipa.quiz.dto.BookUseStatusDto;
import bitedu.bipa.quiz.exception.AlreadyRentalBookException;
import bitedu.bipa.quiz.exception.BookUseStatusNotFountException;
import bitedu.bipa.quiz.exception.NotAllowBorrowUserException;
import java.util.Date;
import java.util.List;

public class LibraryBookService {
	
	private LibraryDAO dao;
	public LibraryBookService() {
		dao = new LibraryDAO();
	}

	public void borrowBook(int bookSeq, String userId) {
		if (!dao.checkCanBorrowUser(userId)) {
			throw new NotAllowBorrowUserException();
		}

		if (dao.existBookUseStatusNotReturnBookByBookSeq(bookSeq)) {
			throw new AlreadyRentalBookException();
		}

		dao.borrowBookByBookSeqAndUserId(bookSeq, userId);

	}

	public void returnBook(int bookSeq, String userId) {

		BookUseStatusDto bookUseStatusDto = dao.findBookUseStatusByBookSeqAndUserId(bookSeq, userId)
			.orElseThrow(BookUseStatusNotFountException::new);

		Date now = new Date();

		if (bookUseStatusDto.getBorrowEnd().before(now)) {
			long dateDifference = now.getTime() - bookUseStatusDto.getBorrowEnd().getTime();
			Date serviceStopDate = new Date(now.getTime() + dateDifference);
			dao.updateServiceStopByUserId(userId, new java.sql.Date(serviceStopDate.getTime()));
		}

		dao.returnBookByBookSeqAndUserId(bookSeq, userId);
	}

	public List<BookUseStatusDto> getAllBookUseStatus(String userId) {
		return dao.findAllBookUseStatusByUserId(userId);
	}
}
