<template>
  <v-container fluid>
    <v-row>
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
                제품 추천수
              </v-col>
              <v-col cols="3" class="Center">
                남은 수량 (재고)
              </v-col>
            </v-row>
            <v-row v-for="item in ranklist" :key="item.pId">
              <v-col cols="3" class="Center">
                <router-link :to="{name:'ItemDetail',
                  params:{
                    pId:item.pId,
                    pName:item.pName
                  }
                }"
                >
                <v-img
                  contain
                  height="250"
                  :src="`/images/thumb/${item.listImages[0].iName}`"
              ></v-img>
                </router-link> 
              </v-col>             
              <v-col cols="3" class="Center">
                {{item.pName}}
                
              </v-col>
              <v-col cols="3" class="Center">
                {{item.pLike}}
              </v-col>
              <v-col cols="3" class="Center">
                {{item.pQuantity}}
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

<script>
import { mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
  data(){
    return{
    
    }
  },

  created(){
    console.log('ProductRank Created Run!')
    this.$store.dispatch('productRank')
  },

  computed:{
    ...mapState(['ranklist'])
  },

  components:{
    BwBar,
    Footer
  }
}
</script>  
