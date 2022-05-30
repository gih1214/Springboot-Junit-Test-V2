package site.metacoding.blogv4junit.web.dto;

import lombok.Getter;
import site.metacoding.blogv4junit.domain.book.Book;

@Getter // 응답할 땐 getter
public class BookRespDto {

    private Long id;
    private String title;
    private String author;

    public BookRespDto toDto(Book bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
        return this;
    }

    // 레이지 로딩 때문에 dto 안에 엔티티를 넣지 말 것.
    // 아래처럼 dto를 만들어서 넣어줄 것!
    // private UserRespDto user;
}
