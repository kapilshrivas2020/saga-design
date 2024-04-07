package commondtos.Events;

import java.util.Date;
import java.util.UUID;

import commondtos.Dtos.PaymentRequestDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymentEvent implements Event{
	
	private UUID eventId = UUID.randomUUID();
	
	private Date eventDate= new Date();
	
	private PaymentRequestDto paymentRequestDto;
	
	private PaymentStatus paymentStatus;

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

	public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
		super();
		this.paymentRequestDto = paymentRequestDto;
		this.paymentStatus = paymentStatus;
	}
	
	

}
