package com.sshpro.piff.business

interface Mapper<Entity, Domain> {
    fun mapToDomain(input: Entity): Domain
    fun mapToDomainList(entities: List<Entity>): List<Domain>
}