package orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import commondtos.Dtos.OrderRequestDto;
import orderservice.entity.PurchaseOrder;
import orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public PurchaseOrder createOrder(@RequestBody OrderRequestDto request) {
		return this.orderService.createOrder(request);
	}
	
	@GetMapping("/")
	public List<PurchaseOrder> getOrders(){
		return this.orderService.getAllOrders();
	}

	
	@GetMapping("cancel_order/{orderId}")
	public String cancelOrder(@PathVariable int orderId) {
		return this.orderService.cancelOrder(orderId);
	}
	
}

