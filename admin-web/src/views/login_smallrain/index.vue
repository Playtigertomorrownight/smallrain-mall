<style>
  .el-dialog__header {
    padding: 30px 10px 30px;
    background-color: #101f46;
    color: #f6f6f3;
    letter-spacing: 5px;
  }
  .el-dialog__header label{
    color: #0c95f1;
    letter-spacing: 1px;
    font-size: 2rem;
    margin-left: 1rem;
  }
  .el-form--label-top .el-form-item__label {
    font-size: 15px;
    font-weight: 700;
    padding: 0px 0px 2px 0px;
  }
  .el-form-item {
    margin-bottom: 5px;
  }
  .el-dialog__wrapper {
    background: #c5c5c5 linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75));
  }
</style>

<template>
  <div>
    <el-dialog class="login-dialog" :visible="true" :center="true" :width="dialogWidth" :show-close="false" :top="top">
      <div slot="title" class="dialog-title" v-cloak>
        <h2> Hello, 你好 <br> <br>  欢迎登录到  <label>smallrain</label> </h2>
      </div>
      <el-form :model="form" status-icon :rules="rules" ref="loginForm" :label-position="labelPosition" :hide-required-asterisk="false" @keyup.enter.native="submitForm('loginForm')">
        <el-form-item label="用户名：" required prop="username">
          <el-input placeholder="请输入用户名"  v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码：" required prop="password">
          <el-input placeholder="请输入密码"  v-model="form.password" type="password"></el-input>
        </el-form-item>
        <!--<div style="text-align: right;margin: 30px 1px 6px 0px;">
          <el-checkbox v-model="form.rememberMe" label="2">记住登录状态</el-checkbox>
        </div>-->
      </el-form>
      <div slot="footer" class="dialog-footer" v-cloak>
        <el-button style="width:100%;" type="primary" :disabled="buttonStatus" :loading="buttonLoading"  @click="submitForm('loginForm')">{{buttonText}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {setSupport,getSupport,setCookie,getCookie} from '@/utils/support';
  import md5 from 'js-md5';

  export default {
    name: 'login2',
    data() {
      let usernamePattern = /^[a-zA-Z0-9_-]{4,16}$/;  //用户名正则，4到16位（字母，数字，下划线，减号）
      let passwordPattern = /^.*(?=.{4,18})(?=.*\d)(?=.*[A-Za-z]).*$/;  //密码强度正则，最少4位，包括至少1 个字母，1个数字，1个特殊字符
      let validateUsername = (rule, value, callback) => {
        if (value === '') {
          return callback(new Error('账号不能为空！'));
        }
        if(!usernamePattern.test(value)){
          return callback(new Error('账号格式不正确！4 到 16 位（字母，数字，_，-）'));
        }
        callback();
      };
      let validatePassword = (rule, value, callback) => {
        if (value === '') {
          return callback(new Error('密码不能为空！'));
        }
        if(!passwordPattern.test(value)){
          return callback(new Error('密码格式不正确！4 到 18 位，包括至少1 个字母，1个数字'));
        }
        callback();
      };
      return {
        top: "150px",
        visible: false,
        labelPosition: "top",
        dialogWidth: "410px",
        buttonLoading: false,
        buttonText: "登录",
        buttonStatus: false,
        form: {
          username: "",
          password: "",
        },
        rules: {
          username: [
            { validator: validateUsername, trigger: 'blur' }
          ],
          password: [
            { validator: validatePassword, trigger: 'blur' }
          ]
        }
      }
    },
    watch: {
      form: {
        handler (value) {
          if("登录"!=this.buttonText){
            console.log("change event");
            this.buttonText="登录";
            this.buttonStatus = false;
          }
        },
        deep: true
      },
    },
    created() {
      this.form.username = getCookie("username");
      this.form.password = getCookie("password");
      if(!this.form.username){
        this.form.username = 'admin';
      }
      if(this.form.password === undefined||this.form.password==null){
        this.form.password = '';
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          console.log(valid)
          if (valid) {   //表单验证成功，在此提交请求
            this.buttonLoading = true;
            this.buttonText="登录中，请稍后..";
            this.buttonStatus = true;
            //开始登录
            let loginForm = {
              username: this.form.username,
              password: md5(this.form.password)
            }
            this.$store.dispatch('Login', loginForm).then(() => {
              this.buttonLoading = false;
              this.buttonText="登录成功，正在跳转..";
              this.buttonStatus = false;
              setCookie("username",loginForm.username,15);
              setCookie("password",loginForm.password,15);
              this.$router.push({path: '/'})
            }).catch(() => {
              this.buttonText="登录";
              this.buttonStatus = false;
              this.buttonLoading = false;
            })
          } else {
            this.buttonText="数据格式不正确，请修改后登陆";
            this.buttonStatus = true;
            return false;
          }
        });
      }
    }
  }
</script>


