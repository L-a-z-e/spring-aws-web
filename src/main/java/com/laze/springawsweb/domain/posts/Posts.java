package com.laze.springawsweb.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자 자동 추가 public Posts(){}
@Entity // Table과 링크될 클래스임을 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭 Entity Class에는 Setter를 만들지 않는다.
public class Posts extends BaseTimeEntity{

    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙 IDENTITY - auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 선언하지 않아도 Column이 되지만 추가 옵션이 필요한경우 사용, VARCHAR 기본값 255 -> 500, 타입을 TEXT로 변경 등
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // Builder Pattern, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
