<template>
  <v-container fluid>    
      <body>
        <nav class="navbar">
          <div class="nav__logo">
            <v-icon large>mdi-atlassian</v-icon>
            <a href="">Jeon Shopping Mall</a>
          </div>

          <ul class="nav__leftmenu" v-if="clickValue.bwBarValue === true">
            <li><router-link :to="{ name:'HotItems' }"><v-icon color="#d49466">mdi-thumb-up</v-icon>인기 상품</router-link></li>
            <li><router-link :to="{ name:'LatestItems' }"><v-icon color="#d49466">mdi-view-list</v-icon>상품 리스트</router-link></li>
          </ul>

          <ul class="nav__rightmenu" v-if="clickValue.bwBarValue === true">
            <li><a @click="boardList()"><v-icon color="#d49466">mdi-account-group</v-icon>커뮤니티</a></li>
            <li><router-link :to="{ name:'Home'}"><v-icon color="#d49466">mdi-shopping-outline</v-icon>쇼핑몰</router-link></li>
            <li><router-link :to="{ name:'PutCart'}"><v-icon color="#d49466">mdi-cart</v-icon>장바구니</router-link></li>
            <li><router-link :to="{ name:'OrderList'}"><v-icon color="#d49466">mdi-tshirt-crew-outline</v-icon>주문내역</router-link></li>
          </ul>

          <ul class="boardlist" v-if="boardListValue === true">
            <li><router-link :to="{ name:'BoardList' }"><v-icon color="#d49466">mdi-account-group</v-icon>커뮤니티 바로가기</router-link></li>
            <li><router-link :to="{ name:'BoardWrite' }"><v-icon color="#d49466">mdi-pencil</v-icon>글 작성</router-link></li>
          </ul>
          
          <div class="bwBar">
            <v-btn @click="bwBarMenu()" depressed color="#263343"><v-icon color="#d49466">mdi-view-list</v-icon></v-btn>
          </div>  
        </nav>  
      </body>
  </v-container>
</template>

<style scoped>
  body {
    font-size: 16px;
  }

  .bwBar {
    display: none;
  }

  i {
    padding-right: 6px;
  }

  i.v-icon {
    color: #d49466;
  }

  a {
    text-decoration: none;
    color: #f0f4f5;
  }

  a:visited {
    color: #f0f4f5;
  }

  button.v-btn {
    display: flex;
    width: 100%;
    justify-content: flex-end;
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

  .boardlist li:hover {
    background: #d49466;
    border-radius: 4px;
  }

  .boardlist {
    display: flex;
    padding: 0;
    text-align: center;
  }

  .boardlist li {
    padding: 8px 12px;
    list-style: none;
  }

  @media (min-width: 975px) and (max-width: 1274px) {
    .navbar{
      font-size: 13px;
    }
  }

  @media screen and (max-width:975px) {
    .navbar {
      font-size: 13px;
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

    .boardlist {
      display: block;
      flex-direction: column;
      align-items: center;
      width: 100%;
      padding: 0;
    }

    .boardlist li {
      text-align: center;
      width: 100%;
    }

    .bwBar {
      display: block;
      position: absolute;
      right: 45px;
    }
  }


</style>

<script>
import { mapState } from 'vuex'

export default {
  data(){
      return{
        boardListValue: false,
        bwBarValue: false
      }
  },

  computed: {
      ...mapState(['isLogin', 'isLoginError','clickValue']),
  },

  methods:{
    boardList() {
      if(this.boardListValue === false) {
        this.boardListValue = !this.boardListValue
        console.log('바뀐 boardListValue는 ?' + this.boardListValue)
      } else {
        this.boardListValue = !this.boardListValue
        console.log('바뀐 boardListValue는 ?' + this.boardListValue)
      }
    },

    bwBarMenu() {
      console.log('bwBarMenu Run !')
      console.log('현재 bwBarValue는' + this.bwBarValue)
      if(this.bwBarValue === false) {
        this.bwBarValue = !this.bwBarValue
        this.$store.commit('SET_CLICK_BWBAR_VALUE', this.bwBarValue)
        console.log('바뀐 state의 bwBarValue?' + this.clickValue.bwBarValue)

      } else {
        this.bwBarValue = !this.bwBarValue
        this.$store.commit('SET_CLICK_BWBAR_VALUE', this.bwBarValue)
      }
    },

  }
}
</script>