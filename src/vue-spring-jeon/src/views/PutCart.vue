<template>
  <v-container fluid>
    <body>
      <BwBar></BwBar>
      <main>
        <v-row>
          <v-col cols="12">
            <v-card>
              <v-card-text class="main">
                  <ul class="table__head">
                    <v-row class="head__row">
                      <v-col cols="1">
                        <v-checkbox
                          v-model="checkedAll"
                          @click="selectAllProduct({item:cartlist})"
                        > 전체선택
                        </v-checkbox>
                      </v-col>
                      <v-col cols="3">
                        <strong>상품 사진</strong>
                      </v-col>  
                      <v-col cols="3">
                        <strong>상품명</strong>
                      </v-col>
                      <v-col cols="3">
                        <strong>판매가</strong>
                      </v-col>
                      <v-col cols="2">
                        <strong>비고</strong>
                      </v-col>
                    </v-row>
                  </ul>
                  <ul class="table__body">
                    <v-row class="body__row"
                      v-for="item in cartlist" :key="item.ctId">
                      <v-col cols="1">
                        <v-checkbox
                          v-model="item.check"
                          @click="selectOneProduct({item:item, listCart:item.listCart[0]})"
                        >
                        </v-checkbox>
                      </v-col>
                      <v-col cols="3">
                        <v-card outlined>
                          <router-link :to="{name:'ItemDetail',
                            params:{
                              pName:item.listCart[0].pName
                            }
                          }"
                          >
                          <v-img
                            contain
                            :src="`/images/thumb/${item.listCart[0].listImages[0].iName}`"                  
                          >
                          </v-img>
                        </router-link>  
                      </v-card>
                      </v-col>  
                      <v-col cols="3">
                        {{item.listCart[0].pName}}
                      </v-col>
                      <v-col cols="3">
                        {{item.listCart[0].pPrice}}
                      </v-col>
                      <v-col cols="2">
                        <v-btn outlined 
                          @click="deleteCart({
                          ctId:item.ctId,
                          })">
                          <v-icon color="#d49466">
                            mdi-delete
                          </v-icon>
                        </v-btn>
                      </v-col>
                    </v-row>
                  </ul>
              </v-card-text>
            </v-card>
            <infinite-loading @infinite="infiniteHandler"></infinite-loading>
            <v-card>
              <v-card-text>
                <v-row>
                  <v-col cols="3" class="text-center">
                    총 상품금액
                  </v-col>
                  <v-col cols="1" class="text-center">
                    +
                  </v-col>
                  <v-col cols="3" class="text-center">
                    기본 배송비
                  </v-col>
                  <v-col cols="1" class="text-center">
                    =
                  </v-col>
                  <v-col cols="4" class="text-center">
                    총 결제가격
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="3" class="text-center">
                    {{totalprice | comma}}원
                  </v-col>
                  <v-col cols="1" class="text-center">
                    +
                  </v-col>
                  <v-col cols="3" class="text-center">
                    {{post | comma}}원
                  </v-col>
                  <v-col cols="1" class="text-center">
                    = 
                  </v-col>
                  <v-col cols="4" class="text-center">
                    {{totalprice + post | comma}}원
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </main>
      <Footer></Footer>
    </body>
  </v-container>
</template>

<style scoped>
  .container {
    height: 100%  ;
  }

  div.col.col-3 {
    padding: 10px 25px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  div.col.col-2 {
    padding: 10px 25px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  div.col.col-1 {
    padding: 10px 25px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  div.col.col-12 {
    padding: 14px 25px;
  }

  div.main {
    padding: 16px 20px;
  }

  .head__row {
    text-align: center;
  }

  .body__row {
    text-align: center;
  }

  @media screen and (max-width:975px) {
    .head__row {
      display: flex;
    }

    .table__head {
      padding: 0;
    }

    .table__body {
      padding: 0;
    }

    .head__row {
      font-size: 13px;
      display: flex;
      justify-content: flex-start;
    }

    div.col.col-3 {
      padding: 0;
      padding-bottom: 10px;
    }

    div.col.col-2 {
      padding: 0;
      padding-bottom: 10px;
    }

    div.col.col-1 {
      padding: 0;
      padding-bottom: 10px;
    }
  }

</style>

<!--<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="12" md="12" sm="12">
        <BwBar></BwBar>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="12" sm="12">
        <v-card outlined>  
          <v-card-text>
            <v-row>
              <v-col cols="1" class="Center">
                <v-checkbox
                  v-model="checkedAll"
                  @click="selectAllProduct({item:cartlist})"
                > 전체선택
                </v-checkbox>
              </v-col>
              <v-col cols="2" class="Center">
                상품 사진
              </v-col>
              <v-col cols="3" class="Center">
                상품명
              </v-col>
              <v-col cols="2" class="Center">
                판매가
              </v-col>
              <v-col cols="2" class="Center">
                소계
              </v-col>
              <v-col cols="2" class="Center">
                비고 
              </v-col>
            </v-row>

            <v-row v-for="item in cartlist" :key="item.ctId"
            > 
              <v-col cols="1" class="Center">
                <v-checkbox
                  v-model="item.check"
                  @click="selectOneProduct({item:item, listCart:item.listCart[0]})"
                >
                </v-checkbox>
              </v-col>
              <v-col cols="2">
                <v-card outlined>
                  <router-link :to="{name:'ItemDetail',
                        params:{
                          pName:item.listCart[0].pName
                        }
                      }"
                    >
                    <v-img
                    contain
                    :src="`/images/thumb/${item.listCart[0].listImages[0].iName}`"                  
                    >
                    </v-img>
                  </router-link>  
                </v-card>
              </v-col>
              <v-col cols="3" md="3" sm="3" class="Center">
                {{item.listCart[0].pName}}
              </v-col>
              <v-col cols="2" md="2" sm="2" class="Center">
                {{item.listCart[0].pPrice | comma}}
              </v-col>
              <v-col cols="2" md="2" sm="2" class="Center">
                {{item.listCart[0].pPrice | comma}}
              </v-col>
              <v-col cols="2" md="2" sm="2" class="Center">
                <v-btn small
                
                >
                  개별 구매
                  <v-icon>
                    mdi-cash
                  </v-icon>
                </v-btn>
                <v-btn small
                  @click="deleteCart({
                    ctId:item.ctId,
                  })"
                >
                  장바구니 삭제
                  <v-icon>
                    mdi-delete
                  </v-icon>
                </v-btn>
              </v-col>
            </v-row>
            <infinite-loading @infinite="infiniteHandler"></infinite-loading>
          </v-card-text>  
        </v-card>
      <v-row>
      </v-row>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="12" sm="12">
        <v-card outlined>
        <v-card-text>
          <v-row>
            <v-col cols="3" class="text-center">
              총 상품금액
            </v-col>
            <v-col cols="1" class="text-center">
              +
            </v-col>
            <v-col cols="3" class="text-center">
              기본 배송비
            </v-col>
            <v-col cols="1" class="text-center">
              =
            </v-col>
            <v-col cols="4" class="text-center">
              총 결제가격
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="text-center">
              {{totalprice | comma}}원
            </v-col>
            <v-col cols="1" class="text-center">
              +
            </v-col>
            <v-col cols="3" class="text-center">
              {{post | comma}}원
            </v-col>
            <v-col cols="1" class="text-center">
              = 
            </v-col>
            <v-col cols="4" class="text-center">
              {{totalprice + post | comma}}원
            </v-col>
          </v-row>
        </v-card-text>    
        </v-card>
      </v-col>  
    </v-row>
    <v-row>
      <v-col cols="12" md="12" sm="12">
        <Footer></Footer>
      </v-col>
    </v-row>
  </v-container>      
</template> 

<style scoped>
  .Center { 
    display: grid;
    align-items: center;      /* 수직 중앙 정렬*/
    justify-content: center;  /* 수평 중앙 정렬*/
  }

  div.col {
    padding: 8px 16px;
  }

</style> 
-->

<script>
import axios from 'axios'
import { mapState } from 'vuex'
import InfiniteLoading from 'vue-infinite-loading'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
  data(){
    return{
      checkedAll:false,
      post:3000,
      limit:0,
      pageOpt:8,
      model:null
    }
  },

  created(){
    console.log('PutCartList Run!')
    console.log('this.Userinfo.User_Id:' + this.$store.state.Userinfo.User_Id)
    this.$store.dispatch('putCartList', {limit:this.limit})
  },

  computed:{
    ...mapState(['Userinfo', 'cartlist','Check', 'totalprice'])
  },

  components:{
    BwBar,
    Footer,
    InfiniteLoading,
  },

  filters:{
    comma(val){
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },

  methods:{
    deleteCart(payload){
      console.log('deleteCart Run')
      console.log(payload)
      this.$store.dispatch('deleteCart', payload)
    },

    selectAllProduct(payload){ // payload = {item:cartlist}
      console.log('payload.item은?')
      console.log(payload.item) 
      let sum = 0

      if(this.checkedAll === true){
        for(let i=0; i<payload.item.length; i++){
          payload.item[i].check = true
          console.log('payload.item[i].check는?'+ payload.item[i].check)
          console.log(payload.item[i].listCart[0].pPrice)
          sum += payload.item[i].listCart[0].pPrice
        }
      } else {
        for(let i=0; i<payload.item.length; i++){
          if(payload.item[i].check == true){
            payload.item[i].check = false
            console.log('payload.item[i].check는?'+ payload.item[i].check)
          }
        }
      }
      this.$store.commit('SET_TOTALPRICE',sum)
    },
  
    selectOneProduct(payload){ // payload = {item:item(cartlist), listCart:item.listCart[0]}
      console.log(payload)

      if(payload.item.check === true){ // 제품 가격 더하는 if
        console.log('check 확인 결과 true 입니다.')
        if(this.totalprice === 0){ // 아무 제품도 선택되어있지 않는 상태일 때,
          this.post = 3000
          this.$store.commit('SET_TOTALPRICE', payload.listCart.pPrice)
        }
        else { // 제품이 하나 이상 선택되어 값이 들어가 있을 때,
          this.$store.commit('SET_TOTALPRICE',this.totalprice + payload.listCart.pPrice) 
        }

      } 
      if(payload.item.check === false){ // 제품 가격 빼는 if
        console.log('check 확인 결과 false 입니다.')        
        this.$store.commit('SET_TOTALPRICE', this.totalprice - payload.listCart.pPrice)
      }
    },

    infiniteHandler($state){ 
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('limit+pageOpt?'+ this.limit + this.pageOpt)
      axios.get(`http://${dev}:9000/api/auth/putcartlist/${this.$store.state.Userinfo.User_Id}/${this.limit + this.pageOpt}`)
        .then(Response => {
          console.log('infiniteHandler Response.data를 받았습니다.')
          console.log('Response.data:', JSON.stringify(Response.data))
          console.log('Response.data.list:', JSON.stringify(Response.data.list)) // 받아온 나머지 데이터
          console.log('Response.data.list.length:', JSON.stringify(Response.data.list.length)) // 나머지 데이터 길이

          setTimeout(() => {
            if(Response.data.list.length) {
              console.log('setTimeout의 Response.data.list는?')
              console.log(Response.data.list)
              this.$store.commit('SET_CART_LIST', Response.data)
              $state.loaded()
              this.limit += 8

              if(this.cartlist.length / this.pageOpt == 0) {  
                $state.complete()
              }

            } else {
              $state.complete()
            }
          },1000)
        })   
        .catch(error => {
          console.log(error)
        })
      }
    }
  }
</script>