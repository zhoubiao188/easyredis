package cn.ityoudream.service;

/**
 * @description: redis实现微信pv
 * @author: zhoubiao
 * @create: 2020-05-10 15:51
 **/
public interface PvService {
    /**
     * 传入文章的id
     * @param id
     * @return
     */
    long view(Integer id);
}
