package com.b4tchkn.times.ui

sealed class LoadingStatus {
    data class Init(val loading: Boolean) : LoadingStatus()
    data class Refresh(val loading: Boolean) : LoadingStatus()
}
