<template>
  <v-container fluid>
    <body>
      <BwBar></BwBar>

      <main>
        <v-row class="main">
          <v-col class="item__col" cols="12" v-for="item in boardlist" :key="item.bId">
            <v-card class="item__card" outlined @click="optionList({item})">
              <v-card-text class="item__text">
                <ul class="item__no__title">
                  <li>#{{item.bId}}</li>
                  <li class="itme__title" v-if="item.bDepth > 0">
                    [Re]{{item.bTitle}}
                  </li>
                  <li v-else>
                    {{item.bTitle}}
                  </li>
                </ul>
                <ul class="item__name__date">
                  <li>{{item.username}}</li>
                  <li>{{item.bDateTime}}</li>
                </ul>
              </v-card-text>
            </v-card>
            <v-card class="click__list" v-if="item.bShow === true">
              <ul class="option">
                <li><v-btn 
                      router :to="{name:'BoardDetail', 
                      params: {bId:item.bId} }" 
                      depressed color="#f0f4f5">상세보기<v-icon color="#263343">mdi-file-eye</v-icon></v-btn>
                </li>
                <li><v-btn
                      v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                      router :to="{name:'BoardEdit',
                      params: {bId:item.bId}}"
                      depressed color="#f0f4f5">수정하기<v-icon color="#d49466">mdi-file-document-edit</v-icon></v-btn>
                </li>
                <li><v-btn
                      router :to="{name:'BoardReply',
                      params: {
                        bId:item.bId,
                        bGroup:item.bGroup,
                        bOrder:item.bOrder,
                        bDepth:item.bDepth}}"
                      depressed color="#f0f4f5">답글달기<v-icon color="#d49466">mdi-pencil</v-icon></v-btn>
                </li>
                <li><v-btn
                      v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                      @click="BoardDelete({bId:item.bId, page:page})"
                      depressed color="#f0f4f5">삭제하기<v-icon color="red">mdi-delete</v-icon></v-btn>
                </li>
              </ul>
            </v-card>
          </v-col>
        </v-row>
        <v-pagination class="pagination"
          v-model="page"
          :length="Pagination.lastPage" 
          circle
          @input="move({page:page})"
        ></v-pagination>
      </main>

      <Footer></Footer>
    </body>  
  </v-container>
</template>

<style scoped>
  .container {
    height: 100%;
  }
  main {
    width: 100%;
    padding: 8px 12px;
  }
  .item__card {
    background: #263343;
    border: 2px #d49466 solid;
  }
  
  .item__card li {
    color: #f0f4f5;
  }

  .item__card:hover {
    background: #d49466;
    border-radius: 4px;
  }
  .item__col {
    padding-bottom: 0px; 
  }
  .item__text {
    display: flex;
    width: 100%;
    justify-content: space-between;
    font-size: 14px;  
  }
  .item__no__title {
    justify-content: flex-start;
    list-style: none;
    padding-left: 4px;
  }
  .item__name__date {
    text-align: right;
    list-style: none;
    padding-left: 4px;
  }
  .option {
    display: flex;
    background: #f0f4f5;
    justify-content: space-around;
    padding: 8px 12px;
  }
  .option li{
    list-style: none;
  }
  .pagination {
    margin-top: 15px;
  }

  @media screen and (max-width: 450px) {
    .item__text {
      font-size: 12px;
      flex-direction: column;
    }

    .option {
      display: flex;
      flex-direction: column
    }

    a {
      width: 100%;
    }

    button {
      width: 100%;
    }
  }

</style>

<script>
import { mapState } from "vuex"
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'
export default {
  data(){
    return{
      search: '',
      page:1,
      // optionValue: false
      // pageUnit:5,
      // perPage:10,
    }
  },
    created() {
      this.$store.dispatch('BoardList', {page:this.page})
    },
    computed: {
      ...mapState(["boardlist", 'Pagination', 'Userinfo']),
    },
    components:{
      Footer,
      BwBar
    },
    methods: {
      BoardDelete(payload){
        if(confirm('정말로 글을 삭제하시겠습니까?')===true){
          this.$store.dispatch('BoardDelete', payload)
        }
      },
      optionList(payload){
        console.log('optionList Run!')
        console.log(payload)
        payload.item.bShow =! payload.item.bShow
        this.$store.commit('SET_OPTION_SHOW', payload.item.bShow)
      },
      move(payload){
        console.log('next')
        console.log(payload)
        this.$store.dispatch('BoardList', payload)
      },
    }
  }  
</script>