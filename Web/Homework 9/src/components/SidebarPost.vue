<!--suppress HtmlUnknownAnchorTarget -->
<template>
    <section>
        <div class="header">
            By {{users[post.userId].login}}
        </div>
        <div class="body">
            {{post.text}}
        </div>
        <div class="footer">
            <a href="#page=Post" @click="changePage('Post', post)">View all</a>
        </div>
    </section>

</template>

<script>
    export default {
        props: ['users', 'post'],
        name: "SidebarPost",
        methods: {
            changePage: function(page, post) {
                this.$root.$emit("onChangePage", page, post);
            }
        }
    }
</script>

<style scoped>

</style>
