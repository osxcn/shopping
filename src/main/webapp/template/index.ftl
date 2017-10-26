<!DOCTYPE html>
<html>
<#include "include/head.ftl">
<body>
<#include "include/support.ftl">
<#include "include/header.ftl">
<#assign listType = RequestParameters['type']>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li <#if !listType || (listType != 1 && listType != 2)>class="z-sel"</#if> ><a href="/">所有内容</a></li>
                <#if user>
                <li <#if listType == 1>class="z-sel"</#if> ><a href="/?type=1">已购买的内容</a></li>
                <li <#if listType == 2>class="z-sel"</#if> ><a href="/?type=2">未购买的内容</a></li>
                </#if>
            </ul>
        </div>
    </div>
    <#if !products || !products?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
    <#else>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
            <#list products as product>
                <li id="p-${product.id}">
                    <a href="/detail?id=${product.id}" class="link">
                        <div class="img"><img src="${product.image}" alt="${product.title}"></div>
                        <h3>${product.title}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${product.salePrice}</span></div>
                    </a>
                </li>
            </#list>
        </ul>
    </div>
    </#if>
</div>
<#include "include/footer.ftl">
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageIndex.js"></script>
</body>
</html>