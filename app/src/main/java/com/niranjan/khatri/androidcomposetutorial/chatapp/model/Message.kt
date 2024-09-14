package com.niranjan.khatri.androidcomposetutorial.chatapp.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Chat::class,
            parentColumns = ["id"],
            childColumns = ["chatId"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Contact::class,
            parentColumns = ["id"],
            childColumns = ["senderId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index("chatId"),
        Index("senderId"),
    ],
)
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val chatId: Long,
    val senderId: Long,
    val text: String,
    val mediaUri: String?,
    val mediaMimeType: String?,
    val timestamp: Long,
) {

    val isIncoming: Boolean
        get() = senderId != 0L

    class Builder {
        var id: Long = 0L
        var chatId: Long? = null
        var senderId: Long? = null
        var text: String? = null
        var mediaUri: String? = null
        var mediaMimeType: String? = null
        var timestamp: Long? = null
        fun build(): Message {
            requireNotNull(chatId)
            requireNotNull(senderId)
            requireNotNull(text)
            requireNotNull(timestamp)
            return Message(
                id!!,
                chatId!!,
                senderId!!,
                text!!,
                mediaUri,
                mediaMimeType,
                timestamp!!,
            )
        }
    }
}