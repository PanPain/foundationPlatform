package edu.xd.ridelab.foundationplatform.service;/**
 * Created by Administrator on 2018/5/10.
 */

import edu.xd.ridelab.foundationplatform.mysql.vo.Rule;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *@author xuziheng
 *@date 2018/5/10
 *@since 1.0
 */
public interface RuleService {
	List<Rule> getAllRules(int curPage, int pageNum) throws Exception;

	List<Rule> getRulesBySomeConditions(String ruleName, String ruleType, String targetMac, String status, int offset, int pageNum) throws Exception;

	Rule getRule(Long ruleId) throws Exception;

	int insertRule(Rule rule) throws Exception;

	int deleteRule(Long ruleId) throws Exception;

	int modifyRuleByRuleId(Rule rule) throws Exception;
}
