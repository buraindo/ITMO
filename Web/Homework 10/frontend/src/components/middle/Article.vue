<!--suppress HtmlUnknownAnchorTarget -->
<template>
    <article>
        <div class="title"><a href="#page=Post" @click="changePost(post.id)">{{ post.title }}</a></div>
        <div class="information">By {{ post.author.login }}</div>
        <div class="body">{{ post.text }}</div>
    </article>
</template>

<script>
    export default {
        name: "Article",
        props: ['post'],
        methods: {
            changePost: function(postId) {
                this.$root.$emit("onChangePost", postId);
            }
        }
    }
</script>

<style scoped>

</style>