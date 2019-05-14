<#import "parts/base.ftl" as b>
<@b.page>
    <#include "parts/head.ftl">
    <#include "parts/nav_bar.ftl">
    <div class="productPage">
        <div class="prodPic">
            <img src="/resources/img/qwe.png" alt="pic" class="prodPicture"></div>
            <div class="prodDesc">
                <p>${product.title}</p>
                <p>${product.description}</p>
                <p>${product.price}</p>
                <a href="${springMacroRequestContext.requestUri}/order">
                    <button class="dob">Заказать</button>
                </a>
            </div>
    </div>
</@b.page>