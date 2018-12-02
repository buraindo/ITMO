<template>
    <div class="middle">
        <Sidebar :posts="posts" :users="users"/>
        <main>
            <Index :posts="posts" :users="users" v-if="page === 'Index'"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <AddPost v-if="page === 'AddPost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Post :post="post" :users="users" :comments="getComments" v-if="page==='Post'"/>
            <Users :users="users" v-if="page === 'Users'"/>
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import AddPost from './middle/AddPost';
    import EditPost from "./middle/EditPost";
    import Sidebar from "./middle/Sidebar";
    import Users from "./middle/Users";
    import Post from "./middle/Post";

    export default {
        name: "Middle",
        props: ['users', 'posts', 'comments'],
        data: function () {
            return {
                page: "Index",
                post: undefined
            }
        },
        computed: {
            getComments: function() {
                return Object.values(this.comments).filter(c => c.postId === this.post.id);
            }
        },
        components: {
            Post,
            Users,
            Sidebar,
            EditPost,
            Index,
            Enter,
            Register,
            AddPost
        }, beforeCreate() {
            this.$root.$on("onChangePage", (page) => {
                this.page = page;
            });
            this.$root.$on("onChangePage", (page, post) => {
                this.page = page;
                this.post = post;
            });
        }
    }
</script>

<style scoped>

</style>
