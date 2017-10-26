<!DOCTYPE html>
<html>
<#include "include/head.ftl">
<body>
<#include "include/support.ftl">
<#include "include/header.ftl">
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb" >
        <h2>已添加到购物车的内容</h2>
    </div>
	<#if !settleAccountList || !settleAccountList?has_content>
		<div class="n-result">
			<h3>暂无内容！</h3>
		</div>
	<#else>
 	<table id="newTable" class="m-table m-table-row n-table g-b3">
		<thead>
			<tr>
                <th>
                    <input type="checkbox" id="allCheck">
                    <label for="allCheck">全选</label>
                </th>
                <th colspan="2">内容信息</th>
                <th>内容单价</th>
                <th>购买数量</th>
                <th>支付金额</th>
                <th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list settleAccountList as sa>
			<tr>
				<td class="checklist">
					<#if sa.status == 0>
					<input type="checkbox" name="check" tid="${sa.id}" />
					</#if>
				</td>
				<td class="img">
                    <a href="/detail?id=${sa.cid}" class="link">
						<img src="${sa.image}" alt="">
					</a>
				</td>
				<td class="title">
					<a href="/detail?id=${sa.cid}" class="link">${sa.title}</a>
				</td>
				<td class="price">
					<span class="v-unit">¥</span>
					<span class="value" id="price${sa.id}">${sa.price}</span>
				</td>
				<#if sa.status == 1>
				<td colspan="2">
					<h2>商品已下架，请删除</h2>
				</td>
				<#else>
                <td class="num">
					<span id="plusNum" class="lessNum" onclick="plusNum(${sa.id})"><a>-</a></span>
					<input type="text" id="allNum${sa.id}" name="allNum" class="totalNum" tid="${sa.id}" value="${sa.num}" />
					<span id="addNum" class="moreNum" onclick="addNum(${sa.id})"><a>+</a></span>
					<input type="hidden" id="inventory${sa.id}" value="${sa.inventory}">
				</td>
                <td class="payment">
					<span class="v-unit">¥</span>
					<span class="value" id="payment${sa.id}">${sa.payment}</span>
				</td>
				</#if>
                <td class="operating">
					<a id="del${sa.id}" onclick="del(${sa.id})">删除</a>
				</td>
			</tr>
			</#list>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6"></td>
                <td><button class="u-btn u-btn-primary" id="Account">结算</button></td>
			</tr>
		</tfoot>
 	</table>
	</#if>
</div>
<#include "include/footer.ftl">
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/settleAccount.js"></script>
</body>
</html>