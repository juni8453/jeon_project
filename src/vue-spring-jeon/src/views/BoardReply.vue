<template>
<v-container fluid> 
  <BwBar></BwBar>
    <v-layout>
        <v-flex xs12>
            <v-card class="elevation-12">
                <v-toolbar
                color="#FBC02D"
                dark
                flat
                >
                <v-toolbar-title>
                  답글 작성
                </v-toolbar-title>
                <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                <v-form>
                  <v-text-field
                  label="제목을 입력하세요"
                  name="bTitle"
                  prepend-icon="mdi-account"
                  v-model="bTitle"
                  type="text"
                  ></v-text-field>

                  <v-textarea
                    outlined
                    auto-grow
                    height="600"
                    label="내용을 입력하세요"
                    name="bContent"
                    v-model="bContent"
                  ></v-textarea>
                </v-form>
                </v-card-text>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="#FBC02D" 
                  @click="BoardReply({
                      bId:bId,
                      bTitle:bTitle, 
                      bContent:bContent, 
                      username:Userinfo.User_Id, 
                      bGroup:bGroup,     
                      bOrder:bOrder,
                      bDepth:bDepth               
                  })">답글 등록
                </v-btn>
                </v-card-actions>
            </v-card>
        </v-flex>
    </v-layout>
    <Footer></Footer>
</v-container>
</template>

<script>
import axios from 'axios'
import Route from '../router/index'
import {mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
props:['bId','bGroup', 'bOrder', 'bDepth'],

  data(){
    return {
      bTitle:null,
      bContent:null,
    }
  },

  computed:{
    ...mapState(['Userinfo'])
  },

  components:{
    BwBar,
    Footer
  },

  methods: {
    BoardReply(payload){ 
      let dev = 'localhost'
      let sev = '3.38.87.14'

      new Promise((resolve, reject) => {
        axios.post(`http://${dev}:9000/api/auth/boardreply`, payload)
        .then(Response => {
            console.log('답글 작성')            
            console.log(payload)
            console.log(Response.data)
            if(Response.data === "success") {
                Route.push("/boardlist")
            }
        })
        .catch(Error => {
            console.log(Error)
        })
      })
    }
  }
}
</script>