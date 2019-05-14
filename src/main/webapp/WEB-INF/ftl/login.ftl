<#import "forms/loginform.ftl" as loginForm>
<#import "parts/base.ftl" as b>
<@b.page>
    <div class="form">
        <#if RequestParameters.warning??>
            <span>Сначала авторизуйтесь</span>
        </#if>
        <#if RequestParameters.error??>
            <span>Пароль и/или Логин введены неправильно</span>
        </#if>
        <#if RequestParameters.message??>
            <span>Пользователь успешно зарегистрирован</span>
        </#if>
        <@loginForm.loginForm/>
    </div>
</@b.page>