package com.hivian.randomusers.homeFeature.di

import com.hivian.randomusers.homeFeature.presentation.detail.DetailViewModel
import com.hivian.randomusers.homeFeature.presentation.home.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}
