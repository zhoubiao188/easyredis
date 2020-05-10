package cn.ityoudream.service;

/**
 * @description: 全局唯一id生成器
 * @author: zhoubiao
 * @create: 2020-05-10 20:15
 **/
public interface IdGeneratorService {
    /**
     * 生成全局唯一id
     * @return id
     */
    Long incrementId();
}
