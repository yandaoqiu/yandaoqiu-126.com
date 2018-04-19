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
  var res = await mysql("cProduct").where({ isShow: 1, p_type:ctx.request.body.index}).orderBy('pindex', 'esc')
  for (var index in res) {
    var item = res[index]
    var productpic = item.pic_id
    var pic = await mysql.select().from('cPic').where('pic_id', productpic).first()
    item.pic_id = pic.address
    //算折扣
    if (item.p_sale != '1'){
      item.price = Math.floor(item.price * item.p_sale)
    }
  }
  ctx.state.data = res
}

module.exports = {
  post,
  get
}



