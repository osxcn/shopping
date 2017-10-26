package com.leaves.shopping.service.impl;

import com.leaves.shopping.mapper.InventoryMapper;
import com.leaves.shopping.mapper.TrxMapper;
import com.leaves.shopping.model.Inventory;
import com.leaves.shopping.model.Trx;
import com.leaves.shopping.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 偶是小菜鸟
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

    @Resource
    private TrxMapper trxMapper;

    @Override
    public int getInventoryByCid(Integer cid) throws Exception {
        int allInventory = 0;
        int allSell = 0;
        // 获取进货总数
        Inventory inventory = new Inventory();
        inventory.setCid(cid);
        List<Inventory> inventories = inventoryMapper.select(inventory);
        if(inventories != null) {
            for(Inventory i : inventories) {
                allInventory += i.getNum();
            }
        }
        // 获取售出总数
        Trx trx = new Trx();
        trx.setContentId(cid);
        List<Trx> trxList = trxMapper.select(trx);
        if(trxList != null) {
            for(Trx t : trxList) {
                allSell += t.getNum();
            }
        }

        return allInventory - allSell;
    }
}
