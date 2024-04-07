package orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderservice.entity.PurchaseOrder;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
