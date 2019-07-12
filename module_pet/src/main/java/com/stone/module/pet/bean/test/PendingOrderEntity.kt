package com.stone.module.pet.bean.test

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class PendingOrderEntity(): Parcelable, Serializable {

    var appointmentTime: String? = null
    var deliveryAddress: String? = null
    var deliveryName: String? = null
    var deliveryPhone: String? = null
    var id: String? = null
    var orderNum: String? = null
    var petsType: String? = null
    var receivingAddress: String? = null
    var receivingName: String? = null
    var receivingPhone: String? = null
    var type: Int = -1

    constructor(parcel: Parcel) : this() {
        appointmentTime = parcel.readString()
        deliveryAddress = parcel.readString()
        deliveryName = parcel.readString()
        deliveryPhone = parcel.readString()
        id = parcel.readString()
        orderNum = parcel.readString()
        petsType = parcel.readString()
        receivingAddress = parcel.readString()
        receivingName = parcel.readString()
        receivingPhone = parcel.readString()
        type = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(appointmentTime)
        parcel.writeString(deliveryAddress)
        parcel.writeString(deliveryName)
        parcel.writeString(deliveryPhone)
        parcel.writeString(id)
        parcel.writeString(orderNum)
        parcel.writeString(petsType)
        parcel.writeString(receivingAddress)
        parcel.writeString(receivingName)
        parcel.writeString(receivingPhone)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PendingOrderEntity> {
        override fun createFromParcel(parcel: Parcel): PendingOrderEntity {
            return PendingOrderEntity(parcel)
        }

        override fun newArray(size: Int): Array<PendingOrderEntity?> {
            return arrayOfNulls(size)
        }
    }
}