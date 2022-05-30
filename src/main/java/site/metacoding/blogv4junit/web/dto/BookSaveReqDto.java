package site.metacoding.blogv4junit.web.dto;

import lombok.Setter;
import site.metacoding.blogv4junit.domain.book.Book;

// 생성자를 호출 값을 채웠을까?
// Setter를 호출해서 값을 채웠을까?
// --> ReqDto의 경우는 무조건 빈생성자 + setter
@Setter
public class BookSaveReqDto {

    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder().title(this.title).author(this.author).build();
    }

    // public BookSaveReqDto(String title, String author) {
    // System.out.println("=========================");
    // System.out.println("BookSaveReqDto() 호출됨");
    // System.out.println("=========================");
    // this.title = title;
    // this.author = author;
    // }

    // public BookSaveReqDto() {
    // System.out.println("=========================");
    // System.out.println("BookSaveReqDto() 호출됨");
    // System.out.println("=========================");
    // }

    // public String getTitle() {
    // return title;
    // }

    // public void setTitle(String title) {
    // System.out.println("=========================");
    // System.out.println("setTitle() 호출됨");
    // System.out.println("=========================");
    // this.title = title;
    // }

    // public String getAuthor() {
    // return author;
    // }

    // public void setAuthor(String author) {
    // System.out.println("=========================");
    // System.out.println("setAuthor() 호출됨");
    // System.out.println("=========================");
    // this.author = author;
    // }
}
