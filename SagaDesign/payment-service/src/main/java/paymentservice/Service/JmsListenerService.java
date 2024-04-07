package paymentservice.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import commondtos.Events.PaymentStatus;
import paymentservice.Repository.PaymentRepository;
import paymentservice.entity.Payment;

@Service
@EnableJms
public class JmsListenerService {

	@Autowired
	private PaymentRepository paymentRepo;
	
	
	@JmsListener(destination="ORDER_CREATED")
	public void listener1(String message) {
		
		try {
			JSONObject json = new JSONObject(message);
			int orderId = json.getInt("orderId");
			int amt = json.getInt("amount");
			
			Payment p = new Payment();
			p.setOrderId(orderId);
			p.setAmount(amt);
			p.setPaymentStatus(PaymentStatus.PAYMENT_PENDING);
			
			this.paymentRepo.save(p);
		}catch(Exception e) {
			
		}
		
	}
	
	@JmsListener(destination="ORDER_CANCELLED")
	public void listener2(String message) {
		
		try {
		JSONObject json = new JSONObject(message);
		int orderId = json.getInt("orderId");
		
		Payment p = this.paymentRepo.findByOrderId(orderId).orElse(null);
		p.setPaymentStatus(PaymentStatus.PAYMENT_REFUND);
		this.paymentRepo.save(p);
		}catch(Exception e) {
			
		}
	}
}
