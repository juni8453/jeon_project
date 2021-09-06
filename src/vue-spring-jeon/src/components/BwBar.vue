<template>
    <v-container fluid>    
        <body>
          <nav class="navbar">
            <div class="nav__logo">
              <v-icon color="white">mdi-delete</v-icon>
              <a href="">Jeon Shopping Mall</a>
            </div>

            <ul class="nav__leftmenu">
              <li><router-link :to="{ name:'HotItems' }"><v-icon color="#d49466">mdi-thumb-up</v-icon>인기 상품</router-link></li>
              <li><router-link :to="{ name:'LatestItems' }"><v-icon color="#d49466">mdi-view-list</v-icon>상품 리스트</router-link></li>
            </ul>

            <ul class="nav__rightmenu">
              <li><router-link :to="{ name:'BoardList' }"><v-icon color="#d49466">mdi-account-group</v-icon>커뮤니티</router-link></li>
              <li><router-link :to="{ name:'Home'}"><v-icon color="#d49466">mdi-shopping-outline</v-icon>쇼핑몰</router-link></li>
              <li><router-link :to="{ name:'PutCart'}"><v-icon color="#d49466">mdi-cart</v-icon>장바구니</router-link></li>
              <li><router-link :to="{ name:'OrderList'}"><v-icon color="#d49466">mdi-tshirt-crew-outline</v-icon>주문내역</router-link></li>
            </ul>
        </nav>  
        </body>
    </v-container>
</template>

<style scoped>
  i {
    padding-right: 6px;
  }

  a {
    text-decoration: none;
    color: #f0f4f5;
  }

  a:visited {
    color: #f0f4f5;
  }

  .navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background: #263343;
    padding: 16px 20px;
    border-radius: 8px;
  }

  .nav__logo {
    padding: 8px 12px;
  }

  .nav__leftmenu {
    display: flex;  
    padding: 0;
    text-align: center;
  }

  .nav__leftmenu li:hover {
    background: #d49466;
    border-radius: 4px;
  }

  .nav__leftmenu li {
    padding: 8px 12px;
    list-style: none;
  }

  .nav__rightmenu {
    display: flex;
    padding: 0;
    text-align: center;
  }

  .nav__rightmenu li:hover {
    background: #d49466;
    border-radius: 4px;
  }

  .nav__rightmenu li {
    padding: 8px 12px;
    list-style: none;
  }

  @media screen and (max-width:975px) {
    .navbar {
      flex-direction: column;
      align-items: flex-start;
    }  

    .nav__leftmenu {
      flex-direction: column;
      align-items: center;
      width: 100%;
    }

    .nav__leftmenu li {
      text-align: center;
      width: 100%;
    }

    .nav__rightmenu {
      flex-direction: column;
      align-items: center;
      width: 100%;
    }

    .nav__rightmenu li{
      text-align:center;
      width: 100%;
    }
  }


</style>

<script>
import { mapState } from 'vuex'

export default {
    data(){
        return{

        }
    },

    computed: {
        ...mapState(['isLogin', 'isLoginError']),
    },

    methods:{
        hotItems(){
            if(this.isLogin === false){
                alert('로그인이 필요한 서비스입니다.')
            }
        }
    }
}
</script>