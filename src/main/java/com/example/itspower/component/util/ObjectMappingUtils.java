package com.example.itspower.component.util;

import com.example.itspower.entity.BaseEntity;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class ObjectMappingUtils {

    public static <S, T extends BaseEntity> void copyProperties(S s, T t, Long userId, String... ignoreProperties) {
        BeanUtils.copyProperties(s, t, ignoreProperties);
        if (t.getCreatedAt() == null) {
            t.setCreatedAt(new Date());
            t.setCreatedBy(userId);
        }
        t.setUpdatedAt(new Date());
        t.setUpdatedBy(userId);
    }
}
