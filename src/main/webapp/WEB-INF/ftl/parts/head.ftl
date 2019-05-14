<header>
    <div class="phone">
        <#if temp??>
            Сейчас такая температура: ${temp}
        </#if>
        <p class="tel">+79274122088 Россия</p>
        <p class="tel">+79273319638 Казань</p>
    </div>
    <div class="logoDiv">
        <div class="logoPic">
            <img src="/resources/img/icon.png" alt="logo" class="logo">
        </div>
        <div class="title">Уточка</div>
    </div>
    <div class="headerButtons">
        <#if user??>
            Здравствуйте, ${user.email} <br>
            <a href="/logout"><button class="headerButton">Выйти</button></a>
        <#else>
            <a href="/login"><button class="headerButton">Войти</button></a>
            <a href="/register"><button class="headerButton">Регистрация</button></a>
        </#if>
    </div>
</header>