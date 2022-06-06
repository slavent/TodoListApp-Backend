package ru.pycak.todolistapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="taskid")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date creationDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date editDate;

    @JsonIgnore
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.DETACH
    })
    @JoinColumn(name = "userid")
    private User user;

    @Column(name = "userid", insertable = false, updatable = false)
    private Long userId;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.DETACH
    })
    @JoinColumn(name = "statusid")
    private TaskStatus status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TaskComment> comments;
}
