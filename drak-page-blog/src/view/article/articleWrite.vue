<style>
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
    .logo a {
        color: #909399;
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
        right: 12px;
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
    .tip {
        margin-left: 10px;
        font-size: 15px;
        color: #ccc;
        font-weight: normal;
    }
    
</style>
<template>
    <div>
        <el-header>
            <el-row>
                <el-col :span="8" class="logo">
                    <router-link tag="a" to="/">LS</router-link>
                </el-col>
                <el-col :span="13" class="navtitle">
                    <span>写文章</span><span class="tip">{{tip}}</span>
                </el-col>
                <el-col :span="2" class="publish">
                    <el-popover placement="bottom" width="300" trigger="click">
                        <el-button class="publishBtn" slot="reference" icon="el-icon-arrow-down" :disabled="isPublishOne" @click="showPublish">发布</el-button>
                        <div class="publish-panel">
                            <div class="publish-title">发布文章</div>
                            <div class="publish-content">
                                <p class="publish-tip">绑定合适的标签，能方便分类检索，文章也更容易让读者发现。</p>
                                <el-radio-group v-model="article.articleCategoryId">
                                    <el-radio v-for="(category, key) in categorys" :key="key" :label="category.oid" border>{{category.categoryName}}</el-radio>
                                </el-radio-group>
                                <ul class="publish-tags">
                                    <li v-for="(tag, key) in article.articleTags" :key="key">
                                        <span class="publish-tagsWrapper preventCloseOnTarget">
                                            <a class="publish-taglink preventCloseOnTarget">{{tag}}</a>
                                            <a class="publish-removeTag el-icon-close" @click="removeTag(tag)"></a>
                                        </span>
                                    </li>                                               
                                </ul>
                            </div>
                            <div class="publish-search">
                                <el-autocomplete prefix-icon="el-icon-search" v-model="tagName" :fetch-suggestions="queryTags" placeholder="请搜索标签" @keyup.enter.native="genTag" @select="handleSelect"></el-autocomplete>
                            </div>
                             <el-button class="publish-stepOneButton" size="small" @click="publish" :disabled="isPublishTwo">确定并发布</el-button>
                        </div>
                    </el-popover>
                </el-col>
                <el-col :span="1" class="more">
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
        <el-row class="article">
            <el-col :span="24">
                <div class="title">
                    <el-input v-model="article.title" placeholder="请输入标题（最多30个字)"> </el-input>
                </div>
                <div class="">
                    <mavon-editor @change="change" @save="save" @imgAdd="imgAdd" :boxShadow=false :toolbars="toobars" placeholder="正文内容（不少于10个字)" ref=md></mavon-editor>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import ArticleApi from '@/api/article'
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import {arrayToString} from '@/utils'
const ARTICLE_TYPE = {
    DRAFT:"draft",
    PUBLISH:"pulish"
}
export default {
    name:'articleWrite',
    components: {
      mavonEditor
    },
    data() {
        return {
            isPublishOne: true,
            isPublishTwo:true,
            tagName: '',
            tip:'',
            categorys:[],
            toobars: {
                bold: true, // 粗体
                italic: true, // 斜体
                header: true, // 标题
                underline: true, // 下划线
                strikethrough: true, // 中划线
                mark: true, // 标记
                superscript: true, // 上角标
                subscript: true, // 下角标
                quote: true, // 引用
                ol: true, // 有序列表
                ul: true, // 无序列表
                link: true, // 链接
                imagelink: true, // 图片链接
                code: true, // code
                table: true, // 表格
                fullscreen: true, // 全屏编辑
                // readmodel: true, // 沉浸式阅读
                // htmlcode: true, // 展示html源码
                // help: true, // 帮助
                /* 1.3.5 */
                // undo: true, // 上一步
                // redo: true, // 下一步
                // trash: true, // 清空
                // save: true, // 保存（触发events中的save事件）
                /* 1.4.2 */
                navigation: true, // 导航目录
                /* 2.1.8 */
                alignleft: true, // 左对齐
                aligncenter: true, // 居中
                alignright: true, // 右对齐
                /* 2.2.1 */
                subfield: true, // 单双栏模式
                preview: true, // 预览
            },
            article: {
                oid: '',
                title: '',
                content: '',
                contentHtml: '',
                articleCategoryId: 0,
                articleTags: []

            }
        }
    },
    created: function() {

    },
    computed: {
        watchTitle: function() {
            return this.article.title;
        },
        watchCategory: function(){
            return this.article.articleCategoryId;
        },
        watchTags: function(){
            return this.article.articleTags;
        }
    },
    watch: {
        watchTitle: function(newValue, oldValue){
            this.isPublishOne = !this.article.content || this.article.content.length<10 || !newValue;
        },
        watchCategory: function(newValue, oldValue) {
            this.isPublishTwo = this.isPublishOne || !newValue || this.article.articleTags.length==0;
        },
        watchTags: function(newValue, oldValue) {
            this.isPublishTwo = this.isPublishOne || newValue.length==0 || !this.article.articleCategoryId;
        }

    },
    mounted: function() {
        this.queryCatetorys();
    },
    methods: {
        change: function(value, render) {
            this.article.content = value;
            this.article.contentHtml = render;
            this.isPublishOne = !value || value.length<10|| !this.article.title;

            if(render){
                // 这里开始存储草稿信息
                clearTimeout(this.draftTimeout)
                this.tip="草稿保存中..."
                this.draftTimeout = setTimeout(()=>{
                    this.save(value, render, ARTICLE_TYPE.DRAFT);
                },2000)
            }    
        },
        showPublish: function(){

        },
        publish: function(){
            this.save(this.article.content, this.article.contentHtml, ARTICLE_TYPE.PUBLISH);
        },
        save: function(value, render, type)  {
            
            let _this = this;
            var params = {
                articleCategoryId:this.article.articleCategoryId,
                articleType: this.articleType,
                articleTags: arrayToString(_this.article.articleTags),
                articleContent: value,
                articleContentHtml: render,
                articleTitle:this.article.title,
                oid:this.article.oid
            }
            if(!type || type === 'draft'){// 草稿
                ArticleApi.draft(params, _this.article.oid).then(function(result){
                     _this.tip = "草稿保存成功";
                    if(result.data){
                        _this.article.oid = result.data;
                    }
                }).catch(function(erro){
                    _this.$message(erro)
                    _this.tip = "草稿保存失败"
                })
            }else {// 发布
                ArticleApi.publish(params, _this.article.oid).then(function(result) {
                    _this.$message('发布成功');
                    _this.$router.replace('/articles/'+_this.article.oid)
                }).catch(function(erro) {
                    _this.$message(erro)
                })
            }
            
        },
        // 绑定@imgAdd event
        imgAdd(pos, $file){
            // 第一步.将图片上传到服务器.
           var formdata = new FormData();
           formdata.append('file', $file);
           ArticleApi.uploadImage(formdata).then((result) => {
                let url = result.data;
                this.$refs.md.$img2Url(pos, url);
           })
        },
        queryTags: function(queryString, callback){
            if(queryString){
                clearTimeout(this.tagtimeout);
                this.tagtimeout = setTimeout(() => {
                    ArticleApi.getTagsByName(queryString, 5).then(function(res){   
                        let tags = res.data;
                        var tagNames = [];
                        if(tags && tags.length>0){
                            for(let i=0; i<tags.length; i++){   
                                tagNames.push({value:tags[i].tagTitle});
                            }
                        }
                        
                        callback(tagNames);
                    }).catch(function(erro){
                        this.$message(error);
                    })
                }, 300);
                
            }else {
                callback([])
            }
            
        },
        queryCatetorys: function() {
            ArticleApi.getCategorys()
            .then(response => {
                this.categorys = response.data;
            })
            .catch(erro => {
                _this.$message(erro);
            });
        },
        handleSelect: function(item){
            this.tagName = '';
            this.article.articleTags.push(item.value);
            
        },
        removeTag: function(tag) {
            let tags = this.article.articleTags
            tags.splice(tags.indexOf(tag), 1);
        },
        genTag: function() {
            if(this.tagName){
                this.article.articleTags.push(this.tagName)
                this.tagName=''
            }
            
        }
    }
}
</script>

