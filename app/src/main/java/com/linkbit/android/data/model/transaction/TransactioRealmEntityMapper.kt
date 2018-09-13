package com.linkbit.android.data.model.transaction

import com.linkbit.android.data.model.RealmEntityMapper
import com.linkbit.android.entity.TransactionModel
import java.math.BigInteger

object TransactioRealmEntityMapper : RealmEntityMapper<TransactionModel, TransactionRealmObject> {
    override fun fromRealmObject(obj: TransactionRealmObject): TransactionModel {
        return TransactionModel().apply {
            transactionHash = obj.transactionHash
            sourceAddress = obj.sourceAddress
            targetAddress = obj.targetAddress
            status = obj.status
            amount = obj.amount
            symbol = obj.symbol
            targetProfile = obj.targetProfile
            date = obj.date
            blockNumber = obj.blockNumber
            confirmation = obj.confirmation
        }
    }

    override fun toRealmObject(model: TransactionModel): TransactionRealmObject {
        return TransactionRealmObject().apply {
            transactionHash = model.transactionHash
            sourceAddress = model.sourceAddress
            targetAddress = model.targetAddress
            status = model.status
            amount = model.amount
            symbol = model.symbol
            targetProfile = model.targetProfile
            date = model.date
            blockNumber = model.blockNumber
            confirmation = model.confirmation
        }
    }
}