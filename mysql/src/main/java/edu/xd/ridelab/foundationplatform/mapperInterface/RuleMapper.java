package edu.xd.ridelab.foundationplatform.mapperInterface;


import edu.xd.ridelab.foundationplatform.mysql.vo.Rule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleMapper {
    int deleteByPrimaryKey(Long ruleId);

    int insert(Rule record);

    int insertSelective(Rule record);

    Rule selectByPrimaryKey(Long ruleId);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKey(Rule record);

    List<Rule> selectAllRules(@Param("offset") int offset, @Param("pageNum") int pageNum);

    List<Rule> getRulesBySomeConditions(@Param("ruleName") String ruleName, @Param("ruleType") String ruleType,@Param("targetMac") String targetMac,@Param("status") String status,@Param("offset") int offset,@Param("pageNum") int pageNum);

    Rule getRule(@Param("ruleId") Long ruleId);
}