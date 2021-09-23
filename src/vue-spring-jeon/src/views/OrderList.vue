<!-- <template>
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
              <v-col cols="3" class="Center">
                상품 사진
              </v-col>
              <v-col cols="3" class="Center">
                상품명
              </v-col>
              <v-col cols="3" class="Center">
                주문일자
              </v-col>
              <v-col cols="3" class="Center">
                비고
              </v-col>
            </v-row>

              <v-col cols="3" v-for="img in item.listImages" :key="img.iId">
                <router-link :to="{name:'ItemDetail',
                        params:{
                          pName:item.pName
                        }
                      }"
                    >
                  <v-card outlined>
                    <v-img
                      contain
                      :src="`/images/thumb/${img.iName}`"                  
                      >
                      </v-img>
                  </v-card>
                </router-link>
              </v-col>
              <v-col cols="3" md="3" sm="3" class="Center">
                {{item.pName}}
              </v-col>
              <v-col cols="3" md="3" sm="3" class="Center">
                {{item.oDate}}
              </v-col>
              <v-col cols="3" md="3" sm="3" class="Center">
                {{item.oDemand}}
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
<!--<style>
  .Center { 
  display: grid;
  align-items: center;      /* 수직 중앙 정렬*/
  justify-content: center;  /* 수평 중앙 정렬*/
}
</style> -->

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
                  <v-row>
                    <v-col cols="3">
                      <strong>사진</strong>
                    </v-col>
                    <v-col cols="3">
                      <strong>상품명</strong>
                    </v-col>
                    <v-col cols="3" class="head__date">
                      <strong>주문일자</strong>
                    </v-col>
                    <v-col cols="3">
                      <strong>주문자</strong>
                    </v-col>
                  </v-row>
                </ul>
                <ul class="table__body" v-for="item in orderlist" :key="item.oNum">
                  <v-row v-for="img in item.listImages" :key="img.iId">
                    <v-col cols="3">
                      <v-card outlined>
                        <router-link :to="{name:'ItemDetail',
                          params:{
                            pName:item.pName
                          }}"
                        >
                          <v-img
                            contain
                            :src="`/images/thumb/${img.iName}`"                  
                          >
                          </v-img>
                        </router-link>  
                      </v-card>
                    </v-col>
                    <!-- <ul class="body__content">
                      <li> 
                        <v-col cols="3">
                          {{item.pName}}
                        </v-col>
                      </li>
                      <li>  
                        <v-col cols="3" class="body__date">
                          {{item.oDate}}
                        </v-col>
                      </li>
                      <li>  
                        <v-col cols="3">
                          {{item.username}}
                        </v-col>
                      </li>  
                    </ul> -->
                    <ul class="body__content">
                      <li>{{item.pName}}</li>
                      <li>{{item.oDate}}</li>
                      <li>{{item.username}}</li>
                    </ul>
                  </v-row>
                </ul>
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
    height: 100%;
  }

  div.main {
    padding: 16px 20px;
    display: flex;
    flex-direction: column;
  }

  .table__head {
    padding-left: 0;
    padding-bottom: 15px;
  }

  .table__body {
    padding-left: 0;
    padding-bottom: 15px;
  }

  .body__content {
    width:75%;
    height: 100%;
    list-style: none;
    display: flex;
    flex-direction: row;
    padding-top: 20px;
    padding-left: 0px;
  }

  .body__content li {
    width: 100%;
    text-align: center;
  }

  div.col.col-12 {
    padding: 14px 25px;
  }

  div.col.col-3 {
    padding: 10px 25px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  @media screen and (max-width:975px) {
    div.main {
      font-size: 12px;
    }

    .table__head {
      display: none;
    }


    div.col.col-3 {
      padding: 0;
      padding-bottom: 20px;
      margin: 0;
    }

    .body__content {
      width:75%;
      height: 100%;
      list-style: none;
      display: flex;
      flex-direction: column;
      padding-top: 20px;
    }

    .body__content li {
      width: 100%;
      display: flex;
      justify-content: center;
    }
  }

</style>

<script>
import { mapState } from 'vuex'
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
    console.log('OrderList Run!')
    this.$store.dispatch('orderList')
  },

  computed:{
    ...mapState(['Userinfo', 'cartlist','orderlist'])
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
    deleteCart(payload){
      console.log('deleteCart Run')
      console.log(payload)
      this.$store.dispatch('deleteCart', payload)
    }
  }
}
</script>