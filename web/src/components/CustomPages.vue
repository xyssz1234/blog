<template>
<div>
  <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5,10, 20, 50, 100]"
      :page-size="5"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
  </el-pagination>
</div>
</template>

<script>
export default {
  name: "CustomPages",
  props:{
    total:{
      type:[String,Number],
      default:0
    },
    currentPage:{
      type: [Number],
      default: 1
    }
  },

  methods:{
    handleSizeChange(e)
    {
      this.$emit("size-change",e)
    },

    handleCurrentChange(e)
    {
      this.$emit("current-change",e)
    }


  }
}
</script>

<style scoped>

</style>