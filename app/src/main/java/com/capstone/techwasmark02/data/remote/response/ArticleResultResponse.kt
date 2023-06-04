package com.capstone.techwasmark02.data.remote.response

import com.google.gson.annotations.SerializedName

data class ArticleResultResponse(
	val componentList: List<ComponentListItem?>,
	val error: String?,
	val message: String?,
)

data class ComponentListItem(
	@SerializedName("componentId")
	val componentId: Int?,
	@SerializedName("articleImageURL")
	val articleImageURL: String?,
	@SerializedName("name")
	val name: String?,
	@SerializedName("id")
	val id: Int?,
	@SerializedName("desc")
	val desc: String?,
)

