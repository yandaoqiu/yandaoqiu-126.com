// 获取促销活动列表接口
const { mysql } = require('../qcloud')

/**
 * 响应 GET 请求（响应微信配置时的签名检查请求）
 */
async function get(ctx, next) {
  
  var res = await mysql("cSaleList").where({ isShow: 1 })
  for (var index in res){
    var item = res[index]
    var salepic = item.sale_pic
    var pic = await mysql.select().from('cPic').where('pic_id',  salepic).first()
    item.sale_pic = pic.address
  }
  ctx.state.data = res
}

async function post(ctx, next) {
  // 检查签名，确认是微信发出的请求
  // const { signature, timestamp, nonce } = ctx.query
  // if (!checkSignature(signature, timestamp, nonce)) ctx.body = 'ERR_WHEN_CHECK_SIGNATURE'

  /**
   * 解析微信发送过来的请求体
   * 可查看微信文档：https://mp.weixin.qq.com/debug/wxadoc/dev/api/custommsg/receive.html#接收消息和事件
   */
  const body = ctx.request.body
  var res = await mysql("cSaleList").where({ isShow: 1 })
  ctx.state.data = res
}

module.exports = {
  post,
  get
}



