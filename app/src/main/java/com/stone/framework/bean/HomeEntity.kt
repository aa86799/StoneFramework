package com.stone.framework.bean


import java.io.Serializable

/**
 * Created by cdy on 2017/12/6.
 */

class HomeEntity : Serializable {
    //        setItemType(category);
    var category: String? = null
    var categoryName: String? = null
    var categoryType: String? = null
    var imgUrl: String? = null
    var linkType: String? = null
    var linkValue: String? = null
    private var itemType: Int = 0
    var list: List<HomeFloorEntity>? = null //用于被 homeAdapter 一次性加载；即没有分页

    var youLike1: MerchandiseSearchVoEntity? = null // 猜你喜欢的数据
    var youLike2: MerchandiseSearchVoEntity? = null // 猜你喜欢的数据

    /**
     * BRVAH 的 multiTypeAdapter 使用
     *
     * banner("轮播图"),
     *
     *
     * icon("图标"),
     *
     *
     * advertisement("广告"),
     *
     *
     * seckill("商品秒杀"),
     *
     *
     * brand("缤纷品牌"),
     *
     *
     * special("特价商品"),
     *
     *
     * enjoyshopping("乐享欢购")
     *
     * @return
     */


    fun setItemType(item: Int) {
        itemType = item
    }

    inner class HomeFloorEntity : Serializable {
        var imgUrl: String? = null
        var linkType: String? = null
        var linkValue: String? = null
        var maxPrice: String? = null
        var maxScore: String? = null
        var minPrice: String? = null
        var minScore: String? = null
        var name: String? = null
        var type: String? = null
        //        public String getNoticeContent() {
        //            return noticeContent;
        //        }
        //
        //        public void setNoticeContent(String noticeContent) {
        //            this.noticeContent = noticeContent;
        //        }

        var id: String? = null
        //        private String noticeContent;//新闻通知内容
        var typeName: String? = null//新闻通知内容
    }
}
