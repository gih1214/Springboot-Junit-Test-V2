package site.metacoding.blogv4junit.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv4junit.domain.book.Book;
import site.metacoding.blogv4junit.domain.book.BookRepository;
import site.metacoding.blogv4junit.web.dto.BookRespDto;
import site.metacoding.blogv4junit.web.dto.BookSaveReqDto;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    // title, author = BookSaveReqDto
    @Transactional(rollbackFor = RuntimeException.class) // RuntimeException이면 롤백
    public BookRespDto 책등록하기(BookSaveReqDto reqDto) {
        Book bookEntity = bookRepository.save(reqDto.toEntity()); // 영속화
        return new BookRespDto().toDto(bookEntity); // dto로 응답
    }

    // 고립성 공부하고 사용하자.
    // @Transactional(readOnly = true) // 영속성 컨텍스트에서 변경감지를 안 함.
    public BookRespDto 책한건가져오기(Long id) {

        Optional<Book> bookOp = bookRepository.findById(id);
        if (bookOp.isPresent()) {
            Book bookEntity = bookOp.get();
            return new BookRespDto().toDto(bookEntity);
        } else {
            throw new RuntimeException("해당 책을 가져올 수 없습니다.");
        }

        // Book bookEntity = bookRepository.findById(id).orElseThrow(() -> {
        // return new RuntimeException("해당 책을 가져올 수 없습니다.");
        // });
        // return new BookRespDto().toDto(bookEntity);
    }
}
