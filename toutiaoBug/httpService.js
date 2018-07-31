let https = require('https')
let httpsService = (options, data) => {
    return new Promise((resolve, reject) => {
        let result = ''
        let req = https.request(options, (res) => {
            res.on('data', (chunk) => {
                result += chunk
            })
            res.on('end', () => {
                resolve(JSON.parse(result))
            })
        })
        req.on('error', (e) => {
            reject(e)
        })
        if (data) req.write(data)
        req.end()
    })
}

exports.httpsFn = httpsService