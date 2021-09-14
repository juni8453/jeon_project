<template>
  <v-container fluid>
    <body>
      <BwBar></BwBar>

      <main>
        <v-row class="main">
          <v-col class="item__col" cols="3" v-for="item in productlist" :key="item.pId">
            <v-card class="item__card" outlined>
              <router-link @click.native="clickLink({item})" 
                :to="{name:'ItemDetail', params:{ pName:item.pName }}">
                <div class="item__img">
                  <v-img
                    v-if="item.pQuantity !== 0"
                    contain
                    :src="`/images/thumb/${item.listImages[0].iName}`"
                  ></v-img>
                  <v-img
                    v-else
                    contain
                    :src="`/images/thumb/${item.listImages[0].iName}`"
                  >
                  <!-- 이미지 내 다른 이미지 삽입 (매진되었을 때)-->
                    <v-img class="sold__out__img"
                      contain
                      :src="`/images/thumb/soldout.png`"
                    >
                    </v-img>
                  </v-img> 
                </div>
              </router-link>
              <v-card-text>
                <ul class="cardtext">
                  <strong><li>{{item.pName}}</li></strong>
                  <strong><li>{{item.pPrice | comma}}원</li></strong>
                  <li>
                    <v-btn outlined depressed  v-if="Userinfo.User_auth.includes('ROLE_ADMIN')"
                      @click="deleteProduct({
                        pId:item.pId,
                        iId:item.listImages[0].iId
                      })"
                    >
                      <v-icon color="#d49466">mdi-delete</v-icon>
                    </v-btn>
                  </li>
                </ul>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </main>

      <infinite-loading infinite-loading @infinite="infiniteHandler"></infinite-loading>

      <Footer></Footer>
    </body>
  </v-container>
</template>

<style scoped>
  .container {
    height: 100%;
  }

  div {
    border-radius: 8px;
  }

  div.col {
    padding: 20px 24px;
  }

  div.v-card.v-sheet {
    padding: 16px 20px;
  }

  div.v-card__text {
    padding: 8px 8px;
  }

  .cardtext {
    padding: 4px 4px;
    list-style: none;
  }

  .cardtext li {
    text-align: center;
  }

  .main__content li {
    list-style: none;
  }

  .item__card.v-card.v-sheet.v-sheet--outlined.theme--light {
    width: 100%;
    height: 100%;
    padding: 8px 8px;
  }

  .sold__out__img {
    position: relative;
    top: 33%;
    height: 45%;
  }

  @media screen and (max-width:975px) {
    div.row.main {
      width: 100%;
      margin: 0;
      align-items: flex-start;
      display: flex;
      flex-direction: column;
    }

    .item__col.col.col-3{
      max-width: 100%; /*25% 이상 반응 없음 > max-width로 변경*/
      padding: 14px;
    }

    .item__card.v-card.v-sheet.v-sheet--outlined.theme--light {
      width: 100%;
      height: 50%;
      padding: 8px 8px;
    }

    .sold__out__img {
      position: relative;
      top: 10rem;
      height: 30%;
    }
  }

</style>


<script>
import axios from 'axios'
import Route from '../router/index'
import { mapState } from 'vuex'
import InfiniteLoading from 'vue-infinite-loading'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
  data() {
    return {
      limit:0,
      pageOpt:8,
      checkorderlist:[]
    }
  },

  created(){
    console.log('created 시작')
    this.$store.dispatch('latestItems', {limit:this.limit, username:this.$store.state.Userinfo.User_Id}) 
  },

  computed:{
      ...mapState(['productlist','Userinfo','heartlist'])
  },

  components: {
    InfiniteLoading,
    BwBar,
    Footer
  },

  filters:{
    comma(val){
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },

  methods: { 
    clickLink(payload){
      console.log('link의 payload는?')
      console.log(payload.item.pQuantity)
      if(payload.item.pQuantity === 0){
        alert('해당 제품은 매진되었습니다.')
        Route.push('/latestitems')
        Route.go(Route.currentRoute)
        // push 이후 페이지 동작 안되서 새로고침 넣음
      }
    },

    likeProduct(payload){ // payload = {pId, username}
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('likeProduct Run')
      console.log(payload)
      if(confirm('제품을 추천하시겠습니까?')===true){
        axios.post(`http://${dev}:9000/api/auth/likeproduct/${payload.username}`, payload)
        .then(Response => {         
          console.log(Response.data)
          this.$store.commit('READ_PRODUCT_LIST', Response.data)
          console.log('likeProduct 메서드가 성공적으로 실행되었습니다.')
          /* 좋아요 현황을 즉각 반영하기 위해 succss를 return 받지 않고 insertHeart 이후의 리스트를 다시 SELECT 하여 
            최신 리스트를 다시 뽑아준다. */
        })
        .catch(Error => {
          console.log('error')
          console.log(Error)
        })
      }
    }, 

    cancelLike(payload){
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('cancleLike Run')
      console.log(payload)
      if(confirm('제품 추천을 취소하시겠습니까?')===true){
        axios.post(`http://${dev}:9000/api/auth/cancellike/${payload.username}` ,payload)
        .then(Response => {         
          console.log(Response.data)
          this.$store.commit('READ_PRODUCT_LIST', Response.data)
          console.log('cancelLike 메서드가 성공적으로 실행되었습니다.')
        })
        .catch(Error => {
          console.log('error')
          console.log(Error)
        })
      }
    }, 

    infiniteHandler($state){ 
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('limit+pageOpt?'+ this.limit + this.pageOpt)
      axios.get(`http://${dev}:9000/api/auth/latestitems/${this.limit + this.pageOpt}/${this.$store.state.Userinfo.User_Id}`)
      .then(Response => {
        console.log('infiniteHandler Response.data를 받았습니다.')
        console.log('Response.data:', JSON.stringify(Response.data))
        console.log('Response.data.list:', JSON.stringify(Response.data.list)) // 받아온 나머지 데이터
        console.log('Response.data.list.length:', JSON.stringify(Response.data.list.length)) // 나머지 데이터 길이

        setTimeout(() => {
          if(Response.data.list.length) {
            console.log('현재 productlist' + JSON.stringify(this.productlist))
            console.log('현재 productlist 길이' + JSON.stringify(this.productlist.length))
            console.log(Response.data.list)
            this.$store.commit('SET_PRODUCT_LIST', Response.data)
            $state.loaded()
            this.limit += 8

            if(this.productlist.length / this.pageOpt == 0) {  
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
    },

    deleteProduct(payload){
      let dev = 'localhost'
      let sev = '3.38.87.14'
      console.log('checkOrder의 payload는?')
      console.log(payload)

      if(confirm('정말로 글을 삭제하시겠습니까?')===true){
        new Promise((resolve, reject) => {
          axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
          axios.get(`http://${dev}:9000/api/admin/checkorder/${payload.pName}`)
          .then(Response => {
            console.log('checkOrder의 Response.data는 ?')
            console.log(Response.data)
            console.log('checkOrder 메서드가 성공적으로 실행되었습니다.')
            this.checkorderlist = Response.data.list
            console.log(this.checkorderlist.length)
            if(this.checkorderlist.length != 0){
              console.log('checkorderlist는 null이 아닙니다.')
              alert('해당 제품은 주문내역이 존재하기 때문에 삭제할 수 없습니다.')
            }
            else {
              console.log('삭제가 가능합니다.')
              console.log('deleteProduct Run')
              new Promise((resolve, reject) => {
                axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
                axios.post(`http://${sev}:9000/api/admin/deleteproduct`, payload)
                .then(Response => {
                  console.log(Response.data)
                  if(Response.data === "success"){
                    console.log('deleteProduct 메서드가 성공적으로 실행되었습니다.')
                    Route.go(Route.currentRoute)
                  }
                })
              })
            }
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
              alert("Error!")        
          })
        })
      }
    },

    clickLink(payload){
      console.log('link의 payload는?')
      console.log(payload.item.pQuantity)
      if(payload.item.pQuantity === 0){
        alert('해당 제품은 매진되었습니다.')
        Route.push('/latestitems')
        Route.go(Route.currentRoute)
        // push 이후 페이지 동작 안되서 새로고침 넣음
      }
    },
    
  }
}
</script>