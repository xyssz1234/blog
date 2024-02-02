<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-input placeholder="请输入标题" v-model="searchForm.title" clearable style="width: 200px;margin-right: 10px"
                size="small" @input="searchTitle()"></el-input>
      <el-button type="primary" icon="el-icon-search" size="small" @click="searchTitle()">搜索</el-button>
    </div>
    <div>
      <el-button type="primary" size="small" @click="isDrawerDialog=true">新增</el-button>
      <el-button type="danger" size="small" @click="batchDelete()" :disabled="multipleSelection.length>1?false:true">批量删除</el-button>
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
          prop="title">
      </el-table-column>
      <el-table-column
          label="状态"
          width="120"
          prop="status">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="创建时间"
          width="220"
      >
      </el-table-column>
      <el-table-column
          prop="updateTime"
          label="更新时间"
          width="220"
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
    <CustomPages @size-change="handleSizeChange" @current-change="handleCurrentChange" :total="pageInfo.total"></CustomPages>

    <custom-drawer
     :form-data="formData"
     :rules-form="ruleForm"
     @close-drawer="closeDrawer"
     @submit-drawer="submitDrawer"
     :is-drawer-dialog="isDrawerDialog"
     :current-page="pageInfo.pageNum"
    >
      <div slot="content">
        <el-form-item label="栏目名称" prop="title">
          <el-input v-model="formData.title" placeholder="please input title"></el-input>
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
      multipleSelection: [],
      ids:[],
      formData:{
        id:null,
        title:""
      },
      ruleForm:{
        title: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ]
      },
      api:{
        pageUrl:"/category/page",
        saveUrl: "/category/save",
        deleteUrl:"/category/delBatch"
      },
      searchForm:{
        title:''
      }
    }
  },

  mixins:[create],
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
      this.ids=[];
      val.forEach(item=>{
            this.ids.push(item.id);
      }
      )
    },
    searchTitle(){
      this.pageInfo.pageNum=1
      this.getAll();
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
</style>