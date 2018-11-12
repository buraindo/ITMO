<#-- @ftlvariable name="user" type="ru.itmo.webmail.model.domain.User" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="target" type="java.lang.String" -->
<#-- @ftlvariable name="messages" type="ru.itmo.webmail.model.domain.Message[]" -->
<#import "common.ftl" as c>

<@c.page>
    <div class="form-box">
        <div class="header">Send message</div>
        <div class="body">
            <form method="post" action="/talks">
                <input type="hidden" name="action" value="send">
                <div class="field">
                    <div class="name">
                        <label for="target">Nickname</label>
                    </div>
                    <div class="value">
                        <input id="target" name="target" value="${target!}"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Message</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text"></textarea>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            <#if error??>
            <div class="error">
                ${error}
            </div>
            </#if>
            </form>
        </div>
    </div>
    <div class="messages">
        <#list messages as message>
            <div class="title"><#if user.login == message.target>From: ${message.source}<#else>To: ${message.target}</#if></div>
            <div class="information">At ${message.creationTime}</div>
            <div class="body">${message.text}</div>
        </#list>
    </div>
</@c.page>