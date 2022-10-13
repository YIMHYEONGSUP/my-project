package hyeong.backend.domain.order.service;

import hyeong.backend.domain.order.dto.OrdersResponseDTO;
import hyeong.backend.domain.order.entity.persist.Orders;
import hyeong.backend.domain.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Override
    public OrdersResponseDTO orders(Orders orders) {

        return OrdersResponseDTO.from(ordersRepository.save(orders));
    }
}
