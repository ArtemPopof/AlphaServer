package org.artempopov.client.net

import org.artempopov.serverFirst.proto.ResponseProto

data class Error(val message: String?, val error: ResponseProto.ErrorType)