package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.mysql.vo.Manufacturer;

import java.util.List;

/**
 * @Author ChenXiang
 * @Date 2018/05/08,16:52
 */
public interface ManufacturerService {

    /**
     * @methodname
     * @description
     * @author ChenXiang
     * @date 18:37,2018/5/8
     * @para
     * @return
     */
    List<Manufacturer> getManufacturerList(String mfName,String mfOrgNo,String mfAddress,int offset,int pageNum);

    /**
     * @methodname
     * @description
     * @author ChenXiang
     * @date 18:40,2018/5/8
     * @para
     * @return
     */

    Manufacturer getManufacturer(Long manufacturerId) throws Exception;

    /**
     * @methodname 
     * @description 
     * @author ChenXiang
     * @date 18:42,2018/5/8
     * @para 
     * @return 
     */
    int insertManufacturer(Manufacturer manufacturer) throws  Exception;
    
    /**
     * @methodname 
     * @description 
     * @author ChenXiang
     * @date 18:43,2018/5/8
     * @para 
     * @return 
     */
     
    int deleteManufacturer(long manuFacturerId) throws Exception;
    
    /**
     * @methodname 
     * @description 
     * @author ChenXiang
     * @date 18:45,2018/5/8
     * @para 
     * @return 
     */

    int updateManufacturerByManuId(Manufacturer manufacturer)throws Exception;

    /**
     * @methodname
     * @description 获取所有的厂商list
     * @author ChenXiang
     * @date 20:33,2018/5/8
     * @para
     * @return
     */

    List<Manufacturer> getAllManu(int offset,int pageNum)throws Exception;


    
}
