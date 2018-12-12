package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.PermissionMapper;
import edu.xd.ridelab.foundationplatform.service.InterceptorPermissionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterceptorPermissionCheckImpl implements InterceptorPermissionCheckService{

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public boolean checkIsPageRequest(String requestUri) {
        return requestUri.contains("html");
    }

    @Override
    public boolean checkUserHasPermission(Long userId, String requestUri) {
        return permissionMapper.checkUserHasPermission(userId,requestUri) != null;
    }
}
