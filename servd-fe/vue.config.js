const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://servd-be:8080',  // Grails backend server
        changeOrigin: true,   // Ensures the host header is updated with the target value
        secure: false,
        logLevel: 'debug',   // Useful for debugging the proxy setup
      }
    }
  }
})

