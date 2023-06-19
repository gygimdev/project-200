//package hello.project.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//
//@Entity
//@Getter
//public class TaskMember {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "task_member_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "task_id")
//    private Task task;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;
//}
