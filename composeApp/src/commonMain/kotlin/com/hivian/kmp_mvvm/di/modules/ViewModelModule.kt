package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.basicFeature.presentation.detail.DetailViewModel
import com.hivian.kmp_mvvm.basicFeature.presentation.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel(get(), get(), get()) }
    factory { userId -> DetailViewModel(userId.get(), get(), get(), get()) }
}
