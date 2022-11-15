package com.example.itspower.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @Transient
    protected Map<String, String> attributes = new HashMap<>();
    @Column(name = "is_deleted")
    @JsonIgnore
    private boolean isDeleted = false;
    @Column(name = "created_by")
    @JsonIgnore
    private Long createdBy;
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updated_by")
    @JsonIgnore
    private Long updatedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedAt;

    public Map<String, String> getAttributes() {
        setAttributes();
        return attributes;
    }

    public void setAttributes() {
    }
}
