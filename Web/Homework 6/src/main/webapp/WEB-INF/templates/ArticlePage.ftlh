<#import "common.ftl" as c>

<@c.page>
<div class="add-article form-box">
    <div class="header">Add news</div>
    <div class="body">
        <form method="post" action="/article">
            <input type="hidden" name="action" value="add">
            <div class="field">
                <div class="name">
                    <label for="title">Title</label>
                </div>
                <div class="value">
                    <input id="title" type="text" name="title">
                </div>
            </div>
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
            <div class="error">
        </form>
    </div>
</div>
<script>
    $(function() {
        $(".add-article form").submit(function () {
            var title = $(this).find("input[name='title']").val();
            var text = $(this).find("textarea[name='text']").val();
            var $error = $(this).find(".error").text("");
            $.post("", {action: "add", title: title, text: text}, function (response) {
                if (response["success"]) {
                    setMessage("You have added new article!");
                    document.location.href = "/";
                } else {
                    $error.text(response["error"]);
                }
            });
            return false;
        });
    })
</script>
</@c.page>