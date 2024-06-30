package com.hivian.randomusers.homefeature.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.hivian.randomusers.core.presentation.navigation.NavigationAction
import com.hivian.randomusers.homefeature.presentation.detail.maps.GoogleMapView
import com.hivian.randomusers.homefeature.presentation.detail.maps.GoogleMapViewEntries
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import randomuserscomposemultiplatform.composeapp.generated.resources.Res
import randomuserscomposemultiplatform.composeapp.generated.resources.ic_cell_24dp
import randomuserscomposemultiplatform.composeapp.generated.resources.ic_email_24dp
import randomuserscomposemultiplatform.composeapp.generated.resources.ic_local_phone_24dp

@Composable
fun DetailScreen(
    userId: Int,
    viewModel: DetailViewModel = koinViewModel(parameters = { parametersOf(userId) }),
    onNavigateBack: () -> Unit
) {
    viewModel.initialize()

    val navigationEventState = viewModel.navigationEvent.collectAsState(initial = null)

    LaunchedEffect(navigationEventState.value) {
        when (navigationEventState.value) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DetailContent(
    @PreviewParameter(DetailViewModelArgProvider::class) viewModelArg: DetailViewModelArg
) {
    Scaffold(
        topBar = {
            TopAppBar(
                /*backgroundColor = LocalCustomColorPalette.current.toolbarBackgroundColor,
                contentColor = LocalCustomColorPalette.current.toolbarContentColor,*/
                title = { Text(text = viewModelArg.name.value) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewModelArg.navigateBack()
                        }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
            .padding(top = 16.dp)
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
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
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
        Image(
            painter = painterResource(resource = drawableStart),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = text,
            //color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun GoogleMapAddress(
    latitude: Double, longitude: Double, city: String, country: String
) {
    val mapViewEntries = arrayOf(
        GoogleMapViewEntries(
            lat = latitude,
            lng = longitude,
            title = city,
            snippet = country
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        GoogleMapView(
            modifier = Modifier.fillMaxSize(),
            googleMapViewEntries = mapViewEntries,
            zoom = 8f
        )
    }

}
