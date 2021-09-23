<template>
  <v-container fluid>
    <body>
      <BwBar></BwBar>
      <main>
        <v-row>
          <v-col cols="12">
            <v-card>
              <v-card-text class="main">
                <ul class="main__right__content">
                  <li>{{pName}}</li>
                  <li>Delivery Infomation</li>
                  <v-text-field v-model="rName" counter="10" label="받는분 이름"></v-text-field>
                  <v-text-field v-model="oAddress" counter="25" label="배송지 주소"></v-text-field>
                  <v-text-field v-model="oDetailAddress" counter="25" label="배송지 상세주소"></v-text-field>
                  <li>Order Infomation</li>
                  <v-text-field v-model="oEmail" counter="25" label="이메일 주소"></v-text-field>
                  <li>{{Userinfo.User_Id}}</li>
                  <li>{{Userinfo.User_Phone}}</li>
                  <li>Payment Infomation</li>
                  <li>{{pPrice | comma}}원</li>
                  <v-text-field v-model="oName" counter="10" label="입금자 명"></v-text-field>
                  <li>기타 요구사항</li>
                  <li><textarea name="textarea" id="" cols="30" rows="10" v-model="oDemand"></textarea></li>
                </ul>
              </v-card-text>
              <v-card-text>
                <ul class="main__footer">
                  <li class="left__footer"><v-icon large color="#d49466" 
                    @click="buyProduct({
                      rName:rName,
                      oAddress:oAddress,
                      oDetailAddress:oDetailAddress,
                      oEmail:oEmail,
                      oDemand:oDemand,
                      oName:oName,
                      pName:pName,
                      pPrice:pPrice,
                      pQuantity:pQuantity,
                      username:Userinfo.User_Id
                    })">mdi-cash</v-icon>구매하기</li>
                  <li class="right__footer">
                    <router-link :to="{ name:'ItemDetail', params:{ pName:pName }}">
                      <v-icon color="#d49466">mdi-undo</v-icon>되돌아가기
                    </router-link></li>                
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

  div.col {
    padding: 10px 25px;
  }

  div.main {
    display: flex;
    justify-content: center;
  }

  .main__left__content {
    padding: 0;
  }

  .main__left__content li {
    list-style: none;
    padding-bottom: 20px;
  }

  .main__right__content {
    padding-left: 20px;
  }

  .main__right__content li {
    list-style: none;
    padding-bottom: 20px;
    text-align: center;
    border-radius: 4px;
  }

  .main__right__content li :hover {
    background: #d49466;
    border-radius: 4px;
  }

  .main__footer {
    display: flex;
    justify-content: space-around;
    padding: 0;
  }

  .main__footer li {
    list-style: none;
  }

  .right__footer {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  a {
    text-decoration: none;
    color: #00000099;
  }

  a:visited {
    color: #00000099;
  }

  input {
    background: #00000099;
    border-radius: 8px;
    color: #f0f4f5;
  }

  textarea {
    border-radius: 8px;
    background: #00000099;
    color: #f0f4f5;
  }

</style>

<script>
import axios from 'axios'
import Route from '../router/index'
import { mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
  props:['pName','pPrice','pQuantity'],

  data(){
    return{
      rName:null,
      oAddress:null,
      oDetailAddress:null,
      oEmail:null,
      oDemand:null,
      oName:null
    }
  },

  computed:{
    ...mapState(['Userinfo'])
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
    buyProduct(payload){
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('buyProduct Run!')
      console.log(payload)
      if(confirm('주문하겠습니까?')===true){
        new Promise((resolve, reject) => {
          axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
          axios.post(`http://${dev}:9000/api/auth/buyproduct`, payload)
          .then(Response => {
            console.log(Response.data)
            if(Response.data === 'success'){
              alert('주문이 완료되었습니다.')
              if(confirm('주문내역을 확인하시겠습니까?') === true){
                Route.push('/orderlist')
              }
            }       
          })
          .catch(Error => {
            console.log(Error)
            alert('Error !')
          })
        })      
      }
    }
  }
}
</script>