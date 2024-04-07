package paymentservice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import paymentservice.entity.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	public Optional<Payment> findByOrderId(Integer orderId);

}
