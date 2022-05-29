package com.sshpro.piff.business.cache

class DefaultCacheStrategy : CacheStrategy {
    override fun shouldUpdate(): Boolean {
        return true
    }
}