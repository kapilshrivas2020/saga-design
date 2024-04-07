package orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import commondtos.Dtos.OrderRequestDto;
import commondtos.Events.OrderStatus;
import jakarta.transaction.Transactional;
import orderservice.entity.PurchaseOrder;
import orderservice.repository.OrderRepository;



@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
//	@Transactional
	public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
		
		try {
		PurchaseOrder order = this.orderRepo.save(convertDtoToEntity(orderRequestDto));
		orderRequestDto.setOrderId(order.getId());
		
		ObjectMapper mp = new ObjectMapper();
		mp.enable(SerializationFeature.INDENT_OUTPUT);
		String message = mp.writeValueAsString(order);
		
		this.jmsTemplate.convertAndSend(OrderStatus.ORDER_CREATED.toString(), message);
		return order;
		
		}catch(Exception e) {
			
		}
		
		return new PurchaseOrder();
	}
	
	
	public List<PurchaseOrder> getAllOrders(){
		return this.orderRepo.findAll();
	}
	

	
	public String cancelOrder(int orderId) {
		try {
			PurchaseOrder or = this.orderRepo.findById(orderId).orElse(null);
			or.setOrderStatus(OrderStatus.ORDER_CANCELLED);
			this.orderRepo.save(or);
			
			
			ObjectMapper mp = new ObjectMapper();
			mp.enable(SerializationFeature.INDENT_OUTPUT);
			String message = mp.writeValueAsString(or);
			this.jmsTemplate.convertAndSend(OrderStatus.ORDER_CANCELLED.toString(), message);
			return message;
		}catch(Exception e) {
			
		}
		return "Order not finished";
	}
	
	
	
	private PurchaseOrder convertDtoToEntity(OrderRequestDto request) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setProductId(request.getProductId());
		purchaseOrder.setUserId(request.getUserId());
		purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
		purchaseOrder.setPrice(request.getAmount());
		return purchaseOrder;
	}

}
