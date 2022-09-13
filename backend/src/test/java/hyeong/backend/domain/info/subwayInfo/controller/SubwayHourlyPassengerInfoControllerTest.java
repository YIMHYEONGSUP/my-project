package hyeong.backend.domain.info.subwayInfo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubwayHourlyPassengerInfoControllerTest {

    @Value("${open-api.seoul.address}")
    private String address;

    @Value("${open-api.seoul.auth-key}")
    private String authKey;

    @Value("${open-api.seoul.type}")
    private String type;

    @Value("${open-api.seoul.service-name}")
    private String serviceName;



    @Test
    public void test() {

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String format = date.format(new Date());
        String[] formats = format.split("-");

        String year = formats[0];
        String month = formats[1];
        String day = formats[2];

        System.out.println("date = " + format);

        String ulrStr = address + "/" +
                authKey + "/" +
                serviceName + "/"
                ;

        System.out.println("ulrStr = " + ulrStr);
    }

}