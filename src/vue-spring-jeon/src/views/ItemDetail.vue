<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="12" md="12" sm="12">
        <BwBar></BwBar>  
        <v-card class="text-center" outlined>
          <v-row>
            <v-col cols="12" md="12" sm="12">
            <v-sheet
                class="mx-auto"
                elevation="1"
                max-width="800"
              >
                <v-slide-group
                  v-model="model"
                  class="pa-4 Center"
                  active-class="success"
                  show-arrows
                >
                  <v-slide-item
                    v-for="item in itemdetaillist.listImages" :key="item.iName"
                    v-slot="{ active, toggle }"
                  >
                    <v-card
                      :color="active ? undefined : 'grey lighten-1'"
                      class="ma-4 Center card__img"
                      
                      @click="toggle"
                    >
                    <v-img
                      contain
                      :src="`/images/thumb/${item.iName}`"
                    >
                    </v-img>
                      <v-row
                        class="fill-height"
                        align="center"
                        justify="center"
                      >
                        <v-scale-transition>
                          <v-icon
                            v-if="active"
                            color="white"
                            size="48"
                            v-text="'mdi-close-circle-outline'"
                          ></v-icon>
                        </v-scale-transition>
                      </v-row>
                    </v-card>
                  </v-slide-item>
                </v-slide-group>
              </v-sheet>  
            </v-col>

            <v-col cols="12" md="12" sm="12">
              <v-card outlined>
                <v-card-text>
                  <ul class="clickbuy">
                    <li><strong>Product Introduce</strong></li>
                    <li>
                      <router-link
                        :to="{ name:'BuyProduct',
                          params:{
                            pName:itemdetaillist.pName,
                            pPrice:itemdetaillist.pPrice,
                            pQuantity: itemdetaillist.pQuantity
                          }
                        }"><v-icon color="#d49466">mdi-cash</v-icon>구매하기
                      </router-link>
                    </li>
                    <li>
                      <v-icon color="#d49466"
                        @click="PutCart({
                        pId: itemdetaillist.pId,
                        username: Userinfo.User_Id,
                      })">mdi-cart-arrow-down</v-icon>장바구니
                    </li>
                    <v-row>
                      <v-col cols="6">
                        <li>제품 이름</li>
                        <li>제품 가격</li>
                        <li>브랜드</li>
                        <li>원산지</li>
                        <li><v-icon color="red">mdi-heart</v-icon></li>
                      </v-col>
                      <v-col cols="6">
                        <li>{{itemdetaillist.pName}}</li>
                        <li>{{itemdetaillist.pPrice | comma}}원</li>
                        <li>{{itemdetaillist.pBrand}}</li>
                        <li>{{itemdetaillist.pFrom}}</li>
                        <li>{{itemdetaillist.pLike}}</li>
                      </v-col>
                    </v-row>
                  </ul>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
        <v-col>
          <Footer></Footer>
        </v-col>
      </v-row>
  </v-container>
</template>

<style scoped> /* style - 전역 style scoped 해당 스크립트만 */
  .Center { 
    display: grid;
    align-items: center;      /* 수직 중앙 정렬*/
    justify-content: center;  /* 수평 중앙 정렬*/
  }

  .clickbuy {
    list-style: none;
    padding: 0;
  }

  .clickbuy li {
    padding: 6px 10px;
  }

  a {
    text-decoration: none;
    color: #00000099;
  }

  a:visited {
    color: #00000099;
  }

  @media screen and (max-width:755px) {
    .card__img {
      height: 500px;
      width: 300px;
    }
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