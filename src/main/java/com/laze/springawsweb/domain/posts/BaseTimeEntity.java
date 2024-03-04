package com.laze.springawsweb.domain.posts;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity Class 들이 BaseTimeEntity를 상속할 경우 필드(createdDate, modifiedDate)들도 컬럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing 기능 포함시킴
public abstract class BaseTimeEntity {

    @CreatedDate // Entity 생성시 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // Entity 값 변경시 시간 자동 저장
    private LocalDateTime modifiedDate;
}
