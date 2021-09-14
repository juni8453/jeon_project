<template>
    <v-container fluid>
        <v-row dense>
            <v-col cols="12" md="12" sm="12">
                <BwBar></BwBar>
                <v-card class="text-center pa-3" outlined color="white">
                    <v-row dense align="center" justify="space-around">
                        <v-col cols="12">
                            <v-file-input
                                v-if="isLogin === true && Userinfo.User_auth.includes('ROLE_ADMIN')"
                                id="uploadFile"
                                label="클릭해서 대표 이미지를 업로드하세요."
                                v-model="fileinput"
                                > 
                            </v-file-input>
                            <v-btn 
                                v-if="isLogin === true && Userinfo.User_auth.includes('ROLE_ADMIN')"
                                outlined
                                color="indigo"
                                @click="insertHomeImg(
                                    {
                                        fileinput:fileinput
                                    })"
                            > 이미지 추가 완료
                                <v-icon>
                                    mdi-pencil
                                </v-icon>
                            </v-btn>
                        </v-col>
                        <v-col>
                            <strong>NEW ARRIVAL</strong>
                        </v-col>
                    </v-row>
                </v-card>
                <v-card class="text-center pa-3" outlined color="white">
                    <v-row dense align="center" justify="space-around">   
                        <v-col cols="12" md="12" sm="12">                            
                            <v-card class="pd-3" outlined v-for="item in homeimglist" :key="item.hiId">
                                <v-row>
                                    <v-col cols="3" md="3" sm="3">
                                        <v-btn 
                                            v-if="isLogin === true && Userinfo.User_auth.includes('ROLE_ADMIN')"
                                            outlined 
                                            color="indigo"
                                            @click="deletehomeImg({item})"
                                        > 이미지 삭제
                                            <v-icon>
                                                mdi-delete
                                            </v-icon>
                                        </v-btn>
                                    </v-col>             
                                    <v-col cols="6" md="6" sm="6">
                                        <v-img
                                            height="350"
                                            contain
                                            :src="`/homeimages/thumb/${item.hiName}`"
                                        ></v-img>
                                    </v-col>
                                </v-row>    
                            </v-card>
                        </v-col>
                    </v-row>
                </v-card>  
            </v-col>
           <v-row>
               <v-col>
                   <Footer></Footer>
               </v-col>
           </v-row>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios'
import Route from '../router/index'
import {mapState} from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
    data(){
        return{      
            fileinput:null
        }
    },

    created(){
        console.log('쇼핑몰 홈페이지입니다.')
        this.$store.dispatch('homePage')
    },

    computed:{
        ...mapState(['Userinfo','homeimglist','isLogin'])
    },

    components:{
        BwBar,
        Footer
    },

    methods:{
      insertHomeImg(payload){
        let dev = 'localhost'
        let sev = '3.38.87.14'
        console.log('insertProduct Run')
        console.log(payload.fileinput.name)
        console.log(payload.fileinput.lastModified)
        new Promise((resolve, reject) => {
            axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
            let formData = new FormData();
            formData.append('uploadFile', payload.fileinput)
            formData.append('hiName',payload.fileinput.name)           // 이미지 이름
    
            axios.post(`http://${dev}:9000/api/admin/inserthomeimg`, formData,
            {
                headers: {
                'Content-Type': 'multipart/form-data',
                'Access-Control-Allow-Origin': '*'
            }
                })
                .then(Response => {
                        console.log('Resonse.data를 받았습니다.')
                        
                    if(Response.data === "success") {
                    Route.go(Route.currentRoute)
                    } 
                })
                .catch(Error => {
                    console.log('error')
                    reject(Error)
                    alert("Error!")
                    Route.push("/latestitem")
                })
            })
        }, // 이미지 등록

        deletehomeImg(payload){ // payload = {각 item 인덱스의 item.hiId, item.hiName}
            let dev = 'localhost'
            let sev = '3.38.87.14'
            console.log('FirstHomeImg Run')
            console.log('FirstHomeImg의 payload')
            console.log(payload) 
            console.log(payload.item)
            if(this.Userinfo.User_auth[0] === 'ROLE_ADMIN' && payload.hiId !== null){
                if(confirm('이미지를 삭제하시겠습니까?')===true){
                    new Promise((resolve, reject) => {
                    axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
                    axios.post(`http://${dev}:9000/api/admin/deletehomeimg`, payload.item)
                        .then(Response => {
                            console.log(Response.data)
                            Route.go(Route.currentRoute)
                        })
                        .catch(Error => {
                            console.log(Error)
                            alert('Error !')
                        })
                    })      
                }
            }
        },
    }
}
</script>