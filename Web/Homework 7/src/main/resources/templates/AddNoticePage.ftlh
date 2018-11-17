<#import "./common.ftlh" as c>

<@c.page>
<div class="add-notice form-box">
    <div class="header">Add new notice</div>
    <div class="body">
        <form method="post" action="/add">
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
        </form>
    </div>
</div>
</@c.page>