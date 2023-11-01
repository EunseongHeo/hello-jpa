package hellojpa04;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
        generator = "MEMBER_SEQ_GENERATOR"
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String userName;

    public Member(){}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }
}
