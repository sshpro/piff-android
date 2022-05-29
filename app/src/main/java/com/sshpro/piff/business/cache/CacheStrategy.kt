package com.sshpro.piff.business.cache

interface CacheStrategy {
    fun shouldUpdate(): Boolean
}