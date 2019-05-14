<#import "parts/base.ftl" as b>
<@b.page>
    <#include "parts/head.ftl">
    <#include "parts/nav_bar.ftl">
    <#if RequestParameters.message??>
        Спасибо за заказ<br>
    </#if>
    <div class="products">
         <#list products as product>
             <a href="products/${product.id}">
                 <div class="product">
                     <img src="" class="imgProd">
                     <div>
                         <p class="titleProd">${product.title}</p>
                         <p class="priceProd">${product.price}</p>
                     </div>
                 </div>
             </a>
         </#list>
    </div>
</@b.page>