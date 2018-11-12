<#-- @ftlvariable name="message" type="java.lang.String" -->
<#-- @ftlvariable name="userService" type="ru.itmo.webmail.model.service.UserService" -->
<#-- @ftlvariable name="news" type="ru.itmo.webmail.model.domain.News[]" -->

<#import "common.ftl" as c>

<@c.page>
    <#if message??>
        <p>${message}</p>
    </#if>
    <h1>Welcome to the index page</h1>
    <#if news??>
        <div class="news">
            <#list news as article>
                <article>
                    <div class="information">By ${userService.findById(article.userId).login}</div>
                    <div class="body">
                        ${article.text}
                    </div>
                </article>
            </#list>
        </div>
    </#if>
</@c.page>
