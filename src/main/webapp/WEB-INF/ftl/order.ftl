<#import "parts/base.ftl" as b>
<#import "forms/signupform.ftl" as form>
<@b.page>
    <#include "parts/nav_bar.ftl"/>
    <@form.signupForm false "Buy"/>
</@b.page>