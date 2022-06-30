package ru.pycak.todolistapp.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.pycak.todolistapp.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="taskcomments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="commentid")
    private Long id;

    @Column
    private String text;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date editDate;

    @ManyToOne
    @JoinColumn(name = "taskid")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
}
