<template>
  <div style="height: 100%">
    <el-container style="height: 100%; border: 1px solid #eee;">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <h3 class="title">xxxx博客系统</h3>
        <el-menu :default-openeds="['1', '3']"
                 background-color="#EBEEF5"
        >
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>导航</template>
            <el-menu-item-group>

              <el-menu-item :index="'1-'+item.id" v-for="item in menuList" :key="item.id">
                <router-link tag="div" :to="item.path">{{ item.name }}</router-link>
              </el-menu-item>

            </el-menu-item-group>


          </el-submenu>

        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
  <span class="el-dropdown-link">
    {{ userInfo.username }}<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item> <span @click="editUserInfo">个人信息</span></el-dropdown-item>
              <el-dropdown-item ><span @click="logOut()">退出登录</span> </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>

        <el-main>


          <router-view></router-view>


        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>

export default {
  data() {
    return {
      menuList: [],
      userInfo: []
    }
  },
  created() {
    this.getMenuList()
    //console.log(this.menuList)
  },

  methods: {
    getMenuList() {
      let data = JSON.parse(localStorage.getItem("userInfo"))
      if (data !== null) {
        this.userInfo=data
        this.menuList = data.menuList
      }
    },
    logOut()
    {
     this.$message({
       message:"退出登录,跳转到登录页面",
       type:"success"
     })
      localStorage.clear();
      setTimeout(()=>{
        this.$router.push({path:"/login"})
      },1000)
    },
    editUserInfo(){
      this.$router.push({path:"/editUserInfo"})
    }


  }
};
</script>


<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
  overflow-x: hidden;
}

.el-dropdown-link {
  cursor: pointer;
  color: black;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.title {
  text-align: center;
  margin: 10px;
}

</style>