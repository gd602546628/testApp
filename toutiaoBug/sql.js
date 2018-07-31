let mysql = require('mysql')
let connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '835414',
    database: 'toutiao'
})

let query = (queryString) => {
    return new Promise((resolve, reject) => {
        connection.query(queryString, (err, result) => {
            if (err) {
                reject(err)
            } else {
                resolve(result)
            }
        })
    })
}

let upDataQuery = (queryString, params) => {
    return new Promise((resolve, reject) => {
        connection.query(queryString, params, (err, result) => {
            if (err) {
                reject(err)
            } else {
                resolve(result)
            }
        })
    })
}

exports.query = query
exports.upDataQuery = upDataQuery