package com.tkdev.muzapp.domain.models

import kotlinx.coroutines.Job

data class ResponseJob(
    val id: String,
    val job: Job
)