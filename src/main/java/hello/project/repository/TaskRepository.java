//package hello.project.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {
//
//    /**
//     * 회원의 household 에 속한 모든 Task 조회하기
//     * @param memberName 회원이름
//     * @return List<Task>
//     */
//    @Query("SELECT t FROM Task t JOIN t.household h JOIN h.members m WHERE m.email = :email order by dueDate")
//    List<Task> findAllTaskBelongMemberHousehold(@Param("email") String memberName);
//
//}
