<template>
  <v-container fluid>
    <body>
      <BwBar></BwBar>
      <main>
        <v-row class="main">
          <v-col cols="12" class="main__content">

            <v-col cols="6">
              <div class="main__img" v-for="item in itemdetaillist.listImages" :key="item.iName">
                <v-img
                  contain
                  :src="`/images/thumb/${item.iName}`"
                ><v-btn class="img__leftbtn">Btn Test</v-btn>
                <v-btn class="img__rightbtn">Btn Test</v-btn></v-img>
              </div>  
            </v-col>
            <v-col cols="6">
              <div class="main__item">
                {{itemdetaillist.pName}}
                {{itemdetaillist.pPrice}}
                {{itemdetaillist.pBrand}}
              </div>
            </v-col>
          </v-col>
        </v-row>
      </main>
      <Footer></Footer>
    </body>
  </v-container>
</template>


<style scoped> /* style - 전역 style scoped 해당 스크립트만 */
  .container {
    height: 100%;
  }

  .main {
    width: 100%;
    padding: 8px 12px;
    margin: 0;
  }

  .main__img {
    display: flex;
    justify-content: space-between;
    border: 1px solid pink;
  }

  .main__content {
    display: flex;
    border: 1px solid black;
    border-radius: 8px;
    padding-left: 0px;
    padding-right: 0px;
  }

  .main__item {
    border: 1px solid orange;
  } 


</style>

<script>
import axios from 'axios'
import Route from '../router/index'
import { mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'


export default {
  props:['pId','pName'],

  data(){
    return{
      // item:{listImages:[]} // []를 초기화 시켜줘야 [0]을 못찾는다는 오류가 안뜸
      model:null
      
    }
  },

  created(){
    let dev = 'localhost'
    let sev = '3.38.87.14'

    console.log('ItemDetail Created Run!')
    console.log('받아온 props pName:'+this.pName)
    console.log('받아온 props pQuantity:'+this.pQuantity)
    new Promise((resolve, reject) => {
      console.log(this.$store.state.Userinfo.User_Id)
      axios.get(`http://${dev}:9000/api/auth/itemdetail/${this.pName}`)
      .then(Response => {
        console.log('ItemDetail created Response data')
        console.log(Response.data)
        // this.item = Response.data  
        this.$store.commit('SET_ITEMDETAIL_LIST', Response.data)
      })
      .catch(Error => {
          console.log(Error)
          alert('새로고침 이후에는 홈으로 이동합니다.')
          Route.push("/")
      })
    })
  },
  
  computed:{
    ...mapState(['productlist', 'itemdetaillist', 'Userinfo', 'heart'])
  },

  components:{
    BwBar,
    Footer
  },

  filters:{
    comma(val){
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },  

  methods:{
    putCart(payload){
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('putCart Run')
      console.log(payload)
      new Promise((resolve, reject) => {
        axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
        axios.post(`http://${dev}:9000/api/auth/putcart/${payload.username}`, payload)
        .then(Response => {
          console.log('putCart Response.data를 받았습니다.')
          console.log(Response.data)
          if(Response.data === 'success'){
            alert('제품을 장바구니에 담았습니다.')
          }
        })
        .catch(Error => {
          console.log(Error)
          alert('Error !')
        })
      })
    },
  }
}
</script>