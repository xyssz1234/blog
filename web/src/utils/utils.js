const tools = {
    getToken() {
        return localStorage.getItem("token")
    },
    getUserInfo() {
        return localStorage.getItem("userInfo")
    }

}

export default tools;