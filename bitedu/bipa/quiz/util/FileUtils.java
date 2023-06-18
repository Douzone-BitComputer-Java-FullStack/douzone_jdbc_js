package bitedu.bipa.quiz.util;

import bitedu.bipa.quiz.dto.BookCopyDto;
import bitedu.bipa.quiz.dto.BookInfoDto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class FileUtils {

    public static List<BookInfoDto> readBookInfoCsv() {

        List<BookInfoDto> bookInfoDtos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/book_info.csv"))) {
            String line;
            StringTokenizer st;
            while ((line = br.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                createBookInfoAndAddList(bookInfoDtos, st);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bookInfoDtos;
    }

    public static List<BookCopyDto> readBookCopyCsv() {

        List<BookCopyDto> bookCopyDtos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/book_copy.csv"))) {
            String line;
            StringTokenizer st;
            while ((line = br.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                createBookCopyAndAddList(bookCopyDtos, st);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bookCopyDtos;
    }

    private static void createBookInfoAndAddList(List<BookInfoDto> bookInfoDtos, StringTokenizer st) {
        while (st.hasMoreTokens()) {
            bookInfoDtos.add(new BookInfoDto(
                st.nextToken().replaceAll("\"", ""),
                st.nextToken().replaceAll("\"", ""),
                st.nextToken().replaceAll("\"", ""),
                Date.valueOf(st.nextToken().replaceAll("\"", "")))
            );
        }
    }

    private static void createBookCopyAndAddList(List<BookCopyDto> bookCopyDtos, StringTokenizer st) {
        while (st.hasMoreTokens()) {
            bookCopyDtos.add(new BookCopyDto(
                Integer.parseInt(st.nextToken()),
                st.nextToken().replaceAll("\"", ""),
                st.nextToken().replaceAll("\"", ""),
                st.nextToken().replaceAll("\"", "")
                )
            );
        }
    }
}
