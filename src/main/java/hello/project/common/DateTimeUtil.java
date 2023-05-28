package hello.project.common;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    /** UTC -> 현지시간 변환
     * 데이터베이스에서 가져온 UTC 시간을 로그인한 유저의 시간대에 맞게 변환해준다.
     * @param localDateTime UTC 시간
     * @param code 로그인한 유저의 시간대 정보
     * @return
     */
    public static LocalDateTime utcToLocalDateTime(LocalDateTime localDateTime, String code) {
        ZoneId utcZone = ZoneId.of("UTC");
        ZoneId localZoneId = ZoneId.of(code);
        ZonedDateTime utcTimezone = ZonedDateTime.of(localDateTime, utcZone);
        ZonedDateTime localTimezone= utcTimezone.withZoneSameInstant(localZoneId);
        return localTimezone.toLocalDateTime();
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

}
