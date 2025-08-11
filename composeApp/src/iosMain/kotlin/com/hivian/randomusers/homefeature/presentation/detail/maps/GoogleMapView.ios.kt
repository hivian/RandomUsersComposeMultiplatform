package com.hivian.randomusers.homefeature.presentation.detail.maps

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import cocoapods.GoogleMaps.GMSAdvancedMarker.Companion.markerImageWithColor
import cocoapods.GoogleMaps.GMSCameraPosition
import cocoapods.GoogleMaps.GMSMapView
import cocoapods.GoogleMaps.GMSMapView.Companion.mapWithFrame
import cocoapods.GoogleMaps.GMSMarker
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import platform.CoreGraphics.CGRectZero
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.UIKit.UIColor

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun GoogleMapView(
    modifier: Modifier,
    vararg googleMapViewEntries: GoogleMapViewEntries,
    zoom: Float
) {
    UIKitView(
        factory = {
            val camera: GMSCameraPosition? = if (googleMapViewEntries.isNotEmpty()){
                GMSCameraPosition.cameraWithLatitude(
                    latitude = googleMapViewEntries.first().lat,
                    longitude = googleMapViewEntries.first().lng,
                    zoom = zoom
                )
            } else null

            val mapView = camera?.let {
                mapWithFrame(frame = cValue { CGRectZero }, camera = it)
            }?: GMSMapView()
            mapView.settings.apply {
                zoomGestures = true
                tiltGestures = false
                zoomGestures = false
                scrollGestures = false
            }
            googleMapViewEntries.forEach { entry ->
                GMSMarker().apply {
                    this.position = CLLocationCoordinate2DMake(
                        latitude = entry.lat,
                        longitude = entry.lng
                    )
                    this.title = entry.title
                    this.snippet = entry.snippet
                    markerImageWithColor(UIColor.redColor)
                }.map = mapView
            }
            mapView
        },
        modifier = modifier,
        onRelease = {
            it.removeFromSuperview()
        }
    )
}