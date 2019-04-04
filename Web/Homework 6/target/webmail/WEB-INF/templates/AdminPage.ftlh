<#-- @ftlvariable name="users" type="ru.itmo.webmail.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.webmail.model.domain.User" -->
<#import "common.ftl" as c>

<@c.page>
<div class="datatable users">
    <div class="caption">Admins</div>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Login</th>
            <th>Is admin</th>
            <#if user?? && user.isAdmin()>
                <th></th>
            </#if>
        </tr>
        </thead>
        <tbody>
        <tr>
            <#if user?? && user.isAdmin()>
                <td colspan="4" class="no-data">No data</td>
            <#else>
                <td colspan="3" class="no-data">No data</td>
            </#if>
        </tr>
        </tbody>
    </table>
</div>

<template class="users-tr">
    <tr>
        <td class="id">{{#}}</td>
        <td class="login">{{login}}</td>
        <td class="admin"></td>
        <#if user?? && user.isAdmin()>
            <td>
                <div class="change">
                    <div class="id" style="display: none">${user.id}</div>
                    <button></button>
                </div>
            </td>
        </#if>
    </tr>
</template>
<script>
    $(function () {
        var $noData = $(".users .no-data");
        $noData.text("Loading...");
        $.post("", {action: "find"}, function (users) {
            var $tbody = $(".users tbody");
            if (users.length > 0) {
                $tbody.empty();
                users.forEach(function (user) {
                    var $tr = cloneTemplate($("template.users-tr"));
                    $tr.find(".id").text(user.id);
                    $tr.find(".login").text(user.login);
                    if (user.admin) {
                        $tr.find(".admin").text('Yes');
                        $tr.find(".change button").text('Disable');
                    }
                    else {
                        $tr.find(".admin").text('No');
                        $tr.find(".change button").text('Enable');
                    }
                    $tr.find(".change").click(function() {
                        var id = $(this).find(".id").text();
                        var value = $(this).find('button').text();
                        if (value === 'Disable') {
                            $(this).find('button').text('Enable');
                        } else {
                            $(this).find('button').text('Disable');
                        }
                        $.post("", {action: "update", id: id, type: value});
                        return false;
                    });
                    $tbody.append($tr);
                });
            } else {
                $noData.text("No data");
            }
        })
    })
</script>
</@c.page>