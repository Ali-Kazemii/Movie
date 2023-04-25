package com.example.sabaideatest.util

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in Req, Res> where Res : Any? {
    abstract suspend fun run(request: Req): Flow<NetworkResponse<Res>>
}