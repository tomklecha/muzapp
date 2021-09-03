package com.tkdev.muzapp.common

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class CoroutineContextProvider @Inject constructor() {
    val Main: CoroutineContext by lazy { Dispatchers.Main }
    val Io: CoroutineContext by lazy { Dispatchers.IO }
    val Default: CoroutineContext by lazy { Dispatchers.Default }
}