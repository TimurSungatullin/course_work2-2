<#macro loginForm>
    <#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
        <@form.form method="post" modelAttribute="loginForm" cssClass="login-form">
            <@form.label path="email">Email</@form.label>
            <@form.input path="email"/>
            <@form.errors path="email" /><br>
            <@form.label path="password">Password</@form.label>
            <@form.password path="password"/>
            <@form.errors path="password"/><br>
            <input type="submit" value="Login">
        </@form.form>
</#macro>