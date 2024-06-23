package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.homeFeature.presentation.detail.DetailViewModel
import com.hivian.kmp_mvvm.homeFeature.presentation.home.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}
