package com.pilotflyingj.codechallenge.network.models


import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonPrimitive

@Serializable
data class ApiSiteItem(
    @SerialName("Address")
    val address: String,
    @SerialName("AddressTwo")
    val addressTwo: JsonPrimitive,
    @SerialName("Area")
    val area: JsonPrimitive,
    @SerialName("City")
    val city: String,
    @SerialName("Country")
    val country: String,
    @SerialName("Interstate")
    val interstate: String,
    @SerialName("Latitude")
    val latitude: Double,
    @SerialName("Longitude")
    val longitude: Double,
    @SerialName("Phone")
    val phone: String,
    @SerialName("SpaceAvailability")
    val spaceAvailability: List<SpaceAvailability>,
    @SerialName("State")
    val state: String,
    @SerialName("StoreFrontBrand")
    val storeFrontBrand: JsonPrimitive,
    @SerialName("StoreName")
    val storeName: String,
    @SerialName("StoreNo")
    val storeNo: Int,
    @SerialName("StoreType")
    val storeType: JsonPrimitive,
    @SerialName("ZipCode")
    val zipCode: String
) {
    @Serializable
    data class SpaceAvailability(
        @SerialName("Available")
        val available: Int,
        @SerialName("Booked")
        val booked: Int,
        @SerialName("Description")
        val description: String,
        @SerialName("ItemID")
        val itemID: Int,
        @SerialName("LocationID")
        val locationID: Int,
        @SerialName("Price")
        val price: Int,
        @SerialName("Total")
        val total: Int
    )
}