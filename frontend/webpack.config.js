const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
require('dotenv').config();

const { REACT_APP_BASE_URI } = process.env;
const path = require('path');

const config = {
    entry: './src/index.js',
    output: {
        path: path.resolve(__dirname, 'public/dist'),
        publicPath: '/',
        filename: '[name]-bundle.js',
        chunkFilename: '[name]-[chunkhash].js',
    },
    optimization: {
        removeAvailableModules: false,
        removeEmptyChunks: false,
        splitChunks: false,
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                use: 'babel-loader',
                exclude: /node_modules/,
            },
            {
                test: /\.css$/,
                loaders: ['style-loader', 'css-loader'],
            },
        ],
    },
    devServer: {
        port: 3000,
        inline: true,
        hot: true,
        historyApiFallback: true,
        contentBase: path.resolve(__dirname, 'public', 'dist'),
        proxy: {
            context: () => true,
            '/': REACT_APP_BASE_URI,
            headers: {
                Connection: 'keep-alive',
            },
        },
    },
    resolve: {
        extensions: ['.js', '.jsx'],
        alias: {
            '@': path.resolve(__dirname, './src/'),
        },
    },
    plugins: [
        new CleanWebpackPlugin(),
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, 'public', 'index.html'),
        }),
    ],
};

module.exports = config;
