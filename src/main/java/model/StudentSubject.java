package model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "student_subject")
@Data
@Accessors(chain = true)
public class StudentSubject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "mssv", nullable = false)
    private String mssv;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "subject_code", referencedColumnName = "code")
    @Column(name = "subject_id", nullable = false)
    private int subjectId;

    @Column(name = "session_one", nullable = false)
    private boolean sessionOne;

    @Column(name = "session_two", nullable = false)
    private boolean sessionTwo;

    @Column(name = "session_three", nullable = false)
    private boolean sessionThree;

    @Column(name = "session_four", nullable = false)
    private boolean sessionFour;

    @Column(name = "session_five", nullable = false)
    private boolean sessionFive;

    @Column(name = "session_six", nullable = false)
    private boolean sessionSix;

    @Column(name = "session_seven", nullable = false)
    private boolean sessionSeven;

    @Column(name = "session_eight", nullable = false)
    private boolean sessionEight;

    @Column(name = "session_nine", nullable = false)
    private boolean sessionNine;

    @Column(name = "session_ten", nullable = false)
    private boolean sessionTen;

    @Column(name = "session_eleven", nullable = false)
    private boolean sessionEleven;

    @Column(name = "session_twelve", nullable = false)
    private boolean sessionTwelve;

    @Column(name = "session_thirteen", nullable = false)
    private boolean sessionThirteen;

    @Column(name = "session_fourteen", nullable = false)
    private boolean sessionFourteen;

    @Column(name = "session_fifteen", nullable = false)
    private boolean sessionFifteen;
}
