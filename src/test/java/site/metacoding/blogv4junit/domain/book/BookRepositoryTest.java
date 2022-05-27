package site.metacoding.blogv4junit.domain.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @ DataJpaTest
 * - 런타임에 실행 (데이터베이스 관련된 것들만)
 * - h2 동작 (메모리 데이터베이스 실행)
 * - 자동 rollback (내부에 트랜잭션이 걸려있음)
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired // DI 해줌
    private BookRepository bookRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach // 테스트 메서드 실행 이전에 수행해줌 -> 테스트 메서드 시작 전후에 롤백해주기
    public void db_init() {
        bookRepository.deleteAll();

        // ALTER를 이용해 id 재설정 (메서드별 테스트 시에 Auto-Increment 초기화를 위함)
        em
                .createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1")
                .executeUpdate();
    }

    // C : 유효성검사 필요 (X)
    @Test
    @Order(1)
    public void save_test() { // () 여기에 주입X
        // given - 이전 데이터를 가짜로 만듦
        String title = "스프링부트 1강";
        String author = "최주호";
        Book book = new Book(title, author); // 비영속화

        // when - 테스트 진행
        Book bookEntity = bookRepository.save(book); // 영속화

        // then - 검증
        assertEquals(title, bookEntity.getTitle());
        assertEquals(author, bookEntity.getAuthor());
        assertEquals(1, bookEntity.getId());

    }

    @Test
    @Order(2)
    public void findById_test() {
        // given
        String title = "스프링부트 1강";
        String author = "최주호";
        Book book = new Book(title, author); // 비영속화
        bookRepository.save(book); // 영속화

        Long id = 1L;

        // when - 테스트 진행
        // Book bookEntity = bookRepository.findById(id).orElseThrow();
        // Book bookEntity = bookRepository.findById(id).orElseGet();
        Optional<Book> bookOp = bookRepository.findById(id);

        // then - 검증
        if (bookOp.isPresent()) {
            Book bookEntity = bookOp.get();
            assertEquals(title, bookEntity.getTitle());
            assertEquals(author, bookEntity.getAuthor());
            assertEquals(1, bookEntity.getId());
        } else {
            assertNotNull(bookOp.get()); // null 아니지? -> null 이 들어왔어 -> false
        }
    }

}
