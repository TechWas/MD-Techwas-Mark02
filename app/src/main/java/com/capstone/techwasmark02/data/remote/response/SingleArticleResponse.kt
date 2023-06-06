package com.capstone.techwasmark02.data.remote.response

import com.google.gson.annotations.SerializedName

data class SingleArticleResponse(

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("article")
	val article: List<ArticleItem?>? = null
)

data class ArticleItem(

	@field:SerializedName("componentID")
	val componentID: Int? = null,

	@field:SerializedName("articleImageURL")
	val articleImageURL: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("componentName")
	val componentName: String? = null
)
