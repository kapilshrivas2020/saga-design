package orderservice.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import commondtos.Events.OrderStatus;
import orderservice.entity.PurchaseOrder;
import orderservice.repository.OrderRepository;

@Service
@EnableJms
public class JmsListenerService {

	@Autowired
	private OrderRepository orderRepo;
	
	@JmsListener(destination="PAYMENT_COMPLETED")
	public void listener(String message) {
		
		try {
			JSONObject json = new JSONObject(message);
			int orderId = json.getInt("orderId");
			PurchaseOrder or = this.orderRepo.findById(orderId).orElse(null);
			or.setOrderStatus(OrderStatus.ORDER_SUCCESSFULLY_PLACED);
			this.orderRepo.save(or);
		}catch(Exception e) {
			System.out.print("Order is not placed");
		}
		
	}
	
	
}
