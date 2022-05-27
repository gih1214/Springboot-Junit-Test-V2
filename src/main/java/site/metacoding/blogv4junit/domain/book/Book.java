package site.metacoding.blogv4junit.domain.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity // value object라고도 함. 디비랑 통신
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; // Long : null이면 값이 안 들어 온다.

    private String title;

    private String author;

    // @Builder // 생성자의 단점 : 순서에 맞게 넣어야 됨 -> builder는 순서 상관없음!
    public Book(String title, String author) { // id를 뺀 생성자 만듦
        this.title = title;
        this.author = author;
    }

}
