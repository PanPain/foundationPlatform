package edu.xd.ridelab.foundationplatform.controller;

import com.google.gson.Gson;
import edu.xd.ridelab.foundationplatform.RedisPool.RedisUtils;
import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.RuleMessage;
import edu.xd.ridelab.foundationplatform.mysql.vo.Rule;
import edu.xd.ridelab.foundationplatform.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

/** 布防设置
 *@author zf
 *@date 2018/5/16
 *@since 1.0
 */
@Controller
public class RuleController {
	@Autowired
	private RuleService ruleService;

	private RuleMessage ruleMessage = new RuleMessage();

	private Jedis jedis = null;

	@ResponseBody
	@RequestMapping(value = "/rule/getAllRules",method = RequestMethod.GET)
	public ResponseResult getAllRules(@RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageNum){
		ResponseResult responseResult = new ResponseResult();
		int offset = ( curPage - 1 ) * pageNum;
		try {
			List<Rule> rules= ruleService.getAllRules(offset,pageNum);
			responseResult.setData(rules);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("查询成功");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}

	@RequestMapping(value = "/rule/getRulesBySomeConditions" ,method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult getRulesBySomeConditions(@RequestParam(required = false) String ruleName, @RequestParam(required = false) String ruleType,@RequestParam(required = false) String targetMac, @RequestParam(required = false) String status, @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageNum) {
		ResponseResult responseResult = new ResponseResult();
		List<Rule> rules = null;
		int offset = (curPage - 1) * pageNum;
		try {
			rules = ruleService.getRulesBySomeConditions(ruleName, ruleType, targetMac, status, offset, pageNum);
			responseResult.setData(rules);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("查询成功");
		} catch (Exception e) {
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}


	@RequestMapping(value = "/rule/getRuleDetailInfo" ,method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult getRuleDetailInfo(@RequestParam Long ruleId) {
		ResponseResult responseResult=new ResponseResult();
		Rule rule = null;
		try {
			rule = ruleService.getRule(ruleId);
			responseResult.setData(rule);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("查询成功");
		} catch (Exception e) {
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}

	@RequestMapping(value = "/rule/addRule", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult addRule(@RequestBody Rule rule) {
		ResponseResult responseResult = new ResponseResult();
		Gson gson = new Gson();
		try {
			int number = ruleService.insertRule(rule);
			//布防信息发布到redis对应频道
			jedis = RedisUtils.getJedis();
			ruleMessage.setModifyType("insert");
			ruleMessage.setRule(rule);
			String message = gson.toJson(ruleMessage);
			jedis.publish("rule",message);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功添加" + number + "条");
		} catch (Exception e) {
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return responseResult;
	}

	@RequestMapping(value = "/rule/deleteRule", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseResult deleteRule(@RequestParam Long ruleId) {
		ResponseResult responseResult = new ResponseResult();
		Gson gson = new Gson();
		Rule rule = new Rule();
		try {
			int number = ruleService.deleteRule(ruleId);
			//布防信息发布到redis对应频道
			jedis = RedisUtils.getJedis();
			rule.setRuleId(ruleId);
			ruleMessage.setModifyType("delete");
			ruleMessage.setRule(rule);
			String message = gson.toJson(ruleMessage);
			jedis.publish("rule",message);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功删除" + number + "条");
		} catch (Exception e) {
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return responseResult;
	}

	@RequestMapping(value = "/rule/updateRule", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult updateDistrict(@RequestBody Rule rule) {
		ResponseResult responseResult = new ResponseResult();
		Gson gson = new Gson();
		try {
			int number = ruleService.modifyRuleByRuleId(rule);
			//布防信息发布到redis对应频道
			jedis = RedisUtils.getJedis();
			ruleMessage.setModifyType("modify");
			ruleMessage.setRule(rule);
			String message = gson.toJson(ruleMessage);
			jedis.publish("rule",message);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功更新" + number + "条");
		} catch (Exception e) {
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return responseResult;
	}
}
