<template>
    <div>
        <Header></Header>
        <el-main>
        <div class="mblog">
            <h2>{{blog.title}}</h2>
            <el-link icon="el-icon-edit" v-if="ownBlog">
                <router-link :to="{name:'BlogAdd',params:{blogId:blog.id}}">
                编辑
                </router-link>
            </el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link @click="del" v-if="ownBlog">
                    删除
            </el-link>
            <el-divider></el-divider>
            <div class="markdown-body" v-html="blog.content"></div>
            <el-backtop class="pad"></el-backtop>
        </div>
        </el-main>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import "github-markdown-css/github-markdown.css"
    export default {
        name: "BlogDetail",
        components:{Header},
        data(){
            return{
                blog:{
                    id:'',
                    title:'',
                    content:''
                },
                ownBlog:false
            }
        },
    methods:{
        del(){
            const _this = this
            this.$axios.get('/blog/'+ this.blog.id + '/del').then(res =>{
                console.log(res)

                this.$message({
                    message: '删除成功',
                    type: 'success'
                });
                _this.$router.push('/blogs')
            })
        }
    },
        created() {
            // 获取路由参数
            const blogId = this.$route.params.blogId
            const _this = this
            this.$axios.get('/blog/' + blogId).then(res => {
                const blog = res.data.data
                // console.log("blog",blog)
                _this.blog.id = blog.id
                _this.blog.title = blog.title

                // 使用 markdown-it 工具将markdown编辑的文本进行 渲染
                var MarkdownIt = require("markdown-it")
                var md = new MarkdownIt()
                var result = md.render(blog.content)
                _this.blog.content = result

                _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)
                console.log(_this.ownBlog)
            })
        }
    }
</script>

<style scoped>
.mblog{
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    width: 100%;
    min-height: 700px;
    padding: 20px 15px;
}
    .pad{

    }
</style>