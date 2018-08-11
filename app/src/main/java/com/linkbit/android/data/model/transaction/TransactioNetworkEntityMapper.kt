package com.linkbit.android.data.model.transaction

import com.linkbit.android.data.model.NetworkEntityMapper
import com.linkbit.android.data.model.RealmEntityMapper
import com.linkbit.android.entity.TransactionModel
import java.math.BigInteger

object TransactioNetworkEntityMapper : NetworkEntityMapper<TransactionModel, TransactionNetworkObject> {
    override fun fromNetworkObject(obj: TransactionNetworkObject): TransactionModel {
        return TransactionModel().apply {
            transactionHash = obj.transactionHash
            sourceAddress = obj.sourceAddress
            targetAddress = obj.targetAddress
            status = obj.status
            amount = obj.amount
            targetProfile = obj.targetProfile
            date = obj.date
            blockNumber = obj.blockNumber
            confirmation = obj.confirmation
        }
    }

    override fun toNetworkObject(model: TransactionModel): TransactionNetworkObject {
        return TransactionNetworkObject().apply {
            transactionHash = model.transactionHash
            sourceAddress = model.sourceAddress
            targetAddress = model.targetAddress
            status = model.status
            amount = model.amount
            targetProfile = model.targetProfile
            date = model.date
            blockNumber = model.blockNumber
            confirmation = model.confirmation
        }
    }
}