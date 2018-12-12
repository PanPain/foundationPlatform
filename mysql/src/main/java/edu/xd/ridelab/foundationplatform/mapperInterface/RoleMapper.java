package edu.xd.ridelab.foundationplatform.mapperInterface;



import edu.xd.ridelab.foundationplatform.mysql.vo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleMapper {

    List<Role> selectByConds(Role role);

    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int batchDeleteByPrimaryKey(List<Long> list);

    long selectRoleIdByRoleName(@Param("roleName") String roleName);
}