package com.neukrang.citadel.util;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public boolean needToUpdate(int updatePeriod) {
        return LocalDateTime.now().isAfter(modifiedDate.plusDays(updatePeriod));
    }
}
