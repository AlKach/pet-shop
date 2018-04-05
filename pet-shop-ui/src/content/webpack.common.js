const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = function(configName) {
    const outputDir = `dist/${configName}`;
    return {
        entry: {
            app: `./${configName}/index.js`
        },
        output: {
            path: path.resolve(__dirname, outputDir),
            filename: '[name].[hash].js'
        },
        module: {
            rules: [
                {
                    test: /\.css$/,
                    use: ExtractTextPlugin.extract({
                        fallback: "style-loader",
                        use: "css-loader"
                    })
                }
            ]
        },
        optimization: {
            splitChunks: {
                cacheGroups: {
                    commons: {
                        test: /[\\/]node_modules[\\/]/,
                        name: "vendors",
                        chunks: "all"
                    }
                }
            }
        },
        plugins: [
            new CleanWebpackPlugin([configName], {
                root: path.resolve('.', 'dist')
            }),
            new HtmlWebpackPlugin({
                template: `./${configName}/index.html`
            }),
            new ExtractTextPlugin('[name].css')
        ]
    };
};