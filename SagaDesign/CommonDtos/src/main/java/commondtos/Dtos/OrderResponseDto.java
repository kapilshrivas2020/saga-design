package commondtos.Dtos;

import commondtos.Events.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

	
	private Integer userId;
	
	private Integer productId;
	
	private Integer amount;
	
	private Integer orderId;
	
	private OrderStatus orderStatus;
}
