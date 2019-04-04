<template>
    <div>
        <Article :users="users" :post="post"/>
        <h1>Comments</h1>
        <div style="padding-left: 2em">
            <article v-for="comment in comments" :comment="comment" :key="comment.id">
                <div class="information">By {{ users[comment.userId].login }}</div>
                <div class="body">{{ comment.text }}</div>
            </article>
        </div>
    </div>
</template>

<script>
    import Article from "./Article";
    export default {
        name: "Post",
        components: {Article},
        props: ['post', 'users', 'comments'],
    }
</script>

<style scoped>

</style>