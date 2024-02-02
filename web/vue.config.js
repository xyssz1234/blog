const path = require("path")
module.exports = {
    devServer: {
        // open: true, //打开浏览器
        overlay: {
            //当出现编译错误或警告时，在浏览器中显示全屏覆盖。
            warnings: false, //不显示警告
            errors: true, //显示错误
        },
        proxy: {
            //下面的key是一个正则表达式它的/api前加上^和不加^符号差别非常大
            "/api": {
                // target: "http://localhost:9998",
                target: process.env.VUE_APP_SERVER,
                pathRewrite: {
                    //下面的key是一个正则表达式它的/api前加上^和不加^符号差别非常大
                    "^/api": "",
                },
            },
        },
    },
    runtimeCompiler: true,
    lintOnSave: false,
    configureWebpack: {
        resolve: {
            alias: {
                "@": path.resolve(__dirname, "src"),
            },
        },
    },
}