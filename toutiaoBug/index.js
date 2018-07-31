let httpService = require('./httpService')
let util = require('./util')
let queryString = require('querystring')
let sql = require('./sql')
let options = {
    hostname: 'www.toutiao.com',
    port: 443,
    method: 'GET',
    path: '/api/pc/feed/',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
    }
}
global.navigator = {
    userAgent: 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36',
}
let ascp = util.getAsCp()
let categoryArr=['news_tech','news_entertainment']
let params = {
    category: 'news_finance',
    utm_source: 'toutiao',
    widen: 1,
    max_behot_time: 0,
    max_behot_time_tmp: 0,
    tadrequire: true,
    as: ascp.as,
    cp: ascp.cp,
    _signature: util.TAC.sign(0)
}
let data = queryString.stringify(params)
let url = 'https://www.toutiao.com/api/pc/feed/?'
let getData = async () => {
    let data = queryString.stringify(params)
    let resultUrl = url + data
    let res = await httpService.httpsFn(resultUrl, data)
    console.log(res)
    params.max_behot_time = res.next.max_behot_time
    params.max_behot_time_tmp = res.next.max_behot_time
    params._signature = util.TAC.sign(res.next.max_behot_time)
    res.data.forEach(async (item) => {
        await saveData(item)
    })
}
let saveData = async (item) => {
    let sqlString = 'insert into news(tag,title,imgUrl,hotTime,abstract,detailUrl) values(?,?,?,?,?,?)'
    let params = [item.tag, item.title, item.image_url, item.behot_time, item.abstract, 'https://www.toutiao.com' + item.source_url]
    await sql.upDataQuery(sqlString, params)
}

let init = async () => {
    for (let i = 0; i < 10; i++) {
        await getData()
    }
}
init()

