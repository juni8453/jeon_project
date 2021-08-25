<template>
    <v-container fluid class="fixed-bar">
        <v-row dense>
            <v-col cols="12" md="12" sm="12">
              <v-card color="indigo" class="text-center">
                  <v-row dense align="center">                      
                      <v-col cols="6" md="6" sm="6">
                        <v-btn text color="white" exact
                          router :to="{name:'BoardList'}"
                          >
                          <v-icon>
                              mdi-account-group
                          </v-icon>
                            커뮤니티
                          </v-btn>
                      </v-col>
                      <v-col cols="6" md="6" sm="6">
                          <v-btn text color="white" exact
                            router :to="{name:'Home'}"
                          >
                          <v-icon>
                              mdi-shopping-outline
                          </v-icon>
                            쇼핑몰
                          </v-btn>
                      </v-col>
                      <v-col cols="6" md="6" sm="6">
                          <v-btn text color="white" exact
                            router :to="{name:'PutCart'}">
                            <v-icon>
                                mdi-cart
                            </v-icon>
                            장바구니
                          </v-btn>
                      </v-col>
                      <v-col cols="6" md="6" sm="6">
                          <v-btn text color="white" exact
                            router :to="{name:'OrderList'}">
                            <v-icon>
                                mdi-tshirt-crew-outline
                            </v-icon>
                            주문내역
                          </v-btn>
                      </v-col>  
                  </v-row>
              </v-card>

              <v-card class="text-center pa-3" outlined color="white">
                  <v-row dense align="center" justify="space-around">
                      <v-col cols="4" md="4" sm="4">
                          Jeon Shopping Mall
                      </v-col>
                      <v-col cols="4" md="4" sm="4">
                          <v-btn text exact
                          router :to="{name:'HotItems'}"
                          >
                            <v-icon>
                                mdi-thumb-up
                            </v-icon>
                            <strong>인기상품</strong>
                          </v-btn>
                      </v-col>
                      
                      <v-col cols="4" md="4" sm="4">
                          <v-btn text exact
                          router :to="{name:'LatestItems'}"
                          >
                            <v-icon>mdi-view-list</v-icon>                         
                              <strong>상품 리스트</strong>
                          </v-btn>
                      </v-col>
                          
                  </v-row>
              </v-card> 

          </v-col>
        </v-row>
    </v-container>
</template>

<script>
import { mapState } from 'vuex'

export default {
    data(){
        return{

        }
    },

    computed: {
        ...mapState(['isLogin', 'isLoginError']),
    },

    methods:{
        hotItems(){
            if(this.isLogin === false){
                alert('로그인이 필요한 서비스입니다.')
            }
        }
    }
}
</script>