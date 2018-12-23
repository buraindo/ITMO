<template>
    <!--suppress HtmlUnknownTag -->
    <body id="app">
    <Header :user="user"/>
    <Middle/>
    <Footer/>
    </body>
</template>

<script>
    import axios from 'axios';
    import Header from './components/Header'
    import Middle from './components/Middle'
    import Footer from './components/Footer'

    axios.defaults.baseURL = '/api/1/';

    export default {
        name: 'app',
        data: function () {
            return this.$root.$data;
        },
        components: {
            Header,
            Middle,
            Footer
        }, beforeCreate() {
            this.$root.$on("onLogout", () => {
                localStorage.removeItem("token");
                this.user = null;
                axios.defaults.headers = {};
            });
            this.$root.$on("onEnter", (token) => {
                localStorage.setItem("token", token);
                axios.defaults.headers = {
                    Authorization: "Bearer " + token
                };
                axios.get("users/authenticated").then(response => {
                    this.user = response.data;
                    this.userId = this.user.id;
                    this.$root.$emit("onEnterSuccess");
                });
            });
            this.$root.$on("onRegister", (login, name, password) => {
                axios.post('users', {
                    login: login, name: name, password: password,
                }).then(response => {
                    if (response.data['success']) {
                        this.$root.$emit("onRegisterSuccess");
                    } else {
                        this.$root.$emit("onRegistrationError", response.data['error'].join("\r\n"));
                    }
                });
            });
            this.$root.$on("onAddPost", (title, text) => {
                if (this.userId) {
                    axios.post('posts', {
                        title: title, text: text, userId: this.userId,
                    }).then(response => {
                        if (response.data['success']) {
                            this.$root.$emit("onAddPostSuccess");
                        } else {
                            this.$root.$emit("onAddPostValidationError", response.data['error'].join("\r\n"));
                        }
                    });
                } else {
                    this.$root.$emit("onAddPostValidationError", "No access");
                }
            });
            this.$root.$on("onEditPost", (id, title, text) => {
                if (this.userId) {
                    if (!id) {
                        this.$root.$emit("onEditPostValidationError", "ID is invalid");
                    } else if (!text) {
                        this.$root.$emit("onEditPostValidationError", "Text is invalid");
                    } else {
                        axios.post('edit', {
                            id: id, title: title, text: text,
                        }).then(response => {
                            if (response.data['success']) {
                                this.$root.$emit("onEditPostSuccess");
                            } else {
                                this.$root.$emit("onEditPostValidationError", response.data['error'].join("\r\n"));
                            }
                        });
                    }
                } else {
                    this.$root.$emit("onEditPostValidationError", "No access");
                }
            });
            this.$root.$on("onAddComment", (id, text) => {
                if (this.userId) {
                    axios.post('post', {
                        postId: id, text: text, userId: this.userId
                    }).then(response => {
                        if (response.data['success']) {
                            this.$root.$emit("onAddCommentSuccess");
                        } else {
                            this.$root.$emit("onAddCommentValidationError", response.data['error'].join("\r\n"));
                        }
                    });
                } else {
                    this.$root.$emit("onAddCommentValidationError", "No access");
                }
            });
        }, beforeMount() {
            if (localStorage.getItem("token") && !this.user) {
                this.$root.$emit("onEnter", localStorage.getItem("token"));
            }
        },
    }
</script>

<style>
</style>
