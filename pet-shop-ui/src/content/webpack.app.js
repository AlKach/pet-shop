const path = require('path');
const merge = require('webpack-merge');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const common = require('./webpack.common.js');

const outputDir = 'dist/web';

module.exports = merge(common, {
	entry: {
		app: './app.js'
	},
	output: {
		path: path.resolve(__dirname, outputDir)
	},
	plugins: [
	new CleanWebpackPlugin([outputDir]),
		new HtmlWebpackPlugin({
			template: 'index.html'
		})
	],
	resolve: {
		alias: {
			vue: 'vue/dist/vue.js'
		}
	}
});