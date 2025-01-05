package com.lullaby.springlibrary.application.user.domain;

import com.lullaby.springlibrary.common.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import static com.lullaby.springlibrary.util.ValidationUtils.*;

@Getter
@Table(name = "users")
@Entity
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_role")
    private UserRole userRole;

    public UserEntity() {
    }

    public UserEntity(String account, String password, String userName, UserRole userRole) {
        setAccount(account);
        setPassword(password);
        setUserName(userName);
        setUserRole(userRole);
    }

    public void setAccount(String account) {
        notBlank(account, "계정을 입력해주세요.");
        longgerThan(account, 4, "계정은 4자 이상 입력해주세요.");
        this.account = account;
    }

    public void setPassword(String password) {
        notBlank(password, "비밀번호를 입력해주세요.");
        this.password = password;
    }

    public void setUserName(String userName) {
        notBlank(userName, "사용자 이름을 입력해주세요.");
        longgerThan(userName, 2, "사용자 이름은 2자 이상 입력해주세요.");
        this.userName = userName;
    }

    public void setUserRole(UserRole userRole) {
        notNull(userRole, "사용자 권한을 입력해주세요.");
        this.userRole = userRole;
    }
}
