<#macro signupForm is_signup button_title>
    <#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
    <div class="form">
        <@form.form method="post" modelAttribute="user" cssClass="login-form">
            <#if is_signup>
                <@form.errors path="email"/>
                <@form.label path="email">Email</@form.label>
                <@form.input path="email"/><br>
                <@form.errors path="password"/>
                <@form.label path="password">Password</@form.label>
                <@form.password path="password"/><br>
                <@form.errors path="passwordRepeat"/>
                <@form.label path="passwordRepeat">Password repeat</@form.label>
                <@form.password path="passwordRepeat"/><br>
            </#if>
            <@form.errors path="first_name"/>
            <@form.label path="first_name">First name</@form.label>
            <@form.input path="first_name"/><br>
            <@form.errors path="second_name"/>
            <@form.label path="second_name">Second name</@form.label>
            <@form.input path="second_name"/><br>
            <@form.errors path="country"/>
            <@form.label path="country">Country</@form.label>
            <@form.select path="country">
                <@form.option value="NONE" label="--- Select ---" />
                <@form.options items=countries itemValue="id" itemLabel="title" />
            </@form.select><br>
            <@form.errors path="city"/>
            <@form.label path="country">City</@form.label>
            <@form.select path="city">
                <@form.option value="NONE" label="--- Select ---" />
                <@form.options items=cities itemValue="id" itemLabel="title"/>
            </@form.select><br>
            <input type="submit" value="${button_title}">
        </@form.form>
    </div>
</#macro>