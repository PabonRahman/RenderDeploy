package com.spring_security_app.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    private String userName;

    @Column
    private String userFirstName;

    @Column
    private String userLastName;

    @Column
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private Boolean enabled;

    @Column
    private Boolean credentialsNonExpired;

    @Column
    private Boolean accountNonExpired;

    @Column
    private Boolean accountNonLocked;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "userrole",
            joinColumns = @JoinColumn(name = "user_name"),
            inverseJoinColumns = @JoinColumn(name = "role_name")
    )
    private Set<Role> roles;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public User() {
    }

    @PrePersist
    void createdAt() {
        this.dateCreated = this.lastUpdated = OffsetDateTime.now();
    }

    @PreUpdate
    void updatedAt() {
        this.lastUpdated = OffsetDateTime.now();
    }
}
