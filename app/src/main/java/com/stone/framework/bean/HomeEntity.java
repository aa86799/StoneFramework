package com.stone.framework.bean;



import java.io.Serializable;
import java.util.List;

/**
 * Created by cdy on 2017/12/6.
 */

public class HomeEntity implements Serializable {
    private String category;
    private String categoryName;
    private String categoryType;
    private String imgUrl;
    private String linkType;
    private String linkValue;
    private int itemType;
    private List<HomeFloorEntity> list; //用于被 homeAdapter 一次性加载；即没有分页

    private MerchandiseSearchVoEntity youLike1; // 猜你喜欢的数据
    private MerchandiseSearchVoEntity youLike2; // 猜你喜欢的数据

    public MerchandiseSearchVoEntity getYouLike1() {
        return youLike1;
    }

    public void setYouLike1(MerchandiseSearchVoEntity youLike1) {
        this.youLike1 = youLike1;
    }

    public MerchandiseSearchVoEntity getYouLike2() {
        return youLike2;
    }

    public void setYouLike2(MerchandiseSearchVoEntity youLike2) {
        this.youLike2 = youLike2;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
//        setItemType(category);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getLinkValue() {
        return linkValue;
    }

    public void setLinkValue(String linkValue) {
        this.linkValue = linkValue;
    }

    public List<HomeFloorEntity> getList() {
        return list;
    }

    public void setList(List<HomeFloorEntity> list) {
        this.list = list;
    }

    /**
     *  BRVAH 的 multiTypeAdapter 使用
     *
     * banner("轮播图"),
     * <p>
     * icon("图标"),
     * <p>
     * advertisement("广告"),
     * <p>
     * seckill("商品秒杀"),
     * <p>
     * brand("缤纷品牌"),
     * <p>
     * special("特价商品"),
     * <p>
     * enjoyshopping("乐享欢购")
     *
     * @return
     */



    public void setItemType(int item) {
        itemType = item;
    }

    public class HomeFloorEntity implements Serializable {
        private String imgUrl;
        private String linkType;
        private String linkValue;
        private String maxPrice;
        private String maxScore;
        private String minPrice;
        private String minScore;
        private String name;
        private String type;
        private String id;
//        private String noticeContent;//新闻通知内容
        private String typeName;//新闻通知内容

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        //        public String getNoticeContent() {
//            return noticeContent;
//        }
//
//        public void setNoticeContent(String noticeContent) {
//            this.noticeContent = noticeContent;
//        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinkType() {
            return linkType;
        }

        public void setLinkType(String linkType) {
            this.linkType = linkType;
        }

        public String getLinkValue() {
            return linkValue;
        }

        public void setLinkValue(String linkValue) {
            this.linkValue = linkValue;
        }

        public String getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(String maxPrice) {
            this.maxPrice = maxPrice;
        }

        public String getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(String maxScore) {
            this.maxScore = maxScore;
        }

        public String getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(String minPrice) {
            this.minPrice = minPrice;
        }

        public String getMinScore() {
            return minScore;
        }

        public void setMinScore(String minScore) {
            this.minScore = minScore;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
