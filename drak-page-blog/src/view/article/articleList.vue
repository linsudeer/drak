<style lang="stylus">
.blogs{
	padding: 30px 0;
	position:relative;
	border-bottom:#CCC 1px solid;
	overflow:hidden;
}
.blogs figure{
	float:left;
	width:25%;
	overflow:hidden;
}
.blogs figure img{
	width:100%;
	margin:auto;
	-moz-transition:all 0.5s;
	-webkit-transition:all 0.5s;
	-o-transition:all 0.5s;
	transition:all 0.5s;
}
.blogs ul{
	float:right;
	line-height:22px;
	width:72%;
	color:#756F71;
}
.blogs h3{
	font-size:16px;
	font-weight:bold;
	transition:all 0.5s;
	margin-bottom:10px;
}
.blogs h3 a{
	color:#474645;
}
.blogs h3 a:hover { 
  color: #066; 
  text-decoration: underline 
}
.blogs .autor{
	overflow:hidden;
    clear:both; 
    margin:10px 0;
    display:inline-block;
    color:#838383;
    width:100%;	
}
.blogs .autor span{
	margin:0 10px 0 0;
	padding-left:20px;
}
.blogs .autor span a { color: #759b08; }
.blogs .autor span a:hover { text-decoration: underline }
</style>
<template>
  <article>
    <div class="blogs" v-for="article in articles" :key="article.oid">
      <figure>
        <img :src="article.articleThumb" ::onerror="errorImg">
      </figure>
      <ul>
        <h3 @click="toDetail(article.oid)"><a href="javascript:void(0)">{{ article.articleTitle }}</a></h3>
        <p>"通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是彼此交换名片，然后郑重或是出于礼貌用手机记下对方的电话号码。在快节奏的生活里，我们不知不觉中就成为住在别人手机里的朋友。又因某些意外，变成了别人手机里匆忙的过客，这种快餐式的友谊 ..."</p>
        <p class="autor">
          <span class="f_l">
            <a href="#">{{ article.articleAuthorNickname }}</a>
          </span>
          <span class="f_l">{{getTime(article.articleCreateTime)}}</span>
          <span class="f_r">	
            赞（<a href="#">{{ article.articleGoodCount }}</a>）
          </span>
          <span class="viewnum f_r">
            浏览（<a href="#">{{ article.articleViewCount }}</a>）
          </span>
          <span class="f_r">	
            评论（<a href="#">{{ article.articleCommentCount }}</a>）
          </span>
        </p>
      </ul>
    </div>
  </article>
</template>

<script>
import ArticleApi from "@/api/article";
import { formatTime } from "@/utils";
export default {
  name: "recommend",
  components: {},
  data() {
    return {
      articles: "",
      errorImg: 'this.src="' + require("../../../static/img/item_default.jpg") + '"'
    };
  },
  computed: {},
  mounted: function() {
    let _this = this;
    let params = this.$route.params;
    params.type = params.type?params.type:'category'
    params.oid = params.oid?params.oid:1
    
    if(params.type=='category') {
      ArticleApi.listByCategory(params.oid)
        .then(response => {
          this.articles = response.data;
        })
        .catch(erro => {
          _this.$message(erro);
        });
    }else {

    }
    
  },
  methods: {
    getTime(time) {
      return formatTime(time);
    },
    toDetail(id) {
      this.$router.push('/articles/'+id)
      // const { href } = this.$router.resolve({
      //   path: "/articles/" + id
      // });
      // window.open(href, "_blank");
    }
  }
};
</script>
