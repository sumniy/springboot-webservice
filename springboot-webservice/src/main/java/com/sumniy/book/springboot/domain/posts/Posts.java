package com.sumniy.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    // @Entity : 실제 DB의 테이블과 링크될 클래스임을 나타냄
    // @Id : PK 필드
    // @GeneratedValue : PK의 생성규칙 GenerationType.IDENTITY 옵션을 설정해야 auto increment가 된다.
    // @Column : 테이블의 칼럼을 나타냄. 굳이 선언하지 않아도 칼럼이지만 옵션이 필요할 때 사용
    // @Builder : 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    // @Setter를 사용하면 값이 언제 어디서 변하는 지 명확하게 알 수 없기 때문에 사용하지 않고,
    // 필요할 때 목적과 의도가 명확한 메소드를 만들어 사용한다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author)
    {
        this. title = title;
        this.content = content;
        this.author = author;
    }
}
