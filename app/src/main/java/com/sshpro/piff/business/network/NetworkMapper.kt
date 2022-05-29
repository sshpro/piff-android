package com.sshpro.piff.business.network

import com.sshpro.piff.business.Mapper
import com.sshpro.piff.business.domain.Photo
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : Mapper<PhotoNetworkEntity, Photo> {

    override fun mapToDomain(input: PhotoNetworkEntity): Photo {
        return Photo(
            url = input.url,
            title = input.title,
            dateTaken = input.dateTaken,
            datePublished = input.datePublished,
            description = input.description,
            author = input.author,
            authorId = input.authorId,
            tags = input.tags.trim().split("\\s+".toRegex())
        )
    }


    override fun mapToDomainList(entities: List<PhotoNetworkEntity>): List<Photo> {
        return entities.map { mapToDomain(it) }
    }

}