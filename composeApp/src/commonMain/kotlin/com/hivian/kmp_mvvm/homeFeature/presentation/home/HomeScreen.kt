package com.hivian.kmp_mvvm.homeFeature.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.hivian.kmp_mvvm.core.domain.base.ViewModelVisualState
import com.hivian.kmp_mvvm.core.presentation.navigation.NavigationAction
import com.hivian.kmp_mvvm.homeFeature.domain.models.RandomUser
import com.hivian.kmp_mvvm.homeFeature.presentation.themes.LocalCustomColorPalette
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToDetail: (Int) -> Unit
) {
    viewModel.initialize()

    val navigationEventState = viewModel.navigationEvent.collectAsState(initial = null)

    LaunchedEffect(navigationEventState.value) {
        when (val event = navigationEventState.value) {
            is NavigationAction.ToDetailScreen -> {
                onNavigateToDetail(event.userId)
            }
            else -> Unit
        }
    }

    HomeContent(
        HomeViewModelArg(
            randomUsers = viewModel.items,
            isListLoading = viewModel.showLoadMoreLoader,
            title = viewModel.title,
            errorMessage = viewModel.errorMessage,
            retryMessage = viewModel.retryMessage,
            refresh = { viewModel.refresh() },
            loadNext = { viewModel.loadNext() },
            openDetail = { userId -> viewModel.openRandomUserDetail(userId) },
            visualState = viewModel.viewModelVisualState
        )
    )
}

@Preview
@Composable
fun HomeContent(
    @PreviewParameter(HomeViewModelArgProvider::class) viewModelArg: HomeViewModelArg
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LocalCustomColorPalette.current.toolbarBackgroundColor,
                contentColor = LocalCustomColorPalette.current.toolbarContentColor,
                title = { Text(text = viewModelArg.title.value) },
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            when (viewModelArg.visualState.value) {
                is ViewModelVisualState.Loading -> CircularProgressIndicator()
                is ViewModelVisualState.Success -> {
                    InitUserList(
                        viewModelArg.randomUsers,
                        isLoadingMore = viewModelArg.isListLoading.value,
                        onItemClick = { userId -> viewModelArg.openDetail(userId) },
                        onLoadMore = { viewModelArg.loadNext() }
                    )
                }
                is ViewModelVisualState.Error -> InitErrorView(
                    errorViewArgs = InitErrorViewArg(
                        viewModelArg.errorMessage.value, viewModelArg.retryMessage.value
                    ),
                    onRetry = { viewModelArg.refresh() }
                )
                else -> Unit
            }
        }
    }
}

@Composable
fun InitErrorView(
    @PreviewParameter(InitErrorViewArgProvider::class)
    errorViewArgs: InitErrorViewArg,
    onRetry : () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = errorViewArgs.errorMessage, style = MaterialTheme.typography.body1)
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = { onRetry() }
        ) {
            Text(text = errorViewArgs.retryMessage)
        }
    }
}

@Composable
fun InitUserList(
    randomUsers: List<RandomUser>,
    isLoadingMore: Boolean,
    onItemClick : (Int) -> Unit,
    onLoadMore : () -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(randomUsers) { user ->
            UserListItem(user = user, onItemClick = onItemClick)
        }
        if (!isLoadingMore) return@LazyColumn

        item {
            UserListLoadingItem()
        }
    }
    listState.OnBottomReached(buffer = 3) {
        onLoadMore()
    }
}

@Composable
fun UserListItem(user: RandomUser, onItemClick : (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onItemClick(user.id) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            Modifier.height(IntrinsicSize.Min)
        ) {
            UserImage(imageUrlPath = user.picture)
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(text = user.fullName, style = MaterialTheme.typography.h6)
                Text(text = user.email, style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun UserListLoadingItem() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun UserImage(imageUrlPath : String = "") {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imageUrlPath)
            .crossfade(true)
            .build()
    )

    Box(
        modifier = Modifier.size(66.dp),
        contentAlignment = Alignment.Center
    ) {
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize(0.5f),
                strokeWidth = 2.dp
            )
        }

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = CornerSize(8.dp),
                        topEnd = CornerSize(0.dp),
                        bottomStart = CornerSize(8.dp),
                        bottomEnd = CornerSize(0.dp)
                    )
                )
        )
    }
}
