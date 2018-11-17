<#import "./common.ftlh" as c>

<@c.page>
<div>
    <#if userData??>
        <div>
            <p>Id: ${userData.id}</p>
            <p>Login: ${userData.login}</p>
            <p>Created at: ${userData.creationTime}</p>
        </div>
    <#else>
    No such user :(
    </#if>
</div>
</@c.page>
