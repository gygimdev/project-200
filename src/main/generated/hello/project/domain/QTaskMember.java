package hello.project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskMember is a Querydsl query type for TaskMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTaskMember extends EntityPathBase<TaskMember> {

    private static final long serialVersionUID = -1532987816L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskMember taskMember = new QTaskMember("taskMember");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QTask task;

    public QTaskMember(String variable) {
        this(TaskMember.class, forVariable(variable), INITS);
    }

    public QTaskMember(Path<? extends TaskMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskMember(PathMetadata metadata, PathInits inits) {
        this(TaskMember.class, metadata, inits);
    }

    public QTaskMember(Class<? extends TaskMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.task = inits.isInitialized("task") ? new QTask(forProperty("task"), inits.get("task")) : null;
    }

}

