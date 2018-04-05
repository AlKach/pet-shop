const merge = require('webpack-merge');
const commonConfig = require('../webpack.common');

const configName = 'web';

module.exports = merge(commonConfig(configName), {
	resolve: {
		alias: {
			vue: 'vue/dist/vue.js'
		}
	}
});