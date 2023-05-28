package hello.project.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Slf4j
public class TaskSearchCond {
    private String keyword;
    private LocalDateTime start;
    private LocalDateTime end;

    public TaskSearchCond(TaskSearchForm form) {
        String keyword = form.getKeyword();
        String start = form.getStart();
        String end = form.getEnd();

        if(keyword == null ||keyword.isEmpty()) {
            this.keyword = null;
        } else {
            this.keyword = keyword;
        }

        if (start == null || start.isEmpty()) {
            this.start = null;
        } else {
            this.start = convertStrToDateTime(form.getStart());
        }
        if (end == null || end.isEmpty()) {
            this.start = null;
        } else {
            this.start = convertStrToDateTime(form.getEnd());
        }
    }

    private LocalDateTime convertStrToDateTime(String plainText) {
        log.info("debug3");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(plainText, formatter).atStartOfDay();
    }

}
