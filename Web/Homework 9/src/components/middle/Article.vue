<!--suppress HtmlUnknownAnchorTarget -->
<template>
    <article>
        <div class="title"><a href="#page=Post" @click="changePage('Post', post)">{{ post.title }}</a></div>
        <div class="information">By {{ users[post.userId].login }}</div>
        <div class="body">{{ post.text }}</div>
    </article>
</template>

<script>
    export default {
        name: "Article",
        props: ['users', 'post'],
        methods: {
            changePage: function(page, post) {
                this.$root.$emit("onChangePage", page, post);
            }
        }
    }
</script>

<style scoped>

</style>