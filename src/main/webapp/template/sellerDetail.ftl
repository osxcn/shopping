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
        <input type="hidden" id="cid" value="${product.id}">
        <div class="img"><img src="${product.image}" alt="${product.title}" ></div>
        <div class="cnt">
            <h2>${product.title}</h2>
            <p class="summary">${product.summary}</p>
            <div class="salePrice">
                价格：
                <span class="v-unit">¥</span>
                <span class="v-value">${product.price}</span>
            </div>
            <div class="price">
                促销价：
                <span class="v-unit">¥</span>
                <span class="v-value">${product.salePrice}</span>
            </div>
            <div class="num">
                库存：
                <span class="v-unit">${product.inventory}&nbsp;件</span>
            </div>
            <div class="oprt f-cb">
                <a href="/content/edit?id=${product.id}" class="u-btn u-btn-primary">编 辑</a>
                &emsp;
                <a class="u-btn u-btn-primary" onclick="addInventory(${product.id})">进 货</a>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
    ${product.detail}
    </div>
</#if>
</div>
<#include "include/footer.ftl">
<script type="text/javascript" src="/js/sellerIndex.js"></script>
</body>
</html>