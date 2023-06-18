package bitedu.bipa.quiz.dao;

import bitedu.bipa.quiz.dto.BookUseStatusDto;
import java.sql.Connection;

import bitedu.bipa.quiz.util.ConnectionManager;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LibraryDAO {

	private Connection con;

	public LibraryDAO() {
		this.con = ConnectionManager.getConnection();
	}

	public void borrowBookByBookSeqAndUserId(int bookSeq, String userId) {

		String sql =
			"INSERT INTO book_use_status (book_seq, user_id, borrow_start, borrow_end, return_date)\n" +
				"VALUES (?, ?, ?, ?, null);";

		LocalDate borrowStartDate = LocalDate.now();
		LocalDate borrowEndDate = borrowStartDate.plusDays(13);

		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, bookSeq);
			preparedStatement.setString(2, userId);
			preparedStatement.setDate(3, Date.valueOf(borrowStartDate));
			preparedStatement.setDate(4, Date.valueOf(borrowEndDate));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void returnBookByBookSeqAndUserId(int bookSeq, String userId) {
		String sql =
			"UPDATE book_use_status SET return_date = ? WHERE book_seq = ? AND user_id = ? AND return_date IS NULL";

		// 반납할 때 book_use_status 에서 정보를 먼저 가져오고
		// 책에 대한 반납예정일자가 오늘보다 늦다면 book_user 상태를 업데이트 해줘야 함

		// 반납하려는 날짜가 borrow_end 보다 늦다면 book_user에 service_stop을 오늘 늦은만큼의 날짜로 업데이트 해줘야함.

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
			preparedStatement.setInt(2, bookSeq);
			preparedStatement.setString(3, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * serviceStopDate 만큼 user 정보에서 serviceStopDate를 수정
	 *
	 * @param userId userId
	 * @param serviceStopDate 연기된 만큼의 날짜
	 */
	public void updateServiceStopByUserId(String userId, Date serviceStopDate) {
		String sql = "UPDATE book_user SET service_stop = ? WHERE user_id = ?";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setDate(1, serviceStopDate);
			preparedStatement.setString(2, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<BookUseStatusDto> findBookUseStatusByBookSeqAndUserId(int bookSeq, String userId) {
		String sql =
			"SELECT * FROM book_use_status AS bus " +
				"INNER JOIN book_user AS bu ON bus.user_id = bu.user_id" +
				" WHERE bus.book_seq = ? AND bus.user_id = ? AND return_date IS NULL";

		PreparedStatement preparedStatement = null;
		BookUseStatusDto bookUseStatusDto = null;

		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, bookSeq);
			preparedStatement.setString(2, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bookUseStatusDto = new BookUseStatusDto(
					resultSet.getInt("book_seq"),
					resultSet.getString("user_id"),
					resultSet.getDate("borrow_start"),
					resultSet.getDate("borrow_end"),
					resultSet.getDate("return_date")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(bookUseStatusDto);

	}

	public List<BookUseStatusDto> findAllBookUseStatusByUserId(String userId) {
		String sql = "SELECT * FROM book_use_status AS bus " +
			"INNER JOIN book_user AS bu ON bus.user_id = bu.user_id " +
			"INNER JOIN book_copy AS bc ON bus.book_seq = bc.book_seq " +
			"INNER JOIN book_info AS bi ON bc.book_isbn = bi.book_isbn " +
			"WHERE bus.user_id = ?";

		PreparedStatement preparedStatement = null;

		List<BookUseStatusDto> bookUseStatusDtos = new ArrayList<>();
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				bookUseStatusDtos.add(new BookUseStatusDto(
					resultSet.getInt("book_seq"),
					resultSet.getString("user_id"),
					resultSet.getDate("borrow_start"),
					resultSet.getDate("borrow_end"),
					resultSet.getDate("return_date")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bookUseStatusDtos;
	}

	/**
	 * 해당 책이 미반납된 책이 존재하는지 확인하는 쿼리
	 *
	 * @param bookSeq 책 번호
	 * @return 존재하면 true
	 */
	public boolean existBookUseStatusNotReturnBookByBookSeq(int bookSeq) {
		String sql =
			"SELECT COUNT(*) FROM book_use_status WHERE book_seq = ? AND return_date IS NULL";

		PreparedStatement preparedStatement = null;
		int result = 0;

		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, bookSeq);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result > 0;
	}

	/**
	 * 해당 유저로부터 해당 책을 빌리고있고, 반납되지 않았는지 확인하는 쿼리
	 *
	 * @param bookSeq 책 번호
	 * @param userId 유저 아이디
	 * @return 존재하면 true
	 */
	public boolean existNotReturnBookByBookSeqAndUserId(int bookSeq, String userId) {
		String sql =
			"SELECT COUNT(*) FROM book_use_status WHERE book_seq = ? AND user_id = ? AND return_date IS NULL";

		PreparedStatement preparedStatement = null;
		int result = 0;

		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, bookSeq);
			preparedStatement.setString(2, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result > 0;
	}

	public boolean checkCanBorrowUser(String userId) {
		String sql = "SELECT service_stop FROM book_user WHERE user_id = ?";
		PreparedStatement preparedStatement = null;

		Date result = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (Objects.isNull(result)) {
			return true;
		}

		return result.before(new java.util.Date());
	}

	
}
