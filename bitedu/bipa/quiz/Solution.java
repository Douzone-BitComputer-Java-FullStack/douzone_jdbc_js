package bitedu.bipa.quiz;

import bitedu.bipa.quiz.dao.BookCopyDao;
import bitedu.bipa.quiz.dao.BookInfoDao;
import bitedu.bipa.quiz.dao.LibraryDAO;
import bitedu.bipa.quiz.dto.BookUseStatusDto;
import bitedu.bipa.quiz.service.LibraryBookService;
import bitedu.bipa.quiz.util.ConnectionManager2;
import bitedu.bipa.quiz.util.FileUtils;
import com.sun.security.jgss.GSSUtil;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.json.simple.JSONArray;

public class Solution {

	private static final String FILE_PATH = "c://dev/test/user1.json";

	public static void main(String[] args) {
		Solution solution = new Solution();
		List<BookUseStatusDto> bookUseStatusDtoList = solution.getUserInfo("user1");
		solution.saveUserInfo(bookUseStatusDtoList);
		solution.solution4();

	}
	
	public List<BookUseStatusDto> getUserInfo(String userId) {
		LibraryBookService libraryBookService = new LibraryBookService();
		return libraryBookService.getAllBookUseStatus(userId);
		
	}
	
	private void saveUserInfo(List<BookUseStatusDto> list) {
		String jsonString = JSONArray.toJSONString(list);
		try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
			fileWriter.write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void solution4() {
		LibraryBookService libraryBookService = new LibraryBookService();
		libraryBookService.borrowBook(10, "user2");
	}
}
