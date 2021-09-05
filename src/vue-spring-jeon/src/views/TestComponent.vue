<template>
    <v-container>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300;400&display=swap" rel="stylesheet">   
    <body>
        <nav class="navbar">
            <div class="navbar__logo">
                <v-icon color="#d49466">mdi-instagram</v-icon>
                <a href="">DereamCoding</a>
            </div>

            <ul class="navbar__menu" v-if="btnvalue === false">
                <li><a href="">Home</a></li>
                <li><a href="">Gallery</a></li>
                <li><a href="">Weddings</a></li>
                <li><a href="">FAQ</a></li>
                <li><a href="">Bookings</a></li>
            </ul>

            <ul class="navbar__icons">
                <li><v-icon color="white">mdi-twitter</v-icon></li>
                <li><v-icon color="white">mdi-instagram</v-icon></li>
            </ul>

            <v-btn class="navbar__btn" color="#263343" @click="clickMenu()">
                <v-icon color="#d49466">mdi-view-list</v-icon>
            </v-btn>
        </nav>
    </body>
    </v-container>
</template>

<style scoped>
    body {
        margin: 0;
        font-family: 'Source Sans Pro';
    }

    a {
        text-decoration: none;
        color: white;
    }

    a:visited {
        color: white;
    }

    .navbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background: #263343;
        padding: 8px 12px;
    }

    .navbar__menu {
        display: flex;
        list-style: none;
        padding-left: 0;
    }

    .navbar__menu li {
        padding: 8px 12px;
    }

    .navbar__menu li:hover {
        background: #d49466;
        border-radius: 4px;
    }

    .navbar__icons {
        list-style: none;
        display: flex;
    }

    .navbar__icons li {
        padding: 8px 12px;
    }

    .navbar__btn {
        display: none;
        position: absolute;
        right: 32px;
    }

    @media screen and (max-width:768px) {
        .navbar {
            flex-direction: column;
            align-items: flex-start;
        }

        .navbar__menu {
            flex-direction: column;
            align-items: center;
            width: 100%;
        }

        .navbar__menu li {
            width: 100%;
            text-align: center;
        }

        .navbar__icons {
            justify-content: center;
            width: 100%;
            padding: 0;
        }

        .navbar__btn {
            display: block;
        }
    }

</style>

<script>
export default {
    data() {
        return {
            btnvalue: false

        }
    },

    methods: {
        clickMenu() {
            console.log('clickMenu Run !')
            console.log('현재 btnvalue는?' + this.btnvalue)
            if(this.btnvalue === true) {
                this.btnvalue = false
            } else {
                this.btnvalue = true
            }
            console.log('현재 btnvalue는?' + this.btnvalue)  
        }
    }


}
</script>
