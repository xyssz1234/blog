<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-input placeholder="请输入标题" v-model="searchForm.title" clearable style="width: 200px;margin-right: 10px"
                size="small" @input="searchTitle()"></el-input>
      <el-button type="primary" icon="el-icon-search" size="small" @click="searchTitle()">搜索</el-button>
    </div>
    <div>
      <el-button type="primary" size="small" @click="Add">新增</el-button>
      <el-button type="danger" size="small" @click="batchDelete()" :disabled="multipleSelection.length>1?false:true">
        批量删除
      </el-button>
    </div>


    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          label="ID"
          width="120"
          prop="id">
      </el-table-column>
      <el-table-column
          label="标题"
          prop="title"
          width="120"
      >
      </el-table-column>
      <el-table-column
          label="文章内容"
          prop="content"
          :show-overflow-tooltip="true"
      >
      </el-table-column>
      <el-table-column
          label="封面图"
          width="200"
          prop="picture">
        <template slot-scope="scope" v-if="scope.row.picture">
          <img :src="scope.row.picture" alt="" width="100" height="100">
        </template>
      </el-table-column>
      <el-table-column
          label="文章状态"
          width="200"
          prop="status">
      </el-table-column>
      <el-table-column
          label="浏览量"
          width="120"
          prop="views">
      </el-table-column>
      <el-table-column
          label="所属栏目"
          width="120"
          prop="categoryName">
      </el-table-column>
      <el-table-column
          label="发布用户"
          width="120"
          prop="username">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="创建时间"
          width="200"
      >
      </el-table-column>
      <el-table-column
          prop="updateTime"
          label="更新时间"
          width="200"
      >
      </el-table-column>
      <el-table-column label="操作" fixed="right">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
              size="mini"
              type="danger"
              :disabled="ids.length>1?true:false"
              @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <CustomPages @size-change="handleSizeChange" @current-change="handleCurrentChange"
                 :total="pageInfo.total"></CustomPages>

    <custom-drawer
        :form-data="formData"
        :rules-form="ruleForm"
        @close-drawer="closeDrawer"
        @submit-drawer="submitDrawer"
        :is-drawer-dialog="isDrawerDialog"
        :current-page="pageInfo.pageNum"
        :size="size"
    >
      <div slot="content">
        <el-form-item label="标题:" prop="title">
          <el-input v-model="formData.title" placeholder="please input title"></el-input>
        </el-form-item>
        <el-form-item label="文章内容:">
<!--          <el-input v-model="formData.content" placeholder="please input content"></el-input>-->
          <CustomerEditor :content="formData.content" @content-change="contentChange"></CustomerEditor>
        </el-form-item>
        <el-form-item label="封面图:">
          <!--          <el-input v-model="formData.picture" placeholder="please input picture"></el-input>-->
          <el-upload
              class="avatar-uploader"
              :action="server"
              :show-file-list="false"
              :headers="headers"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
            <img v-if="formData.picture" :src="formData.picture" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="所属栏目:">

          <el-select v-model="formData.categoryId" clearable placeholder="请选择">
            <el-option
                v-for="item in categoryList"
                :key="item.id"
                :label="item.title"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="文章状态:">

          <el-select v-model="formData.status" placeholder="请选择">
            <el-option
                v-for="item in statusList"
                :key="item.value"
                :label="item.value"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="浏览量:">
          <el-input v-model="formData.views" placeholder="please input views"></el-input>
        </el-form-item>
      </div>
    </custom-drawer>


  </div>
</template>

<script>

import create from "@/mixins/create";

export default {
  data() {
    return {
      searchName: '',
      searchEmail: '',
      ruleForm: {
        title: [
          {required: true, message: '请输入名称', trigger: 'blur'},
          {min: 2, max: 50, message: '长度在 2 到 5 个字符', trigger: 'blur'}
        ]
      },
      drawer: false,
      direction: 'rtl',
      multipleSelection: [],
      ids: [],
      formData: {
        id: null,
        title: "",
        content: '',
        picture: '',
        views: '',
        userId:''
      },
      searchForm: {
        title: ''
      },
      api: {
        pageUrl: "/article/page",
        saveUrl: "/article/save",
        deleteUrl: "/article/delBatch"
      },
      categoryList: [],
      imageUrl: '',
      server: process.env.VUE_APP_SERVER + "/file/uploads",
      headers: {
        "token": localStorage.getItem("token")
      },
      size:'90%'
    }
  },
  created() {
    this.getCategoryList();
    let a=JSON.parse(localStorage.getItem("userInfo"))
    console.log(a)
    console.log(a.id)
  },
  mixins: [create],
  methods: {
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {

      this.multipleSelection = val;
      this.ids = [];
      val.forEach(item => {
            this.ids.push(item.id);
          }
      )
    },
    searchTitle() {
      this.pageInfo.pageNum = 1
      this.getAll();
    },
    getCategoryList() {
      this.$axios.get("/category/all").then((res) => {
        this.categoryList = res.data
      })
    },
    Add(){
      this.isDrawerDialog=true
      this.formData={
        status:"启用"
      }

    },
    handleAvatarSuccess(res, file) {
      this.formData.picture = res.data.url
      // console.log(res.data.url)
      // console.log(file)
      // console.log(this.formData)
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const typeArr = ["image/png", "image/jpeg","image/jpg"]
      if (!typeArr.includes(file.type)){
        this.$message.error("上传头像图片只支持jpg，png，jpeg格式")
        return false
      }

      const isLt2M=file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return  isLt2M
    },
    contentChange(e) {
      console.log(e)
      this.formData.content=e
    },
    closeDrawer(e) {
      this.isDrawerDialog = e
      //this.formData={}
      this.$router.go(0)
    },
    submitDrawer(e) {
      console.log("获取提交的数据", e)
      this.formData = Object.assign({},e)
      let userId=JSON.parse(localStorage.getItem("userInfo"))
      this.formData.userId=userId.id
      this.$axios.post("/article/save", this.formData).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.$router.go(0)
          // this.isDrawerDialog = false
          // this.$message({
          //   message: res.msg,
          //   type: 'success'
          // });
          // this.getList()
        }
      })

    },

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

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 30px;
  height: 30px;
  line-height: 40px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

</style>