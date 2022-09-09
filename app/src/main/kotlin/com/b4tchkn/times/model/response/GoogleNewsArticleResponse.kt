package com.b4tchkn.times.model.response

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class GoogleNewsArticleResponse @JvmOverloads constructor(
    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String,

    @field:Element(name = "link")
    @param:Element(name = "link")
    val link: String,

    @field:Element(name = "pubDate")
    @param:Element(name = "pubDate")
    val pubDate: String,

    @field:Element(name = "description")
    @param:Element(name = "description")
    val description: String,

    @field:Element(name = "source")
    @param:Element(name = "source")
    val source: String,
)
