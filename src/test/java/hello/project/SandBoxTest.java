package hello.project;

import hello.project.repository.MemberRepository;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@SpringBootTest
public class SandBoxTest {

    @Test
    public void testTimezoneConvert() {

        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        String timeStr = "2023-05-28T00:40:00";
        LocalDateTime localDateTime = LocalDateTime.parse(timeStr);

        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId).withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime result = zonedDateTime.toLocalDateTime();
        System.out.println("result = " + result);
    }

}
