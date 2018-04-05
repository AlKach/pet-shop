const merge = require('webpack-merge');
const commonConfig = require('../webpack.common');

const configName = 'rest';

module.exports = merge(commonConfig(configName), {});