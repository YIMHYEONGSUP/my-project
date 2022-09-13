package hyeong.backend.domain.info.subwayInfo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping("/api/v1/subwayHourlyServiceInfo")
public class SubwayHourlyPassengerInfoController {

    StringBuilder seoulData = new StringBuilder();

    String ulrStr = "${open-api.seoul.address}";



}
