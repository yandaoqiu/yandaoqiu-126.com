// 获取商品列表接口
const { mysql } = require('../qcloud')

/**
 * 响应 GET 请求（响应微信配置时的签名检查请求）
 */
async function get(ctx, next) {
  var res = await mysql("cProduct").where({ isShow: 1, p_type}).orderBy('pindex', 'esc')
  for (var index in res) {
    var item = res[index]
    var salepic = item.sale_pic
    var pic = await mysql.select().from('cPic').where('pic_id', salepic).first()
    item.sale_pic = pic.address
  }
  ctx.state.data = res
}

async function post(ctx, next) {
  var res = await mysql("cProduct").where({ isShow: 1, p_type: ctx.req.body.index }).orderBy('pindex', 'esc')
  for (var index in res) {
    var item = res[index]
    var salepic = item.sale_pic
    var pic = await mysql.select().from('cPic').where('pic_id', salepic).first()
    item.sale_pic = pic.address
  }
  ctx.state.data = res
}

module.exports = {
  post,
  get
}



