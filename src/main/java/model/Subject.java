package model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "code", nullable = false)
    private String code;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @Column(name = "date_end", nullable = false)
    private Date date_end;

    @Column(name = "day_of_week", nullable = false)
    private int dayOfWeek;

    @Column(name = "time_start", nullable = false)
    private Timestamp timeStart;

    @Column(name = "time_end", nullable = false)
    private Timestamp timeEnd;

    @JoinColumn(name = "id_room")
    private int roomId;
}
