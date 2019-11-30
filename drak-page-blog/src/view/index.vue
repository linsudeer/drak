<style lang="stylus">
.container
    display: -webkit-box
    display: -ms-flexbox
    display: flex
.container a
    color: #8590a6
aside
    width:48%
    margin-left:20px
    background:#fff
.topnews>h2{
  font-size:16px;
  font-weight:bold;
  line-height:36px;
  color:#333;
  border-bottom:#db6d4c 4px solid;
  }
.topnews>h2 span{
	float:right;
	font-size:12px;
	font-weight:normal;
}
.topnews>h2 span a{
	display:inline-block;
	padding:0 5px;
}
</style>
<template>
    <div id="index">
      <Header-nav></Header-nav>
      <div class="view-contaner">
       <div class="container">
           <div class="topnews">
              <h2>
                <span>
                  <a v-for="(category, key) in categorys" :key="key" href="javascript:void(0)" @click="loadArticlesByCategoryId(category.oid)">{{category.categoryName}}</a>
                </span>	 
                文章推荐
              </h2>
               <!-- 文章列表 -->
                <router-view></router-view>
           </div>
           <aside>
             <!-- 推荐标签 -->
            <div class="cloud">
              <h3>推荐标签</h3>
              <ul>
                <li><a href="#">lsl</a></li>
                <li><a href="#">web开发</a></li>
                <li><a href="#">前端设计</a></li>
                <li><a href="#">Html</a></li>
                <li><a href="#">CSS3</a></li>
                <li><a href="#">CSS3+HTML5</a></li>
                <li><a href="#">百度</a></li>
                <li><a href="#">JavaScript</a></li>
                <li><a href="#">C/C++</a></li>
                <li><a href="#">ASP.NET</a></li>
                <li><a href="#">IOS开发</a></li>
                <li><a href="#">Android开发</a></li>
                <li><a href="#">JAVA</a></li>
                <li><a href="#">VS</a></li>
              </ul>
            </div>
           </aside>
       </div>
    </div>
      
    </div>
   
</template>

<script>
import ArticleApi from "@/api/article";
import HeaderNav from '@/components/head'
export default {
  name: 'index',
  data(){
    return {
      categorys:[]
    }
  },
  components: {
  	 HeaderNav
  },
  mounted: function (){
    ArticleApi.getCategorys()
    .then(response => {
        this.categorys = response.data;
    })
    .catch(erro => {
        _this.$message(erro);
    });
  },
  methods: {
  loadArticlesByCategoryId: function(categoryId) {
    let oid = categoryId?categoryId:this.categorys.pop();
    this.$router.push('/articles/category/'+oid)
  }
  }
}
</script>
