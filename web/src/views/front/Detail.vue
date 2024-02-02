<template>
  <div class="detail">

    <el-card class="box-card" shadow="hover" v>
      <div slot="header" class="clearfix">
        <h1>{{ detail.title }}</h1>
        <el-descriptions class="margin-top" title="" :column="3" border >
          <template slot="extra">
          </template>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              发布人
            </template>
            {{ detail.userListVO.username }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-mobile-phone"></i>
              所属栏目
            </template>
            {{detail.categoryText}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              浏览量
            </template>
            <el-tag size="small">{{ detail.views }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              发布时间
            </template>
            {{ detail.createTime }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-mobile-phone"></i>
              更新时间
            </template>
            {{detail.updateTime}}
          </el-descriptions-item>
        </el-descriptions>


      </div>
      <div class="text" v-html="detail.content"></div>

    </el-card>
  </div>
</template>

<script>
export default {
  name: "detail",
  data() {
    return {
      id: 0,
      detail: {}
    }
  },
  methods: {
    getDetail(e) {
      this.$axios.get("/article/uDetail?id=" + e).then((res) => {
        this.detail = res.data
      })
    }
  },
  created() {
    console.log(this.$route)
    this.getDetail(this.$route.query.id)


  }
}
</script>

<style scoped>
.detail{
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