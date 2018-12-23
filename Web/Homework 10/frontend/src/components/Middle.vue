<template>
    <div class="middle">
        <aside>
            <SidebarPost v-for="post in viewPosts" :post="post" :key="post.id"/>
        </aside>
        <main>
            <Index v-if="page === 'Index'"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <AddPost v-if="page === 'AddPost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Users v-if="page === 'Users'"/>
            <Posts :posts="posts" v-if="page === 'Posts'"/>
            <Post :postId="postId" v-if="page === 'Post'"/>
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import AddPost from './middle/AddPost';
    import SidebarPost from './SidebarPost';
    import EditPost from './middle/EditPost';
    import Users from './middle/Users';
    import Posts from './middle/Posts';
    import Post from './middle/Post';

    import axios from 'axios'

    export default {
        name: "Middle",
        data: function () {
            return {
                page: "Index",
                posts: {},
                postId: undefined,
            }
        },
        computed: {
            viewPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
            }
        },
        components: {
            EditPost,
            Index,
            Enter,
            Register,
            SidebarPost,
            AddPost,
            Users,
            Posts,
            Post,
        },
        beforeCreate() {
            this.$root.$on("onChangePage", page => {
                this.page = page;
            });
            this.$root.$on("onChangePost", postId => {
                this.postId = postId;
                this.page = 'Post';
                window.scrollTo(0,0);
            })
        },
        beforeMount() {
            axios.get('posts').then((response) => {
                this.posts = response.data;
            });
        }
    }
</script>

<style scoped>

</style>
