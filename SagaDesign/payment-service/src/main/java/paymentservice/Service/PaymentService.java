package paymentservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import commondtos.Events.PaymentStatus;
import paymentservice.Repository.PaymentRepository;
import paymentservice.entity.Payment;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public String makePayment(int paymentId) {
		
		try {
			Payment p = this.paymentRepo.findById(paymentId).orElse(null);
			p.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);
			this.paymentRepo.save(p);
			
			ObjectMapper mp = new ObjectMapper();
			mp.enable(SerializationFeature.INDENT_OUTPUT);
			String message = mp.writeValueAsString(p);
			
			this.jmsTemplate.convertAndSend(PaymentStatus.PAYMENT_COMPLETED.toString(), message);
			return "Payment done!";
		}catch(Exception e) {
			
		}
		
		return "Payment Failed";
	}

}
