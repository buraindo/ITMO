<!--suppress HtmlUnknownAnchorTarget -->
<template>
    <div class="datatable">
        <div class="caption">Posts</div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Text</th>
                <th>Author</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="post in posts" :key="post.id">
                <td><a href="#page=Post" @click="changePost(post.id)">{{ post.id }}</a></td>
                <td>{{ post.title }}</td>
                <td>{{ post.text }}</td>
                <td>{{ post.author.name }}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name: "Posts",
        props: ['posts'],
        methods: {
            changePost: function(postId) {
                this.$root.$emit("onChangePost", postId);
            }
        }
    }
</script>

<style scoped>

</style>