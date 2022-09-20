package hyeong.backend.domain.event.service;

import hyeong.backend.domain.event.dto.EventRegisterResponseDTO;
import hyeong.backend.domain.event.entity.persist.Event;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    EventRegisterResponseDTO createEvent (Event event);
}
