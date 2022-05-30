package site.metacoding.blogv4junit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import site.metacoding.blogv4junit.domain.book.Book;
import site.metacoding.blogv4junit.domain.book.BookRepository;
import site.metacoding.blogv4junit.web.dto.BookRespDto;
import site.metacoding.blogv4junit.web.dto.BookSaveReqDto;

@ExtendWith(MockitoExtension.class) // 스프링 컨테이너 내부에 있는 것 처럼 속일 수 있음
public class BookServiceTest {

    @InjectMocks // *** 1. mockito 환경에 있는 다른 모키토에 뜬 애들을 확인해서 띄워줌
    private BookService bookService;

    @Mock
    private BookRepository bookRepository; // mockito에 뜬거

    @Test
    public void 책등록하기_테스트() {

        // given - 서비스의 매개변수는 dto
        BookSaveReqDto reqDto = new BookSaveReqDto();
        reqDto.setTitle("스프링 1강");
        reqDto.setAuthor("메타코딩");

        // stub (행동 정의 = 가설)
        // 레파지토리에 호출하면 이렇게 될거다라고 가정하는 것
        Mockito.when(bookRepository.save(reqDto.toEntity())).thenReturn(new Book(1L, "스프링 1강", "메타코딩"));
        // 가짜 데이터인데 new 된 메모리 주소까지 비교함 -> Book 엔티티에 응급처치 해뒀음

        // when(실행) - *** 2. 서비스는 무조건 서비스 메서드를 호출시켜야 한다!!
        BookRespDto respDto = bookService.책등록하기(reqDto);

        // then(검증)
        assertEquals(1L, respDto.getId());
        assertEquals("스프링 1강", respDto.getTitle());
        assertEquals("메타코딩", respDto.getAuthor());
    }

    @Test
    public void 책한건가져오기_테스트() {

        // given - 서비스의 매개변수는 id
        Long id = 1L;
        // optional 가짜 데이터 만들기
        Optional<Book> bookOp = Optional.of(new Book(1L, "스프링 1강", "메타코딩"));

        // stub (행동 정의 = 가설)
        // 레파지토리에 호출하면 이렇게 될거다라고 가정하는 것
        Mockito.when(bookRepository.findById(id)).thenReturn(bookOp);

        // when(실행) - *** 2. 서비스는 무조건 서비스 메서드를 호출시켜야 한다!!
        BookRespDto respDto = bookService.책한건가져오기(id);

        // then(검증)
        assertEquals(1L, respDto.getId());
        assertEquals("스프링 1강", respDto.getTitle());
        assertEquals("메타코딩", respDto.getAuthor());
    }
}
