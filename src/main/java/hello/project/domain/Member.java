package hello.project.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    private String email;

    private String username;

    private String password;

    protected Member() {}

    public Member(String email) {
        this.email = email;
    }
}
