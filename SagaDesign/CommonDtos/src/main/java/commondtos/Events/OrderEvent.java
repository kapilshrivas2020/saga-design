package commondtos.Events;

import java.util.Date;
import java.util.UUID;

import commondtos.Dtos.OrderRequestDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderEvent implements Event{
	
	
	private UUID eventId = UUID.randomUUID();
	
	private Date eventDate= new Date();
	
	private OrderRequestDto orderRequestDto;
	
	private OrderStatus orderStatus;

	@Override
	public UUID getEventId() {
		// TODO Auto-generated method stub
		return eventId;
	}

	@Override
	public Date getEventDate() {
		// TODO Auto-generated method stub
		return eventDate;
	}

	public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
		super();
		this.orderRequestDto = orderRequestDto;
		this.orderStatus = orderStatus;
	}
	
	

}
