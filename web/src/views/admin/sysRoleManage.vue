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
          label="角色名"
          width="120"
          prop="title">
      </el-table-column>
      <el-table-column
          label="角色描述"
          prop="description">
      </el-table-column>
      <el-table-column
          label="角色标识"
          width="120"
          prop="role">
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
              type="primary"
              @click="settingMenu(scope.row.id)">菜单配置
          </el-button>
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
        <el-form-item label="角色名" prop="title">
          <el-input v-model="formData.title" placeholder="please input title"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="description">
          <el-input v-model="formData.description" placeholder="please input description"></el-input>
        </el-form-item>
        <el-form-item label="角色标识" prop="role">
          <el-input v-model="formData.role" placeholder="please input role"></el-input>
        </el-form-item>
      </div>
    </custom-drawer>

    <el-dialog title="角色菜单配置" :visible.sync="dialogTableVisible" width="20%">
      <el-table :data="menuData"
                ref="multipleTable"
                @selection-change="selectionTableChange"
                @before-close="cancelMenuDialog"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column property="name" label="菜单" width="200"></el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
         <el-button @click="cancelMenuDialog">取 消</el-button>
         <el-button type="primary" @click="submitMenu">确 定</el-button>
      </span>
    </el-dialog>

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
        title:"",
        role:"",
        description:""
      },
      ruleForm:{
        title: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ]
      },
      api:{
        pageUrl:"/sysRole/page",
        saveUrl: "/sysRole/save",
        deleteUrl:"/sysRole/delBatch"
      },
      searchForm:{
        title:''
      },
      dialogTableVisible: false,
      menuData: [],
      id:0,
      currentMenuList:[],
      currentTable:[]
    }
  },

  mixins:[create],
  created() {
    this.getMenuList();
  },
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

    getMenuList(){
      this.$axios.get("/menu/all").then((res)=>{
        this.menuData=res.data
      })
    },

    settingMenu(id)
    {
      this.dialogTableVisible=true;
      this.id=id;
      this.$axios.get("/sysRole/getById?id="+this.id).then((res)=>{
        this.currentMenuList=res.data
        let data=this.menuData.filter(obj=>{
          let menuId=this.currentMenuList.find(menuId=>obj.id===menuId.id)
          return menuId!==undefined
        })
        data.forEach(row=>{
          this.$refs.multipleTable.toggleRowSelection(row);
        })


  })
    },
    selectionTableChange(e)
    {
      this.currentTable=e;
    },
    submitMenu()
    {
     let ids=[]
     this.currentTable.forEach(item=>{
       ids.push(item.id);
     })
      this.currentTable=[];
      let data={
        roleId:this.id,
        menuIdList:ids
      }
      this.$axios.post("/sysRole/saveRoleMenu",data).then((res)=>{
          if(res.code===200)
          {
            this.$message({
              message:res.msg,
              type:"success"
            })
          }
      })
     this.cancelMenuDialog();
    },
    cancelMenuDialog()
    {
      this.dialogTableVisible=false;
      this.$refs.multipleTable.clearSelection();
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