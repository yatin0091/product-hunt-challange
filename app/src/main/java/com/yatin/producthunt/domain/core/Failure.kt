package com.yatin.producthunt.domain.core

/**
 * Base Class for handling errors/failures/exceptions.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
}
