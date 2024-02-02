<template>
  <div class="box">
    <h1 style="text-align: center;margin: 15px auto">博客系统</h1>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password"></el-input>
      </el-form-item>

      <el-form-item display:flex>
        <div style="width: 70px;">
          <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        </div>
        <div style="width: 70px">
          <router-link tag="div" :to="{path:'register'}">
            <el-button >注册</el-button>
          </router-link>
        </div>



      </el-form-item>
    </el-form>
  </div>

</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'change' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'change' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'change' }
        ]

      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //alert('submit!');
          this.$axios.post("/user/login",this.ruleForm).then(res=>{
            console.log(res)
            if(res.code===200)
            {
              this.$message({
                message:"login success",
                type:"success"
              })
              let userInfo=res.data
              localStorage.setItem("userInfo",JSON.stringify(userInfo))
              localStorage.setItem("token",userInfo.token)

              setTimeout(()=>{
                this.$router.push({path:"/"})
              },1000)

            }
          })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.box{
  width: 450px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>