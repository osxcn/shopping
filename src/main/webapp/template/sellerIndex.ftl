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
                <li <#if listType == 1>class="z-sel"</#if> ><a href="/?type=1">已售出的内容</a></li>
                <li <#if listType == 2>class="z-sel"</#if> ><a href="/?type=2">未售出的内容</a></li>
            </#if>
            </ul>
        </div>
    </div>
    <#if !Contents || !Contents?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
    <#else>
    <div class="n-tlist">
        <table class="m-table m-table-row n-c4 g-b3" id="newTable">
            <thead>
                <tr>
                    <th colspan="2">内容信息</th>
                    <th>内容单价</th>
                    <th>库存</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <#list Contents as x>
                <tr>
                    <td class="img"><a href="/detail?id=${x.id}&type=seller"><img src="${x.image}" alt="${x.title}"></a></td>
                    <td class="title"><h4><a href="/detail?id=${x.id}&type=seller">${x.title}</a></h4></td>
                    <td class="price"><span class="v-unit">¥</span><span class="value">${x.salePrice}</span></td>
                    <td class="inventory"><span id="inventory">${x.inventory}</span></td>
                    <td class="status">
                        <#if x.status == 1>
                            未发布
                        <#else>
                            发布
                        </#if>
                    </td>
                    <td class="operating">
                        <#if x.status == 1>
                            <a id="switch${x.id}" onclick="switchStatus(${x.id}, '发布')">发布</a>
                            &nbsp;
                        <#else>
                            <a id="switch${x.id}" onclick="switchStatus(${x.id}, '下架')">下架</a>
                            &nbsp;
                        </#if>
                        <a href="/content/edit?id=${x.id}" id="edit${x.id}">修改</a>
                        &nbsp;
                        <a onclick="addInventory(${x.id})">进货</a>
                        <#if !x.isSell>
                            &nbsp;
                            <a id="del${x.id}" onclick="del(${x.id})">删除</a>
                        </#if>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
    </#if>
</div>
<#include "include/footer.ftl">
<#--<script type="text/javascript" src="/js/global.js"></script>-->
<script type="text/javascript" src="/js/sellerIndex.js"></script>
</body>
</html>