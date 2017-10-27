package com.leaves.shopping.service.impl;

import com.leaves.shopping.mapper.ContentMapper;
import com.leaves.shopping.mapper.TemporaryMapper;
import com.leaves.shopping.mapper.TrxMapper;
import com.leaves.shopping.model.Content;
import com.leaves.shopping.model.Temporary;
import com.leaves.shopping.model.Trx;
import com.leaves.shopping.service.InventoryService;
import com.leaves.shopping.service.SettleAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 偶是小菜鸟
 * CreateTime: 2017-10-23 20:45
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SettleAccountServiceImpl implements SettleAccountService {

    @Resource
    private TrxMapper trxMapper;

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private TemporaryMapper temporaryMapper;

    @Resource
    private InventoryService inventoryService;

    @Override
    public boolean buy(Integer personId, Integer cid, Integer num) throws Exception {
        // 查询商品库存
        int inventory = inventoryService.getInventoryByCid(cid);
        // 如果数量大于库存，返回错误
        if (num > inventory) {
            return false;
        }
        buyContent(personId, cid, num);
        return true;
    }

    @Override
    public boolean buyFromTemporary(Integer personId, Integer tid) throws Exception {
        Temporary temporary = temporaryMapper.selectByPrimaryKey(tid);
        Integer cid = temporary.getCid();
        Integer num = temporary.getNum();
        // 查询商品库存
        int inventory = inventoryService.getInventoryByCid(cid);
        // 如果数量大于库存，返回错误
        if (num > inventory) {
            return false;
        }
        // 从购物车中删除
        temporaryMapper.deleteByPrimaryKey(tid);
        // 购买内容
        buyContent(personId, cid, num);
        return true;
    }

    /**
     * 购买商品
     * @param personId 用户ID
     * @param cid 商品ID
     * @param num 购买数量
     */
    private void buyContent(Integer personId, Integer cid, Integer num) {
        Content content = contentMapper.selectByPrimaryKey(cid);
        Double price = content.getSalePrice();
        Double payment = price * num;
        Trx trx = new Trx();
        trx.setContentId(cid);
        trx.setPersonId(personId);
        trx.setPrice(price);
        trx.setNum(num);
        trx.setPayment(payment);
        trx.setTime((new Date()).getTime());
        trxMapper.insertSelective(trx);
    }
}
