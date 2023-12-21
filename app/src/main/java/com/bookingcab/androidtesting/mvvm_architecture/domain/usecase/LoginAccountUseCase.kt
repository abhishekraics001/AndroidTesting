package com.bookingcab.mvvm_architecture.domain.usecase

interface LoginAccountUseCase {

    suspend operator fun invoke(): Int

    suspend  fun executeAPICall(): Int
}