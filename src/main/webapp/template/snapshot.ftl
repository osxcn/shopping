<!DOCTYPE html>
<html>
<#include "include/head.ftl">
<body>
<#include "include/support.ftl">
<#include "include/header.ftl">
<div class="g-doc">
    <#if !product>
    <div class="n-result">
        <h3>内容不存在！</h3>
    </div>
    <#else>
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${product.image}" alt="${product.title}" ></div>
        <div class="cnt">
            <h2>${product.title}</h2>
            <p class="summary">${product.summary}</p>
            <div class="price">
                商品单价：
                <span class="v-unit">¥</span>
                <span class="v-value">${product.price}</span>
            </div>
            <div class="num">
                购买数量：
                <span class="totalNum" id="allNum">${product.num}</span>
            </div>
            <div class="price">
                支付金额：
                <span class="v-unit">¥</span>
                <span class="v-value">${product.payment}</span>
            </div>
            <div class="time">
                购买时间：
                <span class="v-time">${product.time?number_to_datetime?string("yyyy-MM-dd HH:mm")}</span>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
    ${product.text}
    </div>
    </#if>
</div>
<#include "include/footer.ftl">
</body>
</html>