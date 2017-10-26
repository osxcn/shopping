package com.leaves.shopping.service.impl;

import com.leaves.shopping.dto.BuyDto;
import com.leaves.shopping.mapper.ContentMapper;
import com.leaves.shopping.mapper.TrxMapper;
import com.leaves.shopping.model.Content;
import com.leaves.shopping.model.Trx;
import com.leaves.shopping.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 偶是小菜鸟
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private TrxMapper trxMapper;

    @Override
    public List<BuyDto> buyList(Integer userId) throws Exception {
        List<BuyDto> buyList = new ArrayList<BuyDto>();

        Example example = new Example(Trx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("personId", userId);
        example.setOrderByClause("time desc");
        List<Trx> trxList = trxMapper.selectByExample(example);
        for (Trx trx : trxList) {
            BuyDto buyDto = new BuyDto();
            Content content = contentMapper.selectByPrimaryKey(trx.getContentId());
            buyDto.setId(trx.getId());
            buyDto.setPrice(trx.getPrice());
            buyDto.setNum(trx.getNum());
            buyDto.setPayment(trx.getPayment());
            buyDto.setTime(trx.getTime());
            buyDto.setTitle(content.getTitle());
            buyDto.setSummary(content.getSummary());
            byte[] icon = content.getIcon();
            if (icon != null) {
                buyDto.setImage(new String(icon, "utf-8"));
            }
            byte[] text = content.getText();
            if (text != null) {
                buyDto.setDetail(new String(text, "utf-8"));
            }

            buyList.add(buyDto);
        }

        return buyList;
    }

    @Override
    public BuyDto getContentInfoByTid(Integer tid) throws Exception {
        BuyDto buyDto = new BuyDto();
        // 获取购买记录信息
        Trx trx = trxMapper.selectByPrimaryKey(tid);
        buyDto.setId(trx.getId());
        buyDto.setPrice(trx.getPrice());
        buyDto.setNum(trx.getNum());
        buyDto.setPayment(trx.getPayment());
        buyDto.setTime(trx.getTime());
        // 获取购买的商品的信息
        Content content = contentMapper.selectByPrimaryKey(trx.getContentId());
        buyDto.setTitle(content.getTitle());
        buyDto.setSummary(content.getSummary());
        byte[] icon = content.getIcon();
        if (icon != null) {
            buyDto.setImage(new String(icon, "utf-8"));
        }
        byte[] text = content.getText();
        if (text != null) {
            buyDto.setDetail(new String(text, "utf-8"));
        }

        return buyDto;
    }
}
