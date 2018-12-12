package edu.xd.ridelab.foundationplatform.service;

public interface InterceptorPermissionCheckService {
    /**
     * 根据当前请求的uri，判断是页面请求还是数据请求
     * 当前实现方法是：页面请求带有“.html”，数据请求没有后缀
     * @param requestUri the request uri
     * */
    public boolean checkIsPageRequest(String requestUri);

    /**
     * 检查当前发起的用户数据请求是否在用户权限范围内，不在范围内，不能做查询，直接在拦截器阶段过滤
     * @param userId the userId
     * @param requestUri the request uri
     * @return boolean true means the user has the permission,false means has no permission
     * */
    public boolean checkUserHasPermission(Long userId,String requestUri);
}
