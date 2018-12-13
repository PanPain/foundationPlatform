package edu.xd.ridelab.foundationplatform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *@author xuziheng
 *@date 2018/5/23
 *@since 1.0
 */
@Data
public class TotalCollectCount {
	//日期
	@JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
	private Date date;
	//每日统计量
	private BigDecimal count;
}
