<template>
  <v-container>
    <BwBar></BwBar>
    <v-row no-gutters>  
      <v-row>
        <v-col cols="12" md="6" sm="6"
          v-for="item in boardlist" :key="item.bId">
          <v-row>
            <v-col cols="12" md="12" sm="12"> 
              <v-card outlined>
                <v-row>
                  <v-col cols="8" md="8" sm="8">
                    <v-card-text
                      v-if="item.bDepth > 0"
                    >
                      <strong class="text-color">[Re]{{item.bTitle}}</strong>
                    </v-card-text>
                    <v-card-text class="Center"
                      v-else
                    >
                      <strong>{{item.bTitle}}</strong>
                    </v-card-text>
                  </v-col>

                  <v-col cols="2" md="2" sm="2" class="Center">
                    <v-icon>
                      mdi-eye
                    </v-icon>
                    <strong>{{item.bViews}}</strong>
                  </v-col>
                  <v-col cols="2" md="2" sm="2" class="Center">
                    <strong>[Group]<br>{{item.bGroup}}</strong>
                  </v-col>
                </v-row>
                <v-card-text>
                  <v-row>
                    <v-col cols="3" md="3" sm="3">
                      <v-btn outlined
                        icon
                        router :to="{name:'BoardDetail', 
                        params: {bId:item.bId} }"
                      >
                      <v-icon>mdi-file-eye</v-icon>
                      </v-btn>
                    </v-col>
                    <v-col cols="3" md="3" sm="3">
                      <v-btn outlined
                        icon
                        color="indigo"
                        router :to="{name:'BoardReply',
                        params: {
                          bId:item.bId,
                          bGroup:item.bGroup,
                          bOrder:item.bOrder,
                          bDepth:item.bDepth
                        }
                      }"
                      >
                      <v-icon>mdi-pencil</v-icon>
                      </v-btn>                       
                    </v-col>
                    <v-col cols="3" md="3" sm="3">
                      <v-btn outlined
                        icon
                        color="black"
                        v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                        @click="BoardEdit({bId:item.bId})"
                      >
                        <v-icon>mdi-file-document-edit</v-icon>
                      </v-btn>
                    </v-col>
                      <v-col cols="3" md="3" sm="3">
                      <v-btn outlined
                        icon
                        color="red"
                        v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                        @click="BoardDelete({bId:item.bId, page:page})"
                      >
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                    </v-col> 
                  </v-row>
                  <v-row>
                    <v-col cols="12" md="12" sm="12">
                      {{item.bDateTime}} | {{item.username}}
                    </v-col>
                  </v-row>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-row>
    <v-row>
      <v-col cols="12" md="12" sm="12">
        <v-pagination
          v-model="page"
          :length="Pagination.lastPage" 
          circle
          @input="move({page:page})"
        ></v-pagination>
      </v-col>
    </v-row>
  </v-container> 
</template>

<style scoped>

div {
  width:100%;
 /* height:100%;*/
  text-align: center;
}

.border {
  border-width: 3px 3px;
  border-color: indigo indigo
}

.text-color {
  color: yellowgreen
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
      pageUnit:5,
      perPage:5,
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

      move(payload){
        console.log('next')
        console.log(payload)
        this.$store.dispatch('BoardList', payload)
      },
    }
  }  
</script>