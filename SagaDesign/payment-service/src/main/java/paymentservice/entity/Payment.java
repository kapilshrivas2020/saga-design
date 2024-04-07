package paymentservice.entity;

import commondtos.Events.OrderStatus;
import commondtos.Events.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	private Integer id;
	private Integer userId;
	private Integer orderId;
	private Integer amount;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
}
