package edu.xd.ridelab.foundationplatform.model;/**
 * Created by Administrator on 2018/5/23.
 */

import edu.xd.ridelab.foundationplatform.mysql.vo.MacMachine;
import lombok.Data;

import java.math.BigDecimal;

/**
 *@author xuziheng
 *@date 2018/5/23
 *@since 1.0
 */
@Data
public class MachineCollectCount {

	private MacMachine macMachine;

	private BigDecimal count;
}
