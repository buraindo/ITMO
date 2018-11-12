<#-- @ftlvariable name="message" type="java.lang.String" -->

<#import "common.ftl" as c>

<@c.page>
    <#if message??>
        <p>${message}</p>
    </#if>

    <div class="articles"></div>

    <template class="article">
        <article>
            <div class="title"></div>
            <div class="information"></div>
            <div class="body"></div>
        </article>
    </template>

    <script>
        $(function () {
            var $articles = $(".articles");
            $articles.text("Loading...");
            $.post("", {action: "find"}, function (articles) {
                if (articles.length > 0) {
                    $articles.empty();
                    articles.forEach(function (article) {
                        var $article = cloneTemplate($("template.article"));
                        $article.find(".title").text(article.title);
                        $article.find(".information").html('At ' + article.creationTime);
                        $article.find(".body").text(article.text);
                        $articles.append($article);
                    });
                } else {
                    $articles.text('No articles available :(');
                }
            })
        })
    </script>
</@c.page>
