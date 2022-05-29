package com.sshpro.piff.business.cache

import com.sshpro.piff.business.Mapper
import com.sshpro.piff.business.domain.Photo
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : Mapper<PhotoCacheEntity, Photo> {

    override fun mapToDomain(input: PhotoCacheEntity): Photo {
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

//    override fun mapToEntity(domain: Photo): PhotoCacheEntity {
//        return PhotoCacheEntity(
//            url = domain.url,
//            title = domain.title,
//            dateTaken = domain.dateTaken,
//            datePublished = domain.datePublished,
//            description = domain.description,
//            author = domain.author,
//            authorId = domain.authorId,
//            tags = domain.tags
//        )
//    }

    override fun mapToDomainList(entities: List<PhotoCacheEntity>): List<Photo> {
        return entities.map { mapToDomain(it) }
    }

}