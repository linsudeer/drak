<style scoped>
a {
    color: #8590a6;
}
 .el-header {
        background-color: #fff;
        vertical-align: middle;
        height: 60px;
        line-height: 60px;
        border-bottom:1px #DCDFE6 solid; 
    }
    .navtitle {
        font-size: 18px;
        font-weight: bold;
    }
    .logo {
        color: #909399;
        font-size: 30px;
    }
    .title {
        margin-bottom: 15px;
    }
    .title input {
        border: none;
        font-size: 2rem;
        font-weight: bold;
        color: #666;
        width:100%;
        height: 50px;
    }
    .publish {
        color: #999;
        padding: 0 16px;
        text-align: center;
    }
    .publish-content {
        max-height: 270px;
        overflow: auto;
        
        font-size: 14px;
        color: gray;
        line-height: 1.7;
    }
    .publish-tip {
        padding: 10px 0;
        color: #999;
    }

    .publish-title {
        text-align: left;
        font-size: 20px;
        font-weight: bold;
        color: #999;
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    }
    .publish-tags {
        margin-top: 16px;
    }
    .publish-tags li {
        position: relative;
        float: left;
        list-style: none;
        margin: 0 15px 10px 0;
    }
    .publish-taglink {
        color: gray;
        border-radius: 15px;
        font-size: 14px;
        line-height: 20px;
        padding: 5px 36px 5px 16px;
        background: #eee;
    }
    .publish-removeTag {
        cursor: pointer;
        position: absolute;
        width: 18px;
        height: 18px;
        right: 0;
        top: 62%;
        margin-top: -8px;
        text-align: center;
        line-height: 11px;
    }
    .publish-taglink, .publish-tagsWrapper {
        display: inline-block;
        max-width: 100%;
        box-sizing: border-box;
    }
    .publish-tagsWrapper {
        cursor: pointer;
        position: relative;
    }
    .publish-stepOneButton {
        float:right;
        border-color: #0080ff;
        color: #0080ff;
    }
    .publishBtn{
        border-color: #0080ff;
        color: #0080ff;
    }

    .publish-search input {
        color: gray;
        border:  none;
    }
    .el-icon-search {
        font-size: 18px;
    }
    .more{
        text-align: center;
    }
    .more-list>li {
        padding: 15px 20px;
        margin: 10px 0;
        cursor: pointer;
    }
    .more-list>li:hover{
        background-color: #f1f1f1;
    }
    .more .el-popover {
        padding: 0;
    }
    .el-icon-more-outline {
        font-size: 28px;
        color: #ccc;
        vertical-align: middle;
    }
    .article-container {
        margin-top: 20px;
    }
    .article-title .title-info {
        text-align: center;
        font-size: 25px;
    }
    .article-text {
        margin-top: 30px;
    }
    .v-note-wrapper .v-note-panel.shadow {
        box-shadow: none;
    }
    .v-note-wrapper .v-note-panel .v-note-edit.divarea-wrapper .content-input-wrapper {
        padding: 0;
    }
    .head {
        width: 35px;
        height: 35px;
        float: left;
        margin-right: 5px;
        padding-top: 5px;
    }  
    .head img {
        max-width: 100%;
    }
</style>

<template>
    <main>
        <el-header>
            <el-row>
                <el-col :span="21" class="logo">
                    <router-link to="/">LS</router-link>
                </el-col>
                <el-col :span="2" class="publish"  v-if="isLogin">
                   <el-button @click="toWrite" class="publishBtn" icon="el-icon-edit-outline">写文章</el-button>
                </el-col>
                <el-col :span="1" class="more"  v-if="isLogin">
                    <el-popover placement="bottom" width="200" trigger="click">
                        <el-button type="text" slot="reference"><i class="el-icon-more-outline"></i></el-button>
                        <ul class="more-list">
                            <li>草稿</li>
                            <li>我的文章</li>
                        </ul>
                    </el-popover>
                    
                </el-col>
            </el-row>
        </el-header>
        <article>
            <el-row class="article-container">
                <el-col :span="12" :offset="6">
                    <div class="info-box article-title ">
                        <div class="title-info">{{article.articleTitle}}</div>
                        <ul>
                            <li class="head"><img src="../../../static/img/head1.png" slot="reference"></li>
                            <li><a href="javascript:void(0)">{{article.articleAuthorNickname}}</a></li>
                            <li><a href="javascript:void(0)">{{article.articleTags}}</a></li>
                        </ul>
                    </div>
                    <!-- <div class="article-text markdown-body" v-html="article.articleContent"></div> -->
                    <div class="article-text">
                        <mavon-editor :subfield=false :boxShadow=false defaultOpen="preview" :toolbarsFlag=false :editable=false ref=md :value="article.articleContent"></mavon-editor>
                    </div>
                </el-col>
            </el-row>
        </article>
    </main>
</template>


<script>
    import ArticleApi from '@/api/article'
    import Store from 'store'
    import Global from '@/global'
    import {mavonEditor} from 'mavon-editor'
    import 'mavon-editor/dist/css/index.css'
    export default {
        name:'articleDetail',
        components: {
            mavonEditor
        },
        data(){
            return {
                article:{}
            }
        },
        computed: {
            isLogin: function(){
                return Store.get(Global.ISLOGIN)
            }
        },
        mounted() {
            var _this = this;
            let id = this.$route.params.id;
            ArticleApi.detail(id).then(result => {
                _this.article = result.data
            }).catch(error => {
                console.log(error)
            })
        },
        methods:{
            toWrite: function(){
                this.$router.push('/write/')
            }
        }
    }
</script>