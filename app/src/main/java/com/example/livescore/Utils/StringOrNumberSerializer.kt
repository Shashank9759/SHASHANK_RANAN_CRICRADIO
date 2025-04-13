package com.example.livescore.Utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive

object StringOrNumberSerializer : KSerializer<String> {
    override val descriptor = PrimitiveSerialDescriptor("StringOrNumber", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): String {
        return if (decoder is JsonDecoder) {
            val prim = decoder.decodeJsonElement().jsonPrimitive
            prim.contentOrNull ?: prim.int.toString()
        } else decoder.decodeString()
    }
    override fun serialize(encoder: Encoder, value: String) = encoder.encodeString(value)
}
