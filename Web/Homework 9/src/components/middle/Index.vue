<template>
    <div>
        <Article v-for="post in viewPosts" :post="post" :users="users" :key="post.id"/>
    </div>
</template>

<script>
    import Article from "./Article";
    export default {
        name: "Index",
        components: {Article},
        props: ['users', 'posts'],
        computed: {
            viewPosts: function () {
                return Object.values(this.posts).reverse();
            }
        }
    }
</script>

<style scoped>

</style>
