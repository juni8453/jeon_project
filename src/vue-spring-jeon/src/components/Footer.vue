<template>
  <body>
    <v-container fluid>
      <div class="footerbar">
        <ul class="footer__high">
          <li>CS CENTER<v-icon @click="clickCscenter()">mdi-arrow-down-bold-box</v-icon></li>
          <li>DELIVERY<v-icon @click="clickDelivery()">mdi-arrow-down-bold-box</v-icon></li>
          <li>BANK<v-icon @click="clickBank()">mdi-arrow-down-bold-box</v-icon></li>
        </ul>
        <ul class="footer__row">
          <li v-if="clickValue.CscenterValue == true">1599 - 3120 <br>
                MON-FRI : 10:00 ~ 17:00 <br>
                LUNCH: 13:00 ~ 14:00 <br>
                SAT, SUN, HOLIDAY OFF</li>
          <li v-if="clickValue.DeliveryValue == true">경기도 성남시 분당구 불정로 6 (우)13561</li>
          <li v-if="clickValue.BankValue == true">예금주: (주)ABC <br>
                우리 1005-123-123456 <br>
                농협 351-3333-4444-55 <br>
                기업 114-12342-13-333 <br>
                국민 594401-22-323232 <br>
                신한 140-333-13423414 <br></li>
        </ul>
      </div>
    </v-container>
  </body>
</template>

<style scoped>
  /* 755px 이상 해상도일 때 버튼 사라지도록 설정 */
  button.v-icon  {
    display: none;
    color: #ff9100;
  }

  .footerbar {
    display: flex;
    flex-direction: column;
    background: #263343;
    border-radius: 8px;
  }

  .footer__high{
    display: flex;
    padding-top: 14px;
    padding-bottom: 14px;
    width: 100%;
    justify-content: space-around;
  }

  .footer__high li {
    list-style: none;
    color: #d49466;
    padding-right: 30px;
  }

  .footer__row {
    display: flex;
    padding-top: 14px;
    padding-bottom: 20px;
    width: 100%;
    justify-content: space-around;
  }

  .footer__row li {
    list-style: none;
    color: #f0f4f5;
  }

  .navbar__btn {
    display: none;
    position: absolute;
    right: 32px;
  }

  @media screen and (max-width:755px) {
    .footerbar {
      flex-direction: column;
      text-align: center;
    }

    .footer__high {
      flex-direction: column;
    }

    .footer__row {
      flex-direction: column;
      padding: 0;
    }

    .footer__row li {
      padding-bottom: 20px;
    }

    /* 755px 미만 해상도일 때 버튼 나오도록 설정 */
    button.v-icon {
      display: block;
      color: #ebbe84;
    }
  }
</style>

<script>
import { mapState } from 'vuex'
export default {
  data() {
    return {
      CscenterValue: false,
      DeliveryValue: false,
      BankValue: false
    }
  },

  methods: {
    clickCscenter() {
      console.log('clickCscenter Run !')
      console.log('현재 CscenterValue는' + this.CscenterValue)
      if(this.CscenterValue === false) {
        this.CscenterValue = !this.CscenterValue
        this.$store.commit('SET_CLICK_CSCENTER_VALUE', this.CscenterValue)
        console.log('바뀐 state의 CscenterValue는?' + this.clickValue.CscenterValue)

      } else {
        this.CscenterValue = !this.CscenterValue
        this.$store.commit('SET_CLICK_CSCENTER_VALUE', this.CscenterValue)
      }
    },

    clickDelivery() {
      console.log('clickDelivery Run !')
      console.log('현재 DeliveryValue는' + this.DeliveryValue)
      if(this.DeliveryValue === false) {
        this.DeliveryValue = !this.DeliveryValue
        this.$store.commit('SET_CLICK_DELIVERY_VALUE', this.DeliveryValue)
        console.log('바뀐 state의 DelevaryValue는?' + this.clickValue.DeliveryValue)

      } else {
        this.DeliveryValue = !this.DeliveryValue
        this.$store.commit('SET_CLICK_DELIVERY_VALUE', this.DeliveryValue)
      }
    },  
    
    clickBank() {
      console.log('clickBank Run !')
      console.log('현재 BankValue는' + this.BankValue)
      if(this.BankValue === false) {
        this.BankValue = !this.BankValue
        this.$store.commit('SET_CLICK_BANK_VALUE', this.BankValue)
        console.log('바뀐 state의 BankValue는?' + this.clickValue.BankValue)

      } else {
        this.BankValue = !this.BankValue
        this.$store.commit('SET_CLICK_BANK_VALUE', this.BankValue)
        console.log('바뀐 state의 BankValue는?' + this.clickValue.BankValue)
      }
    },

  },

  computed:{
    ...mapState(['clickValue'])
  },

}  


</script>


