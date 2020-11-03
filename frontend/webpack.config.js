const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
require('dotenv').config();

const { REACT_APP_BASE_URI } = process.env;
const path = require('path');

const mode = process.env.NODE_ENV || 'development';

const config = {
    mode: mode,
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
                test: /\.s(a|c)ss$/,
                exclude: /\.module.(s(a|c)ss)$/,
                loader: [
                    mode ? 'style-loader' : MiniCssExtractPlugin.loader,
                    'css-loader',
                    {
                        loader: 'sass-loader',
                        options: {
                            sassOptions: {
                                indentWidth: 4,
                            },
                        },
                    },
                ],
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
        extensions: ['.js', '.jsx', '.scss'],
        alias: {
            '@': path.resolve(__dirname, './src/'),
        },
    },
    plugins: [
        new CleanWebpackPlugin(),
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, 'public', 'index.html'),
        }),
        new MiniCssExtractPlugin({
            filename: mode ? '[name].css' : '[name].[hash].css',
            chunkFilename: mode ? '[id].css' : '[id].[hash].css',
        }),
    ],
};

module.exports = config;
