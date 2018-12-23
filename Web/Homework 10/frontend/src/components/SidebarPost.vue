<!--suppress HtmlUnknownAnchorTarget -->
<template>
    <section>
        <div class="header">
            {{ post.title }}
        </div>
        <div class="body">
            {{ post.text }}
        </div>
        <div class="footer">
            <small>By {{ post.author.login }}</small><a href="#page=Post" @click="changePost(post.id)">View all</a>
        </div>
    </section>

</template>

<script>
    export default {
        props: ['post'],
        name: "SidebarPost",
        methods: {
            changePost: function(postId) {
                this.$root.$emit("onChangePost", postId);
            }
        }
    }
</script>

<style scoped>

</style>
