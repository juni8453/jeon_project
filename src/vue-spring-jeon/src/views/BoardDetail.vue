<template>
    <v-container fluid>
        <BwBar></BwBar>
        <v-row dense>
            <v-col>
                <v-card outlined>
                    <v-card-text>
                        <strong class="Center">{{board.bTitle}}</strong>
                        <div class="text-right">{{board.username}} || {{board.bDateTime}}</div>
                        <v-spacer></v-spacer>
                        <div class="text-right">조회 {{board.bViews}}</div>
                    </v-card-text>                    
                </v-card>
            </v-col>
        </v-row>
        <v-row dense> 
            <v-col cols="12" md="12" sm="12">
                <v-card class="text-center pa-3" outlined tile style="height: 600px;" color="Whtie">
                    {{board.bContent}}
                </v-card>
            </v-col>
        </v-row>        
        <v-row dense>
            <v-col cols="12" md="12" sm="12">
                <v-card class="pa-3" outlined tile color="White">
                    <v-card>
                        <v-card-text class="text-left border: 1px solid primary">
                            <strong>전체 댓글 {{commentlist.length}} 개</strong>
                        </v-card-text>        
                    </v-card>
                    <v-row>
                        <v-col cols="12" md="12" sm="12">
                            <v-card outlined>
                                <v-row v-for="item in commentlist" :key="item.cId">
                                    <v-col cols="3" class="text-center">
                                        <v-card-text class="font-size">
                                            {{item.username}}
                                        </v-card-text>
                                        <v-row>
                                            <v-col>
                                                <v-card-text class="font-size">
                                                    <strong>Group[{{item.cGroup}}]</strong>
                                                </v-card-text>
                                            </v-col>
                                        </v-row>
                                    </v-col>
                                    <v-col cols="5" class="text-center">
                                        <v-card-text class="font-size"
                                            v-if="item.cDepth > 0">
                                            [답글]{{item.cContent}}
                                        </v-card-text>
                                        <v-card-text class="font-size"
                                            v-else>
                                            {{item.cContent}}
                                        </v-card-text>
                                    </v-col>
                                    <v-col cols="4" class="text-right">
                                        <v-card-text class="font-size-date">
                                            {{item.cDateTime}}
                                        </v-card-text>
                                        <v-row>
                                            <v-col cols="4" class="text-center pl-0">
                                                <v-icon
                                                    v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                                                    @click="Show(item)"
                                                    color="amber lighten-1"
                                                >
                                                mdi-pencil
                                                </v-icon>
                                            </v-col>
                                            <v-col cols="4" class="text-center pl-0">
                                                <v-icon
                                                    v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                                                    @click="CommentDelete({
                                                    bId: item.bId, 
                                                    cId: item.cId,
                                                    page: page
                                                    })"
                                                    color="red lighten-3"
                                                    fab
                                                >
                                                mdi-delete
                                                </v-icon>
                                            </v-col>
                                            <v-col cols="4" class="text-center pl-0">
                                                <v-icon
                                                    @click="ShowReply(item)"                                    
                                                    color="grey lighten-1"
                                                    fab
                                                    dark
                                                >
                                                mdi-pencil
                                                </v-icon>
                                            </v-col>
                                        </v-row>
                                    </v-col>
                                    <v-col cols="8" md="8" sm="8"
                                        v-if="item.cShow2 === true"
                                    >
                                        <v-textarea
                                            style="width:100%"
                                            class="ml-4"
                                            auto-grow
                                            outlined
                                            label="댓글을 작성하세요."
                                            name="cContent"
                                            v-model="cContent"
                                        >
                                        </v-textarea>                                        
                                    </v-col>
                                    <v-col cols="4" md="4" sm="4" class="Center"
                                        v-if="item.cShow2 === true">
                                        <v-icon
                                            class="Center"
                                            @click="CommentReply({
                                            bId: item.bId,
                                            cId: item.cId,
                                            cContent:cContent,
                                            username:Userinfo.User_Id,
                                            page: page,
                                            cGroup:item.cGroup,
                                            cOrder:item.cOrder,
                                            cDepth:item.cDepth
                                            })"
                                        >mdi-file-document-edit
                                        </v-icon>
                                    </v-col>

                                    <v-col cols="8" md="8" sm="8"
                                        v-if="item.cShow === true"
                                    >
                                        <v-textarea
                                            style="width:100%"
                                            class="ml-4"
                                            auto-grow
                                            outlined
                                            label="댓글을 수정하세요."
                                            name="cContent"
                                            v-model="cContent"
                                        >
                                        </v-textarea>                                        
                                    </v-col>
                                    <v-col cols="4" md="4" sm="4" class="Center"
                                        v-if="item.cShow === true">
                                        <v-icon
                                            @click="CommentEdit({                                        
                                            bId: item.bId,
                                            cId: item.cId,
                                            cContent:cContent,
                                            username:Userinfo.User_Id,
                                            page: page,
                                            cShow: item.cShow
                                            })"
                                        >mdi-file-document-edit
                                        </v-icon>
                                    </v-col>                                    
                                </v-row>
                            </v-card>
                        </v-col>
                    </v-row>
                     <v-row>
                        <v-col cols="12" md="12" sm="12">
                            <tfoot class="Center">
                                <v-pagination
                                    v-model="page"
                                    :length="Pagination.lastPage"
                                    circle
                                    @input="move({page:page, bId:board.bId , board:board})"
                                >
                                </v-pagination>
                            </tfoot>
                        </v-col>
                    </v-row>
                </v-card>
            </v-col>
        </v-row>

        <v-row dense
            v-if="this.$store.state.Show === false"
           >
            <v-col cols="12" md="12" sm="12">
                 <v-card class="pa-3" outlined tile style="height:600px;" color="White">
                    <v-card>
                        <v-card-text class="text-left border: 1px solid primary">
                            <strong>댓글 작성</strong>
                        </v-card-text>        
                    </v-card>
                    <v-form class="pa-3">
                        <v-textarea
                            outlined
                            auto-grow
                            label="댓글을 입력하세요"
                            name="cContent"
                            v-model="cContent"
                        >
                        </v-textarea>
                        
                        <v-row>
                            <v-col cols="12" md="12" sm="12" class="text-right">
                                <v-btn large outlined
                                    @click="CommentWrite(
                                {
                                    page:page,
                                    cContent:cContent,                                     
                                    username:Userinfo.User_Id,
                                    bId:bId
                                })"
                                > 댓글 작성 완료
                                    <v-icon>mdi-pencil</v-icon>
                                </v-btn>
                            </v-col>
                        </v-row>
                        
                    </v-form>
                </v-card>
            </v-col>
        </v-row>
            <v-col>
                <Footer></Footer>
            </v-col>    
    </v-container>
</template>

<style scoped>
    html{
        font-size:10px;
    }
    .font-size{
        font-size: 80%;
    }
    .font-size-date{
        font-size: 60%;
    }
</style>

<script>
import axios from 'axios'
import Route from '../router/index'
import Footer from '../components/Footer.vue'
import BwBar from '../components/BwBar.vue'
import { mapState } from 'vuex'
export default {
    props: ['bId'],
    data(){
        return{
            board: {}, // Object Type은 null 대신 {} 객체로 받아야함
            cContent: null,
            username:null,
            page:1,
            pageUnit:5,
            perPage:5,
        }
    },
    created(){
        let dev = 'localhost'
        let sev = '3.38.87.14'

        new Promise((resolve, reject) => {
            axios.get(`http://${sev}:9000/api/auth/boarddetail/${this.bId}/${this.page}`)
            .then(Response => {
                console.log('return board vo')
                console.log(Response.data)
                this.board = Response.data
                this.$store.commit('READ_COMMENT_LIST', Response.data)
                this.$store.commit('InitData') // boarddetail page 접속 시 항상 Show 값 false 고정
                //state의 Pagination 값을 변화시키고 사용할 수 있게 값 셋팅  
            })
            .catch(Error => {
                console.log(Error)
                alert('새로고침 이후에는 홈으로 이동합니다.')
                Route.push("/")
            })
        })
    },
    methods: {
        CommentReply(payload){
            let dev = 'localhost'
            let sev = '3.38.87.14'

            console.log('CommentReply Run')
            console.log(payload)
            new Promise((resolve,reject) => {
            axios.post(`http://${sev}:9000/api/auth/commentwrite/${payload.page}`, payload)
            .then(Response => {
                console.log("Response Data를 받았습니다")
                console.log(Response.data)
                this.$store.commit('READ_COMMENT_LIST', Response.data)
            })
            .catch(Error => {
                console.log('error')
                reject(Error)
                alert("Error!")
            })
            })
        },
        
        CommentEdit(payload){ // payload = {bId, cId, page, username, cContent, cShow}
            let dev = 'localhost'
            let sev = '3.38.87.14'

            console.log('payload를 받았습니다.')
            console.log(payload.cShow)
            console.log(payload)
            payload.cShow =! payload.cShow //cShow 다시 true > false로 조정
            console.log(payload.cShow)
            new Promise((resolve,reject) => {
            axios.post(`http://${sev}:9000/api/auth/commentedit/${payload.page}`, payload)
            .then(Response => {
                console.log("Response Data를 받았습니다")
                console.log(Response.data)
                this.$store.commit('READ_COMMENT_LIST', Response.data)
                this.$store.commit('SET_SHOW', payload.cShow) 
                this.cContent = null // 댓글 수정 후 cContent 초기화
            })
            .catch(Error => {
                console.log('error')
                reject(Error)
                alert("Error!")
            })
            })
        },
        CommentWrite(payload) {
            let dev = 'localhost'
            let sev = '3.38.87.14'

            new Promise((resolve, reject) => {
                axios.post(`http://${sev}:9000/api/auth/commentwrite/${payload.page}`, payload)
                .then(Response => {
                    console.log('CommentWrite Run')
                    console.log(payload)
                    console.log(Response.data)          // 새로 작성된 댓글 insert 이후 Commentlist
                    console.log(Response.data.commentList)
                    this.$store.commit('READ_COMMENT_LIST', Response.data)
                    this.cContent = null // 댓글 입력 후 입력 창 초기화
                })
                .catch(Error => {
                    console.log('error')
                    reject(Error)
                    alert("Error!")
                })
                })
            },
        move(payload){
            console.log('next')
            console.log(payload)
            console.log(payload.page)
            console.log(payload.bId) //bId를 그냥 보내면 계속 초기화되기 때문에 payload로 넘겨줌
            this.$store.dispatch('CommentPaginationList', payload)
        },
        CommentDelete(payload){
            let dev = 'localhost'
            let sev = '3.38.87.14'

            if(confirm('정말로 글을 삭제하시겠습니까?')===true){
                new Promise((resolve, reject) => {
                    axios.post(`http://${sev}:9000/api/auth/commentdelete/${payload.page}`, payload)
                    .then(Response => {
                        console.log('payload')
                        console.log(payload)
                        console.log(Response.data)
                        this.$store.commit('READ_COMMENT_LIST', Response.data)
                    })
                    .catch(Error => {
                        console.log('error')
                        reject(Error)
                        alert("Error!")
                    
                    })
                })
             }
        },
        Show(comment){ //commentlist의 배열 인덱스 item
            comment.cShow =! comment.cShow
            console.log(comment)
            console.log(comment.cShow)
            this.$store.commit('SET_SHOW', comment.cShow)
        },
        ShowReply(comment){ 
            comment.cShow2 =! comment.cShow2
            console.log(comment)
            this.$store.commit('SET_SHOW', comment.cShow2)
        },
    },
    computed:{
        ...mapState(['Userinfo', 'Pagination','commentlist'])
    },
    components:{
        Footer,
        BwBar
    }
}    
</script>