package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.basicFeature.presentation.detail.DetailViewModel
import com.hivian.kmp_mvvm.basicFeature.presentation.home.HomeViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.detail.DetailScreenViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.HomeScreenViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.profile.ProfileViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.saved_photos.SavedPhotosScreenViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos.SearchPhotosScreenViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.topics.TopicScreenViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel(get(), get(), get(), get()) }
    factory { userId -> DetailViewModel(userId.get(), get(), get(), get(), get()) }
}
