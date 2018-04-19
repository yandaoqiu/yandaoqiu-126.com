// pages/market/index.js
//获取应用实例
var base = getApp();
// 引入 QCloud 小程序增强 SDK
var qcloud = require('../../vendor/wafer2-client-sdk/index');

// 引入配置
var config = require('../../config');
// 显示繁忙提示
var showBusy = text => wx.showToast({
  title: text,
  icon: 'loading',
  duration: 10000
});

// 显示成功提示
var showSuccess = text => wx.showToast({
  title: text,
  icon: 'success'
});
//消失
var dismissBusy=()=>{
  wx.hideToast();
};
// 显示失败提示
var showModel = (title, content) => {
  wx.hideToast();

  wx.showModal({
    title,
    content: JSON.stringify(content),
    showCancel: false
  });
};
Page({
  /**
   * 页面的初始数据
   */
  data: {
    tab: 0,
    productlist: [],
  },
  /**
   * 点击切换
   */
  changeTab: function (e) {
    var d = e.currentTarget.dataset.index;
    this.setData({ tab: d });
    //获取数据
    this.loadData(++d);
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.loadData(1);
  },

  loadData(i){
    var array = base.market.getCache(i);
    if (array && array.length > 0) {
      this.setData({ productlist: array });
      //TOOD 刷新页面
      console.log('缓存数据', array);
    } else {
      //获取type = 1的数据
      this.showProductList(i);
    }
  },
  //请求商品 按照类型
  showProductList(i) {
    showBusy('请稍等');
    qcloud.request({
      url: config.service.productList,
      data:{
        index: i,
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'post',
      login: false,
      success: (result) => {
        var pList = result.data.data;
        this.setData({ productlist: pList });
        base.market.setCache(i, pList);
        dismissBusy();
        //TOOD 刷新页面

        console.log('获取数据', pList);
      },
      fail: (error) => {
        dismissBusy();
        showModel('加载失败', error);
        console.log('获取加载失败', error);
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
})