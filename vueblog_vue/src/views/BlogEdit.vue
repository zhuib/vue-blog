<template>
    <div>
        <Header></Header>
        <div class="m-content">
            <el-form ref="ruleForm" :rules ="rules" :model="ruleForm" label-width="80px" class="ruleForm">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>
                <el-form-item label="摘要" prop="description">
                    <el-input type="password" v-model="ruleForm.description"></el-input>
                </el-form-item>
                <el-form-item label="内容" prop="content">
<!--                    编辑器-->
                    <mavon-editor v-model="ruleForm.content"></mavon-editor>
                </el-form-item>
                <el-form-item class="btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">发布</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import Header from "../components/Header";
    export default {
        name: "BlogEdit",
        components:{Header},
        data() {
            return {
                ruleForm: {
                    id:'',
                    title: '',
                    description: '',
                    content:''
                },
                rules:{
                    title:[
                        {require:true,message:"请输入标题",trigger:"blur"},
                        {min:3,max:25,message:"长度在3到25个字符",trigger:"blur"},
                    ],
                    description: [
                        {require:true,message:"请输入摘要",trigger:"change"},
                        {min:3,max:25,message:"长度在3到25个字符",trigger:"blur"},
                    ],
                    content: [
                        {require:true,message:"请输入内容",trigger:"change"}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid)=>{
                    if (valid){
                        const _this = this
                        _this.$axios.post('/blog/edit', this.ruleForm,{
                            headers:{
                                "Authorization":localStorage.getItem("token")
                            }}).then(res =>{
                                console.log(res)
                            _this.$alert('操作成功', '提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/blogs')
                                }
                            });
                        })
                    }else {
                        return false;
                    }
                });
            },
            resetForm(formName){
                this.$refs[formName].resetFields();
            }
        },
        created() {
            const blogId = this.$route.params.blogId
            console.log(blogId)
            const _this = this
            if(blogId){
                this.$axios.get('/blog/' + blogId).then(res => {
                    const blog = res.data.data
                    // console.log("blog",blog)
                    _this.ruleForm.id = blog.id
                    _this.ruleForm.title = blog.title
                    _this.ruleForm.description = blog.description
                    _this.ruleForm.content = blog.content
                })
            }
        }
    }
</script>

<style scoped>

    .m-content{
        max-width: 960px;
        margin: 0 auto;
    }
    .btn{
        min-width: 960px;
        margin: 0 auto;
    }

</style>