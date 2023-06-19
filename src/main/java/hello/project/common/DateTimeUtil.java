package hello.project.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {

    /** Date 객체를 해당 지역 LocalDateTime 으로 변환
     * @param date
     * @param localCode
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date, String localCode) {
        ZoneId zoneId = ZoneId.of(localCode);
        Instant instant = date.toInstant();

        // 지역시간으로 변환
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDateTime();
    }

    /** 시간대 변환
     * 현재 시간대(origin)을 변경할 시간대(target)
     * @param localDateTime 지역 시간대
     * @param originTimezoneCode 현재 시간대
     * @param targetTimezoneCode 변경 시간대
     * @return
     */
    public static LocalDateTime convertLocalDateTime(LocalDateTime localDateTime, String originTimezoneCode, String targetTimezoneCode) {
        ZoneId originZoneId = ZoneId.of(originTimezoneCode);
        ZoneId targetZoneId = ZoneId.of(targetTimezoneCode);
        ZonedDateTime utcTimezone = ZonedDateTime.of(localDateTime, originZoneId);
        ZonedDateTime localTimezone= utcTimezone.withZoneSameInstant(targetZoneId);
        return localTimezone.toLocalDateTime();
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

}
