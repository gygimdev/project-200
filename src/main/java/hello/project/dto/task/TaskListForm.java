//package hello.project.dto.task;
//
//import hello.project.common.DateTimeUtil;
//import hello.project.domain.Member;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//public class TaskListForm {
//
//    private List<TaskListDto> tasks;
//
//    public TaskListForm(List<TaskDto> dtos, Member member) {
//
//        //로그인한 회원의 시간대 정보(예: Asia/Seoul)
//        String code = member.getTimezone().getTimeCode();
//        System.out.println("code = " + code);
//
//        List<TaskListDto> tasks = new ArrayList<>();
//        for(TaskDto dto : dtos) {
//            TaskListDto taskListDto = new TaskListDto();
//
//            LocalDateTime result = dto.getDueDate();
//            System.out.println("previous localDateTime = " + result);
//
//            // 1. UTC 시간을 유저의 로컬시간으로 변경
//            LocalDateTime localDateTime = DateTimeUtil.utcToLocalDateTime(dto.getDueDate(), code);
//            System.out.println("localDateTime = " + localDateTime);
//
//            // 2. 템플릿에 그리기 위해 포멧 변경
//            String formattedDateTime = DateTimeUtil.formatLocalDateTime(localDateTime, "HH:mm");
//            System.out.println("formattedDateTime = " + formattedDateTime);
//
//            // 3. TaskListDto 변환
//            taskListDto.setId(dto.getId());
//            taskListDto.setName(dto.getName());
//            taskListDto.setStatus(dto.getStatus());
//            taskListDto.setDueDate(formattedDateTime);
//            tasks.add(taskListDto);
//        }
//        this.tasks = tasks;
//    }
//
//}
