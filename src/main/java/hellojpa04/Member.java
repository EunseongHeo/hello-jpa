package hellojpa04;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",    //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,               //초기화값
        allocationSize = 50             //얻어올 시퀀스 값
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
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
