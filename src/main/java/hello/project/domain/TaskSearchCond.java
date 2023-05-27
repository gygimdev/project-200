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
        log.info("::::TaskSearchCond - form.getStart(): {}", form.getStart());
        this.keyword = form.getKeyword();
        if (form.getStart() != null) {
            this.start = convertStrToDateTime(form.getStart());
        }
        if (form.getEnd() != null) {
            this.end = convertStrToDateTime(form.getEnd());
        }
    }

    private static LocalDateTime convertStrToDateTime(String plainText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(plainText, formatter).atStartOfDay();
    }

}
