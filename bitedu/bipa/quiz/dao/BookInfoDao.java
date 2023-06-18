package bitedu.bipa.quiz.dao;

import bitedu.bipa.quiz.dto.BookInfoDto;
import bitedu.bipa.quiz.util.ConnectionManager;
import bitedu.bipa.quiz.util.ConnectionManager2;
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
public class BookInfoDao {

    public void insertBookInfo(List<BookInfoDto> bookInfoDtos) {

        String sql = "INSERT INTO book_info VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            for (BookInfoDto bookInfoDto : bookInfoDtos) {
                preparedStatement.setString(1, bookInfoDto.getBookIsbn());
                preparedStatement.setString(2, bookInfoDto.getBookTitle());
                preparedStatement.setString(3, bookInfoDto.getBookAuthor());
                preparedStatement.setDate(4, (Date) bookInfoDto.getBookPublishedDate());

                preparedStatement.executeUpdate();
                preparedStatement.clearParameters();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionManager.closeConnection(null, preparedStatement, connection);

    }
}
