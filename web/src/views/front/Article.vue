<template>
<div>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    <el-menu-item index="1">首页</el-menu-item>
    <el-menu-item index="4"><a href="/" target="_blank">后台管理</a></el-menu-item>
  </el-menu>
  <div class="line"></div>
  <div class="article">
    <el-card class="box-card" shadow="hover" v-for="item in tableData" :key="item.id">
      <div slot="header" class="clearfix">
        <span>{{ item.title | delHtmlTag}}</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="toDetail(item.id)">查看</el-button>
      </div>
<!--      <div v-for="o in 4" :key="o" class="text item">-->
<!--        {{'列表内容 ' + o }}-->
<!--      </div>-->

      <div class="text item">
        {{item.content | delHtmlTag }}......
      </div>

    </el-card>
    <!--自定义分页组件-->
    <CustomPages @size-change="handleSizeChange" @current-change="handleCurrentChange" :total="pageInfo.total"></CustomPages>
    <!--自定义分页组件-->
  </div>


</div>
</template>

<script>
import create from "@/mixins/create";

export default {
  name: "Article",
  data() {
    return {
      activeIndex:'1',

      api:{
        pageUrl:"/article/uPage"
      }
    }

  },
  mixins:[create],
  methods:{
    handleSelect(key,keyPath)
    {
      console.log(key,keyPath)
    },
    toDetail(e) {
      //跳转到详情页并且携带id参数
      this.$router.push({
        path: "detail", query: {
          id: e
        }
      })
    }

  },
  filters: {
    delHtmlTag(str) {
      //去掉所有的html标记
      return str.replace(/<[^>]+>/g, "").slice(0, 200)
    }
  }
}
</script>

<style scoped>
.article{
  max-width:1000px;
  margin: 0 auto;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  /*width: 480px;*/
  margin-top: 20px;
}
</style>