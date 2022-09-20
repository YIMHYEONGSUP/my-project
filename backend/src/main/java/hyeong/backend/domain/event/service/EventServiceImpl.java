package hyeong.backend.domain.event.service;

import hyeong.backend.domain.event.dto.EventRegisterResponseDTO;
import hyeong.backend.domain.event.entity.persist.Event;
import hyeong.backend.domain.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventRegisterResponseDTO createEvent(Event event) {
        return EventRegisterResponseDTO.from(eventRepository.save(event));
    }
}
