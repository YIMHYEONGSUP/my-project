package hyeong.backend.domain.item.repository;

import hyeong.backend.domain.item.entity.persist.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item , Long> , ItemRepositoryCustom {

}
