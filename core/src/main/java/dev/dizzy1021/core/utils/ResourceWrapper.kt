package dev.dizzy1021.core.utils

data class ResourceWrapper<out R>(val state: ResourceState, val data: R?, val message: String?) {

    companion object {
        fun <R> success(data: R?): ResourceWrapper<R> {
            return ResourceWrapper(ResourceState.SUCCESS, data, null)
        }

        fun <R> failure(msg: String, data: R?): ResourceWrapper<R> {
            return ResourceWrapper(ResourceState.FAILURE, data, msg)
        }

        fun <R> pending(data: R?): ResourceWrapper<R> {
            return ResourceWrapper(ResourceState.PENDING, data, null)
        }

    }

}