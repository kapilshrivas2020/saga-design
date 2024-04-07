package commondtos.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

	private Integer userId;
	
	private Integer amount;
	
	private Integer orderId;
	
	
}
