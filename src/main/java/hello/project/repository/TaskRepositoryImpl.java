package hello.project.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.project.domain.Task;
import hello.project.domain.TaskSearchCond;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static hello.project.domain.QTask.*;

@Slf4j
@RequiredArgsConstructor
public class TaskRepositoryImpl implements  TaskRepositoryCustom {
    private final EntityManager em;


    @Override
    public List<Task> startQuery(TaskSearchCond cond) {

        //BooleanBuilder
        BooleanBuilder builder = new BooleanBuilder();
        if (cond.getKeyword() != null) {
            builder.and(task.name.contains(cond.getKeyword()));
        }

        if (cond.getStart() != null && cond.getEnd() != null) {
            builder.and(task.dueDate.between(cond.getStart(), cond.getEnd()));
        } else if (cond.getStart() != null) {
            builder.and(task.dueDate.after(cond.getStart()));
        } else if (cond.getEnd() != null) {
            builder.and(task.dueDate.before(cond.getEnd()));
        }

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        List<Task> findTask = queryFactory
                .select(task)
                .from(task)
                .where(builder)
                .fetch();

        log.info(":::: 결과확인 {}", findTask);
        return findTask;
    }
}
