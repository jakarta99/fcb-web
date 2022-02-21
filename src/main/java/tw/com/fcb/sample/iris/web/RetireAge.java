package tw.com.fcb.sample.iris.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetireAge {
		private Long id;
		private String type;
	    private int voluntary_cnt;
	    private int age_cnt;
	    private int order_cnt;
	    private RetireEnum retireEnum;
	    
}
