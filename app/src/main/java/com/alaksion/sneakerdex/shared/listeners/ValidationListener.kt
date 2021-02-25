package com.alaksion.sneakerdex.shared.listeners

class ValidationListener(message: String = "") {
    private var isSuccess : Boolean = true
    private var errorMessage : String = ""

    init {
        if (message.isNotEmpty()) {
            this.isSuccess = false
            errorMessage = message
        }
    }

    fun getSuccess() : Boolean {
        return this.isSuccess
    }

    fun getMessage() : String{
        return this.errorMessage
    }

}