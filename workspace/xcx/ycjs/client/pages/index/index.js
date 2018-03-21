//index.js
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

// 显示失败提示
var showModel = (title, content) => {
  wx.hideToast();

  wx.showModal({
    title,
    content: JSON.stringify(content),
    showCancel: false
  });
};

var doLogin={
 showBusy:'',
}

Page({
  data: {
    loginUrl: config.service.loginUrl,
    path:base.path.res,
    // motto: '酒水饮料批发',
    // userInfo: {},
    // array: ['盐城','上海', '北京', '杭州', '宁波'],
    index: 0,
   salelist:[],
   toView: 'sale_id',
   scrollTop: 100
  },
  goCake: function (e) {
    var brand = e.currentTarget.dataset.brand;
    if(brand&&brand==1){
      base.cake.tab=1;
    }
    wx.switchTab({ url: '../cake/cake' });
  },
  goDetail: function (e) {
    var nm = e.currentTarget.dataset.pname;
    var b = e.currentTarget.dataset.brand;
    wx.navigateTo({
      url: '../cakeDetail/cakeDetail?pname=' + nm+"&brand="+(b||0)
    })
  },
  bindPickerChange: function (e) {
    this.setData({
      index: e.detail.value
    })
  },
  //事件处理函数
  bindViewTap: function () {
    wx.showActionSheet({
      itemList: ['A', 'B', 'C'],
      success: function (res) {
        if (!res.cancel) {
          console.log(res.tapIndex)
        }
      }
    })

    //wx.navigateTo({
    //url: '../socket/socket'
    //})
  },

  onLoad: function () {
    var that = this
    //调用应用实例的方法获取全局数据
    //app.getUserInfo(function (userInfo) {
    //更新数据
    //that.setData({
    //userInfo: userInfo
    //})
    //})
    showBusy('请稍等');

    // 登录之前需要调用 qcloud.setLoginUrl() 设置登录地址，不过我们在 app.js 的入口里面已经调用过了，后面就不用再调用了
    qcloud.login({
      success(result) {
        // showSuccess('加载成功');
        console.log('登录成功', result);
      },

      fail(error) {
        // showModel('加载失败', error);
        console.log('登录失败', error);
      }
    });

    //获取活动清单
    this.showSaleList();
  },
  showSaleList(){
    qcloud.request({
      url: config.service.saleList,
      login: false,
      success: (result) => {
     
       var saleProductList = result.data.data;
      //  for (var i = 0; i < saleProductList.length; i++) {
      //    l.push(saleProductList[i])
      //    } 
       this.setData({ salelist: saleProductList});
        showSuccess('加载成功');
     
        console.log('获取数据', saleProductList);
      }, 
      fail:(error) =>{
        showModel('加载失败', error);
        console.log('获取加载失败', error);
      }
    });
    
  },

  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
  },
  onShareAppMessage: function () {
    return {
      title: '酒水饮料批发',
      desc: '',
      path: '/pages/index/index?id=123'
    }
  }
})
