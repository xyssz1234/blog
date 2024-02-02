<template>
<div>
  <el-drawer
      :title="formData.id==null?'新增':'编辑'"
      :visible.sync="isDrawerDialog"
      :size="size"
      direction="rtl"
      :wrapperClosable="false"
      :before-close="cancelForm">

    <div  class="demo-drawer_content">
      <el-form :model="formData" :rules="rulesForm" ref="formData" label-width="100px" class="demo-ruleForm">
        <slot name="content"></slot>
      </el-form>
    </div>
    <div class="demo-drawer_footer">
      <el-button type="primary" @click="submitForm()">提交</el-button>
      <el-button @click="cancelForm()">取消</el-button>
    </div>

  </el-drawer>
</div>
</template>

<script>
export default {
  name: "CustomDrawer",
  props:{
    formData:{
      type:Object,
      default:function ()
      {
        return {}
      }
    },
    isDrawerDialog:{
      type:Boolean,
      default:false
    },
    rulesForm:{
      type:Object,
      default:function ()
      {
        return {}
      }
    },
    size:{
      type:[String,Number],
      default:'30%'
    }

  },
  methods:{
    submitForm()
    {
      this.$refs.formData.validate((valid)=>{
        if(valid){
          this.$emit("submit-drawer",this.formData)
        }else{
         this.$message.error('please input correct data')
          return false;
        }
      });

    },
    cancelForm(formName) {
     // this.isDrawerDialog=false;
      this.$emit('close-drawer',false)
     // this.$refs[formName].resetFields()
    },

  }
}
</script>

<style scoped>

</style>