package hello.project.dto.task;

import hello.project.common.DateTimeUtil;
import hello.project.domain.Member;
import hello.project.domain.TaskStatus;
import hello.project.domain.Timezone;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.h2.util.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TaskUpdateForm {

    private Long id;

    @NotEmpty
    private String name;

    private String content;

    @NotEmpty
    private String status;

    @NotNull
    private LocalDateTime dueDate;

    public TaskUpdateForm(){};

    /** TaskUpdateForm 템플릿 데이터 매핑
     * 회면에 보내기 위해 데이터를 From 데이터로 변환
     * 사용자에 맞는 시간대로 변경해준다.(UTC -> 현지시간)
     * TaskDto -> TaskUpdateForm
     * @param taskDto
     */
    public TaskUpdateForm(TaskDto taskDto, Member loginMember) {
        //로그인한 유저의 timezone 정보 불러오기
        String code = loginMember.getTimezone().getTimeCode();

        //매핑
        this.id = taskDto.getId();
        this.name = taskDto.getName();
        this.content = taskDto.getContent();
        this.status = statusToString(taskDto.getStatus());
        // UTC 에서 로그인 유저의 현지시간으로 변환
        this.dueDate = DateTimeUtil.utcToLocalDateTime(taskDto.getDueDate(), code);
    }

    private String statusToString(TaskStatus status) {
        return String.valueOf(status);
    }
}
