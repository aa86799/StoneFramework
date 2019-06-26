package com.stone.framework.bean.test;

import java.io.Serializable;

/**
 * Created by cdy on 2017/12/8.
 * 商品类
 */

public class MerchandiseSearchVoEntity implements Serializable {

    /**
     * 店铺id
     */
    private long storeId;

    /**
     * 行业id
     */
    private String industryLeafId;

    /**
     * 行业名称
     */
    private String leafName;

    /**
     * 返还比例id   序列化时候 忽略
     */

    private long restoreScaleId;

    /**
     * 邮费默认包邮几件
     */
    private int postageNum;


    /**
     * 返还比例  值
     */
    private int restoreScale;
    /**
     * 商品id
     */
    private long merchandiseId;
    /**
     * 商品名称
     */
    private String merchandiseName;

    /**
     * 最高价
     */
    private double maxPrice;

    /**
     * 最低价
     */
    private double minPrice;

    /**
     * 最高积分
     */
    private double maxScore;

    /**
     * 最低积分
     */
    private double minScore;

    /**
     * 商品主图
     */
    private String majorImageUrl;

    /**
     * 商品图片
     */
    private String imageUrl;

    /**
     * 描述
     */
    private String descrip;

    /**
     * 详细
     */
    private String detail;

    /**
     * 规格json
     */
    private String specJson;

    /**
     * 关注数
     */
    private long attentionNumber;

    /**
     * 点击量
     */
    private long visitsNumber;

    /**
     * 交易量
     */
    private long tradeNum;

    /**
     * 发货地
     */
    private long sendAreaId;


    /**
     * 是否开启余额支付,0:关闭,1:开启
     */
    private int balancePayment;

    /**
     * 店铺分类id
     */
    private String storeClassificationId;

    /**
     * 0,非自营，1.自营,2.全球购
     */
    private int selfSupport;

    /**
     * 邮费(-1查看moduleid，如果存在默认拿邮费)
     */
    private double postage;

    /**
     * 邮费模板Id
     */
    private long postageModuleId;

    /**
     * 发货周期
     */
    private int deliveryPeriod;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 供应商
     */
    private String supplier;

    /**  */
    private String brand;

    /**  */
    private String origin;

    /**
     * 限购数量
     */
    private int purchaseLimitation;

    /**
     * 限购周期
     */
    private int quantityCycle;

    /**
     * 状态（0.下架，1.上架）
     */
    private int status;

    /**
     * 是否冻结（0.否，1.是）
     */
    private int isfrozen;

    /**
     * 创建时间
     */
    private String createTime;


    //-----------------------店铺信息 部分----------------------
    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 所在区
     */
    private String area;



    /**  */
    private long areaId;

    /**
     * 商店类型：1.自营 2.全球购 3.其他
     */
    private int storeType;

    /**
     * 列表中图片
     */
    private String logoUrl;

    /**
     * 多主图 逗号分隔
     */
    private String signageImageUrl;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 电话号码
     */
    private String servicePhone;

    /**
     * 详细信息
     */
    private String storeInfo;

    /**
     * 纬度
     */
    private double storeLat;

    /**
     * 经度
     */
    private double storeLng;

    /**  */
    private String storeExtendCode;

    /**
     * 一级行业id(6)
     */
    private String sysIndustryId;

    /**
     * 关注数
     */
    private long userAttentionNum;

    /**  */
    private long areaSalesmanId;

    /**
     * 临时表店铺id
     */
    private long tempStoreId;

    /**
     * 审核通过时间
     */
    private String auditTime;

    /**
     * VR链接
     */
    private String vrUrl;



    //======================

    private boolean isAttent= false;//是否关注
    private boolean isAttentStore=false; //是否关注 店铺
    private String merchandiseShareUrl;

    public String getMerchandiseShareUrl() {
        return merchandiseShareUrl;
    }

    public void setMerchandiseShareUrl(String merchandiseShareUrl) {
        this.merchandiseShareUrl = merchandiseShareUrl;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getIndustryLeafId() {
        return industryLeafId;
    }

    public void setIndustryLeafId(String industryLeafId) {
        this.industryLeafId = industryLeafId;
    }

    public String getLeafName() {
        return leafName;
    }

    public void setLeafName(String leafName) {
        this.leafName = leafName;
    }

    public long getRestoreScaleId() {
        return restoreScaleId;
    }

    public void setRestoreScaleId(long restoreScaleId) {
        this.restoreScaleId = restoreScaleId;
    }

    public int getPostageNum() {
        return postageNum;
    }

    public void setPostageNum(int postageNum) {
        this.postageNum = postageNum;
    }

    public int getRestoreScale() {
        return restoreScale;
    }

    public void setRestoreScale(int restoreScale) {
        this.restoreScale = restoreScale;
    }

    public long getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {
        this.merchandiseName = merchandiseName;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public double getMinScore() {
        return minScore;
    }

    public void setMinScore(double minScore) {
        this.minScore = minScore;
    }

    public String getMajorImageUrl() {
        return majorImageUrl;
    }

    public void setMajorImageUrl(String majorImageUrl) {
        this.majorImageUrl = majorImageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSpecJson() {
        return specJson;
    }

    public void setSpecJson(String specJson) {
        this.specJson = specJson;
    }

    public long getAttentionNumber() {
        return attentionNumber;
    }

    public void setAttentionNumber(long attentionNumber) {
        this.attentionNumber = attentionNumber;
    }

    public long getVisitsNumber() {
        return visitsNumber;
    }

    public void setVisitsNumber(long visitsNumber) {
        this.visitsNumber = visitsNumber;
    }

    public long getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(long tradeNum) {
        this.tradeNum = tradeNum;
    }

    public long getSendAreaId() {
        return sendAreaId;
    }

    public void setSendAreaId(long sendAreaId) {
        this.sendAreaId = sendAreaId;
    }

    public int getBalancePayment() {
        return balancePayment;
    }

    public void setBalancePayment(int balancePayment) {
        this.balancePayment = balancePayment;
    }

    public String getStoreClassificationId() {
        return storeClassificationId;
    }

    public void setStoreClassificationId(String storeClassificationId) {
        this.storeClassificationId = storeClassificationId;
    }

    public int getSelfSupport() {
        return selfSupport;
    }

    public void setSelfSupport(int selfSupport) {
        this.selfSupport = selfSupport;
    }

    public double getPostage() {
        return postage;
    }

    public void setPostage(double postage) {
        this.postage = postage;
    }

    public long getPostageModuleId() {
        return postageModuleId;
    }

    public void setPostageModuleId(long postageModuleId) {
        this.postageModuleId = postageModuleId;
    }

    public int getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(int deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPurchaseLimitation() {
        return purchaseLimitation;
    }

    public void setPurchaseLimitation(int purchaseLimitation) {
        this.purchaseLimitation = purchaseLimitation;
    }

    public int getQuantityCycle() {
        return quantityCycle;
    }

    public void setQuantityCycle(int quantityCycle) {
        this.quantityCycle = quantityCycle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsfrozen() {
        return isfrozen;
    }

    public void setIsfrozen(int isfrozen) {
        this.isfrozen = isfrozen;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getSignageImageUrl() {
        return signageImageUrl;
    }

    public void setSignageImageUrl(String signageImageUrl) {
        this.signageImageUrl = signageImageUrl;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(String storeInfo) {
        this.storeInfo = storeInfo;
    }

    public double getStoreLat() {
        return storeLat;
    }

    public void setStoreLat(double storeLat) {
        this.storeLat = storeLat;
    }

    public double getStoreLng() {
        return storeLng;
    }

    public void setStoreLng(double storeLng) {
        this.storeLng = storeLng;
    }

    public String getStoreExtendCode() {
        return storeExtendCode;
    }

    public void setStoreExtendCode(String storeExtendCode) {
        this.storeExtendCode = storeExtendCode;
    }

    public String getSysIndustryId() {
        return sysIndustryId;
    }

    public void setSysIndustryId(String sysIndustryId) {
        this.sysIndustryId = sysIndustryId;
    }

    public long getUserAttentionNum() {
        return userAttentionNum;
    }

    public void setUserAttentionNum(long userAttentionNum) {
        this.userAttentionNum = userAttentionNum;
    }

    public long getAreaSalesmanId() {
        return areaSalesmanId;
    }

    public void setAreaSalesmanId(long areaSalesmanId) {
        this.areaSalesmanId = areaSalesmanId;
    }

    public long getTempStoreId() {
        return tempStoreId;
    }

    public void setTempStoreId(long tempStoreId) {
        this.tempStoreId = tempStoreId;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getVrUrl() {
        return vrUrl;
    }

    public void setVrUrl(String vrUrl) {
        this.vrUrl = vrUrl;
    }


    public boolean isAttent() {
        return isAttent;
    }

    public void setAttent(boolean attent) {
        isAttent = attent;
    }


    public boolean isAttentStore() {
        return isAttentStore;
    }

    public void setAttentStore(boolean attentStore) {
        isAttentStore = attentStore;
    }
}
