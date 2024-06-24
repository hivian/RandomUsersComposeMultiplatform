package com.hivian.randomusers.homefeature.di

import com.hivian.randomusers.homefeature.presentation.detail.DetailViewModel
import com.hivian.randomusers.homefeature.presentation.home.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}
