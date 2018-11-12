<#-- @ftlvariable name="articles" type="ru.itmo.webmail.model.domain.Article[]" -->
<#import "common.ftl" as c>

<@c.page>
<div class="datatable articles">
    <div class="caption">Articles</div>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Show/Hide</th>
        </tr>
        </thead>
        <tbody>
        <#list articles as article>
            <tr>
                <td>
                    ${article.id}
                </td>
                <td>
                    ${article.title}
                </td>
                <td>
                    <div class="show-hide-button">
                        <div class="id" style="display: none">${article.id}</div>
                        <div class="userId" style="display: none">${article.userId}</div>
                        <button><#if article.isHidden()>Show<#else>Hide</#if></button>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<script>
    $(".show-hide-button").click(function () {
        var id = $(this).find('.id').text();
        var userId = $(this).find('.userId').text();
        var value = $(this).find('button').text();
        if (value === 'Hide') {
            $(this).find('button').text('Show');
        } else {
            $(this).find('button').text('Hide');
        }
        $.post("", {action: "update", id: id, userId: userId, type: value});
        return false;
    });
</script>
</@c.page>