<div class="menu">
    <div class="menuButtonDiv"><a class="menuButton" href="/products">Главная</a></div>
        <#list categories as category>
            <div class="menuButtonDiv">
                <a class="menuButton" role="tab" href="/category/${category.title}">${category.title}</a>
            </div>
        </#list>
</div>