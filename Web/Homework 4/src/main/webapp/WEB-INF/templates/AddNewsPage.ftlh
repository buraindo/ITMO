<#import "common.ftl" as c>

<@c.page>
<div class="form-box">
    <div class="header">Add news</div>
    <div class="body">
        <form method="post" action="/addNews">
            <input type="hidden" name="action" value="add">
            <div class="field">
                <div class="name">
                    <label for="text">Text</label>
                </div>
                <div class="value">
                    <textarea id="text" name="text"></textarea>
                </div>
            </div>
            <div class="button-field">
                <input type="submit" value="Add">
            </div>
        <#if error??>
        <div class="error">
            ${error}
        </div>
        </#if>
        </form>
    </div>
</div>
</@c.page>
