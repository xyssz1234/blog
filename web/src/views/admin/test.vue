<template>
<div>
  <div style="margin-bottom: 20px">
    <el-input placeholder="请输入用户名" v-model="searchName" clearable style="width: 200px;margin-right: 10px" size="small" ></el-input>
    <el-input placeholder="请输入邮箱" v-model="searchEmail" clearable style="width: 200px;margin-right: 10px" size="small"></el-input>
    <el-button type="primary" icon="el-icon-search" size="small">搜索</el-button>
  </div>
  <test-component></test-component>
  <div>
    <el-button type="primary" size="small">新增</el-button>
    <el-button type="danger" size="small">批量删除</el-button>
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
        label="日期"
        width="120">
      <template slot-scope="scope">{{ scope.row.date }}</template>
    </el-table-column>
    <el-table-column
        prop="name"
        label="姓名"
        width="120">
    </el-table-column>
    <el-table-column
        prop="address"
        label="地址"
        show-overflow-tooltip>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑
        </el-button>
        <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      background
      layout="prev, pager, next"
      :total="1000">
  </el-pagination>
</div>
</template>

<script>
export default {
  data() {
    return {
      searchName: '',
      searchEmail:'',
      tableData: [{
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-08',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-06',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-07',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
      multipleSelection: []
    }
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
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
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