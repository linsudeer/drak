<style lang="stylus">
.prompt-box {
  margin-top: 10px;
}

.forget-pwd {
  float: right;
}
</style>
<template>
  <div class="login">
    <div class="item">
      <label for="name">用户名：</label>
      <input
        type="text"
        id="name"
        class="form-control input"
        name="name"
        placeholder="请输入用户名"
        v-model="name"
      />
    </div>
    <div class="item">
      <label for="password">密码：</label>
      <input
        type="password"
        id="password"
        class="form-control input"
        name="password"
        placeholder="请输入密码"
        v-model="password"
      />
    </div>
    <button class="btn" @click="login()">登录</button>
    <div class="prompt-box">
      <span>没有账号？</span>
      <span @click="switchStateToReg()" class="switch-state">注册</span>
      <span class="switch-state forget-pwd">忘记密码</span>
    </div>
  </div>
</template>

<script>
import LoginApi from "@/api/user";
import Store from "store";
import Global from "@/global";
export default {
  name: "login",
  components: {},
  data() {
    return {
      name: "",
      password: ""
    };
  },
  computed: {},
  ready: function() {},
  methods: {
    switchStateToReg: function() {
      this.$emit("switchStateToReg");
    },
    login: function() {
      LoginApi.login(this.name, this.password)
        .then(response => {
          let user = response.data.user;
          if (user) {
            Store.set(Global.ISLOGIN, true);
            Store.set(Global.USER, user);
            this.$emit("changeLoginMask", user);
          } else {
            this.$message("没有找到登录用户！");
          }
        })
        .catch(error => {
          this.$message(error);
        });
    }
  }
};
</script>
