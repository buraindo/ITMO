<#import "/spring.ftl" as spring>
<#import "./common.ftlh" as c>

<#macro error field>
    <@spring.bind field/>
    <#list spring.status.errorMessages as error>
        <div class="error">${error}</div>
        <#break/>
    </#list>
</#macro>

<@c.page>
<div>
    <#if notice??>
        <article>
            <div class="information">By ${notice.author.login}</div>
            <div class="body">${notice.text}</div>
            <h1>Tags: </h1>
            <#if notice.tags?size != 0>
                <div class="tags">
                    <#list notice.tags as tag>${tag.name} </#list>
                </div>
            </#if>
            <h1>Comments: </h1>
            <#list notice.comments as comment>
            <div style="padding-left: 2em">
                <article>
                    <div class="information">By ${comment.user.login}</div>
                    <div class="body">${comment.text}</div>
                </article>
            </div>
            </#list>
        </article>
        <#if user??>
        <div class="form-box">
            <div class="header">New comment</div>
            <div class="body">
                <form method="post" action="">
                    <div class="field">
                        <div class="name">
                            <label for="text">Text</label>
                        </div>
                        <div class="value">
                            <textarea id="text" rows="10" name="text">${comment.text!?html}</textarea>
                        </div>
                        <@error 'comment.text'/>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Add">
                    </div>
                </form>
            </div>
        </div>
        </#if>
    <#else>
    No such notice :(
    </#if>
</div>
</@c.page>