var path = require("path");

module.exports = {
    output : {
        filename: "[name].bundle.js",
        path: __dirname + "/build"
    },
    devServer: {
        contentBase: __dirname + "/build/",
        inline: true,
        hot: true,
        host: "localhost",
        port: 3000
    },
}