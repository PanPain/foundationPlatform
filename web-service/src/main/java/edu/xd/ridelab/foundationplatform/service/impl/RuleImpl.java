package edu.xd.ridelab.foundationplatform.service.impl;/**
 * Created by Administrator on 2018/5/10.
 */

import edu.xd.ridelab.foundationplatform.mapperInterface.RuleMapper;
import edu.xd.ridelab.foundationplatform.mysql.vo.Rule;
import edu.xd.ridelab.foundationplatform.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author xuziheng
 *@date 2018/5/10
 *@since 1.0
 */
@Service
public class RuleImpl implements RuleService{
	@Autowired
	private RuleMapper ruleMapper;

	@Override
	/**
	* 
	* @author xuziheng
	* @date 2018/5/10
	* @return java.util.List<edu.xd.ridelab.foundationplatform.mysql.vo.Rule>
	* @throws
	* @since 1.0
	*/
	public List<Rule> getAllRules(int offset, int pageNum) throws Exception{
		return ruleMapper.selectAllRules(offset,pageNum);
	}

	@Override
	public List<Rule> getRulesBySomeConditions(String ruleName, String ruleType, String targetMac, String status, int offset, int pageNum) throws Exception {
		return ruleMapper.getRulesBySomeConditions(ruleName,ruleType,targetMac,status,offset,pageNum);
	}

	@Override
	public Rule getRule(Long ruleId) throws Exception {
		return ruleMapper.getRule(ruleId);
	}

	@Override
	public int insertRule(Rule rule) throws Exception {
		return ruleMapper.insertSelective(rule);
	}

	@Override
	public int deleteRule(Long ruleId) throws Exception {
		return ruleMapper.deleteByPrimaryKey(ruleId);
	}

	@Override
	public int modifyRuleByRuleId(Rule rule) throws Exception {
		return ruleMapper.updateByPrimaryKeySelective(rule);
	}
}
