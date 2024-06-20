package com.hivian.kmp_mvvm.basicFeature.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.hivian.kmp_mvvm.core.services.navigation.NavigationAction
import kmp_mvvm.composeapp.generated.resources.Res
import kmp_mvvm.composeapp.generated.resources.ic_cell_24dp
import kmp_mvvm.composeapp.generated.resources.ic_email_24dp
import kmp_mvvm.composeapp.generated.resources.ic_local_phone_24dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreen(
    userId: Int,
    viewModel: DetailViewModel = koinInject(parameters = { parametersOf(userId) }),
    onNavigateBack: () -> Unit
) {
    viewModel.initialize()

    LaunchedEffect(viewModel.navigationEvent) {
        when (viewModel.navigationEvent.value) {
            is NavigationAction.Back -> onNavigateBack()
            else -> Unit
        }
    }

    DetailContent(
        DetailViewModelArg(
            picture = viewModel.picture,
            name = viewModel.name,
            email = viewModel.email,
            cell = viewModel.cell,
            phone = viewModel.phone,
            city = viewModel.city,
            country = viewModel.country,
            latitude = viewModel.latitude,
            longitude = viewModel.longitude,
            navigateBack = { viewModel.navigateBack() }
        )
    )
}

@Preview
@Composable
fun DetailContent(
    @PreviewParameter(DetailViewModelArgProvider::class) viewModelArg: DetailViewModelArg
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = viewModelArg.name.value) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewModelArg.navigateBack()
                        }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "backIcon"
                        )
                    }
                },
            )
        }
    ) { contentPadding ->
        Column(
            Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageDetail(
                imageUrlPath = viewModelArg.picture.value
            )
            UserInfo(
                email = viewModelArg.email.value,
                phone = viewModelArg.phone.value,
                cell = viewModelArg.cell.value
            )
            GoogleMapAddress(
                latitude = viewModelArg.latitude.value,
                longitude = viewModelArg.longitude.value,
                city = viewModelArg.city.value,
                country = viewModelArg.country.value
            )
        }
    }
}

@Composable
fun ImageDetail(imageUrlPath : String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imageUrlPath)
            .crossfade(true)
            .build()
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center,
    ) {
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.primary, CircleShape)
        )
    }
}

@Composable
fun UserInfo(email: String, phone: String, cell: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp, 16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserInfoItem(drawableStart = Res.drawable.ic_email_24dp, text = email)
            UserInfoItem(drawableStart = Res.drawable.ic_local_phone_24dp, text = phone)
            UserInfoItem(drawableStart = Res.drawable.ic_cell_24dp, text = cell)
        }
    }
}

@Composable
fun UserInfoItem(drawableStart: DrawableResource, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(resource = drawableStart), contentDescription = null)
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = text,
            style = MaterialTheme.typography.h2,
        )
    }
}

@Composable
fun GoogleMapAddress(
    latitude: Double, longitude: Double, city: String, country: String
) {
    /*val location = LatLng(latitude, longitude)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        GoogleMap(
            cameraPositionState = CameraPositionState(
                position = CameraPosition.fromLatLngZoom(location, 8f)
            ),
            modifier = Modifier.fillMaxSize(),
            uiSettings = MapUiSettings(
                tiltGesturesEnabled = false,
                zoomControlsEnabled = false,
                zoomGesturesEnabled = false,
                scrollGesturesEnabled = false,
            )
        ) {
            Marker(
                state = MarkerState(position = location),
                title = city,
                snippet = country
            )
        }
    }*/

}
