package hyeong.backend.domain.order.repository;

import hyeong.backend.domain.order.dto.OrdersResponseDTO;
import hyeong.backend.domain.order.entity.persist.Orders;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> , OrdersRepositoryCustom {

    Orders save(Orders orders);
}

