package com.leaves.shopping.mapper;

import com.leaves.shopping.model.Trx;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 偶是小菜鸟
 */
@Repository
public interface TrxMapper extends Mapper<Trx> {

    /**
     * 获取已购买的商品ID列表
     * @param personId 用户ID
     * @return 商品ID列表
     * @throws Exception exception
     */
    List<Integer> getBuyContentIdList(Integer personId) throws Exception;

    /**
     * 获取售出的商品ID列表
     * @return 商品ID列表
     * @throws Exception exception
     */
    List<Integer> getIsSellContentIdList() throws Exception;
}
