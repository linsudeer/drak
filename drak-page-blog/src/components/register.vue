<style lang="stylus">
    .switch-state-to-login
        display:block
        margin-top:10px
        text-align: center
</style>
<template>
        <div class="register">
            <!-- <div class="item">
                <label for="mobile">手机号：</label>
                <input type="text" id="mobile" 
                        class="form-control input" 
                        name="mobile"
                        placeholder="请输入手机号"
                        v-model="mobile"
                        v-validate:mobile="'required|mobile'">
            </div> -->
            <div class="item">
                <label for="name">用户名</label>
                <input type="text" id="name" 
                            class="form-control input" 
                            name="name"
                            placeholder="请输入用户名"
                            v-model="name">
            </div>
            <div class="item">
                <label for="email">邮箱</label>
                <input type="text" id="email" 
                            class="form-control input" 
                            name="email"
                            placeholder="请输入邮箱"
                            v-model="email">
            </div>
            <div class="item">
                <label for="password">密码：</label>
                <input type="password" id="password" 
                            class="form-control input" 
                            name="password"
                            placeholder="请输入密码"
                            v-model="password">
            </div>
            <div class="item">
                <label for="repassword"> 确认密码:</label>
                <input type="password" id="repassword" 
                            class="form-control input" 
                            name="repassword"
                            placeholder="请确认密码"
                            v-model="repassword">
            </div>
            <button class="btn" @click="submit()" type="button">注册</button>
            <span @click="switchStateToLogin()" class="switch-state switch-state-to-login">已有账号登录</span>
    </div>
</template>

<script>
import UserApi from '@/api/user'
export default {
    name:'register',
    components: {
       
    },
    data() {
        return {
            password:"",
            email:"",
            repassword:"",
            name:"",
            mobile:""
        }
    },
    computed: {
       
    },
    mounted() {
    },
    methods: {
        switchStateToLogin:function(){
            this.$emit("switchStateToLogin")
        },
        submit:function(){
            let parmas={
                    userNickname : this.name,
                    userPassword :this.password,
                    userPassword2:this.repassword,
                    userEmail: this.email

            }
            UserApi.register(parmas).then((response) => { 
                this.$message("注册成功，现在去登陆");
                this.$emit("switchStateToLogin")
            });
            // this.$allValidate().then().catch(err =>{
            
            // })
        }
    }
}

</script>
