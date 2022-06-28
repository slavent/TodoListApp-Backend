package ru.pycak.todolistapp.entity;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date editDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @Column(name = "userid", insertable = false, updatable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "statusid")
    private TaskStatus status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "taskid")
    private List<TaskComment> comments;
}
