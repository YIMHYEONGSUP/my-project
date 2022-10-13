package hyeong.backend.domain.order.service;

import hyeong.backend.domain.order.dto.OrdersResponseDTO;
import hyeong.backend.domain.order.entity.persist.Orders;
import org.springframework.stereotype.Service;

@Service
public interface OrdersService {

    OrdersResponseDTO orders(Orders orders);
}
