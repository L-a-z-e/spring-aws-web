package com.laze.springawsweb.config.auth.dto;

import com.laze.springawsweb.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

    /**
     * 왜 User Class가 아니라 SessionUser를 사용하는지 -> User Class에 직렬화를 구현하지 않음 + User Class 는 Entity임
     * .@OneToMany 나 .@ManyToMany 등 다른 엔티티와 관계가 형성될때 자식 엔티티를 갖고있으면 자식들까지 직렬화 대상에 포함되므로 성능 이슈등이 생김
     * 그래서 직렬화 기능을 가진 세션 Dto를 따로 만듦
     */
}
