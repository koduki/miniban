module.exports = {
    devServer: {
        port: 3000,
        disableHostCheck: true,
        proxy: {
            "^/login": {
                target: "http://localhost:8080",
                ws: false,
                pathRewrite: {
                    "^/login": "/login"
                }
            }
        }
    },
    pages: {
        index: {
            entry: 'src/main.js',
            title: 'Mini Banking Online',
        }
    }
};