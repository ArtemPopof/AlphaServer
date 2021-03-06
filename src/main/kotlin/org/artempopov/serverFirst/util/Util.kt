package org.artempopov.serverFirst.util

import org.artempopov.serverFirst.proto.ResponseProto

fun createErrorResponse(error: ResponseProto.ErrorType): ResponseProto.Response {
    val builder = ResponseProto.Response.newBuilder()

    builder.error = error

    return builder.build()
}

fun createErrorResponse(error: ResponseProto.ErrorType, message: String): ResponseProto.Response {
    val builder = ResponseProto.Response.newBuilder()

    builder.error = error
    builder.errorMessage = message

    return builder.build()
}

fun createEmptyResponse(): ResponseProto.Response {
    return ResponseProto.Response.newBuilder().build()
}