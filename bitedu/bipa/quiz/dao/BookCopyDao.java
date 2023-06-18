package bitedu.bipa.quiz.dao;

import bitedu.bipa.quiz.dto.BookCopyDto;
import bitedu.bipa.quiz.dto.BookInfoDto;
import bitedu.bipa.quiz.util.ConnectionManager;
import bitedu.bipa.quiz.util.FileUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookCopyDao {

    public void insertBookInfo(List<BookCopyDto> bookCopyDtos) {

        String sql = "INSERT INTO book_copy VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            for (BookCopyDto bookCopyDto : bookCopyDtos) {
                preparedStatement.setInt(1, bookCopyDto.getBookSeq());
                preparedStatement.setString(2, bookCopyDto.getBookPosition());
                preparedStatement.setString(3, bookCopyDto.getBookStatus());
                preparedStatement.setString(4,  bookCopyDto.getBookIsbn());

                preparedStatement.executeUpdate();
                preparedStatement.clearParameters();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionManager.closeConnection(null, preparedStatement, connection);

    }
}
