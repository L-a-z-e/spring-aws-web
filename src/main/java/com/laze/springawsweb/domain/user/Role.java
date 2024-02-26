package com.laze.springawsweb.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    // spring security에서 권한 코드에 항상 ROLE_이 뭍어있어야함
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반사용자");

    private final String key;
    private final String title;
}
