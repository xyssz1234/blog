<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-input placeholder="请输入标题" v-model="searchForm.username" clearable style="width: 200px;margin-right: 10px"
                size="small" @input="searchTitle()"></el-input>
      <el-button type="primary" icon="el-icon-search" size="small" @click="searchTitle()">搜索</el-button>
    </div>
    <div>
      <el-button type="primary" size="small" @click="isDrawerDialog=true">新增</el-button>
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
          sortable
          prop="id">
      </el-table-column>
      <el-table-column
          label="用户名"
          width="220"
          prop="username">
      </el-table-column>
      <el-table-column
          label="用户类型"
          width="220"
          prop="roleTypeText">
      </el-table-column>
      <el-table-column
          label="座右铭"
          prop="motto">
      </el-table-column>
      <el-table-column
          prop="email"
          label="邮箱"
          width="220"
      >
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址"
          width="220"
      >
      </el-table-column>
      <el-table-column
          prop="mobile"
          label="手机"
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
    <CustomPages @size-change="handleSizeChange" @current-change="handleCurrentChange"
                 :total="pageInfo.total"></CustomPages>

    <custom-drawer
        :form-data="formData"
        :rules-form="ruleForm"
        @close-drawer="closeDrawer"
        @submit-drawer="submitDrawer"
        :is-drawer-dialog="isDrawerDialog"
        :current-page="pageInfo.pageNum"
        :before-close="closeRule"
    >
      <div slot="content">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="formData.username" placeholder="please input title"></el-input>
        </el-form-item>
        <el-form-item label="座右铭" prop="motto">
          <el-input v-model="formData.motto" placeholder="please input motto"></el-input>
        </el-form-item>
<!--        <el-form-item label="密码" prop="password">-->
<!--          <el-input v-model="formData.password" placeholder="please input password"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData.email" placeholder="please input email"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="formData.address" placeholder="please input address"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="mobile">
          <el-input v-model="formData.mobile" placeholder="please input mobile"></el-input>
        </el-form-item>
        <el-form-item label="用户类型" prop="roleType">
          <el-select v-model="formData.roleType" filterable placeholder="请选择">
            <el-option
                v-for="item in roleList"
                :key="item.role"
                :label="item.title"
                :value="item.role">
            </el-option>
          </el-select>
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
      ids: [],
      formData: {
        id: null,
        username: "",
        motto: "",
        email: "",
        mobile: "",
        address: ""
      },
      ruleForm: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur'}
        ]
      },
      api: {
        pageUrl: "/user/page",
        saveUrl: "/user/save",
        deleteUrl: "/user/delBatch"
      },
      searchForm: {
        username: ''
      },
      roleList:[{
        role:'',
        title:''
      }]
    }
  },
  created() {
    this.getRoleList();
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

    getRoleList() {
      this.$axios.get("sysRole/all").then((res) => {
        console.log(res.data)
        this.roleList=res.data
      })
    },

    closeRule()
    {
      this.$refs[this.formData].resetFields();
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