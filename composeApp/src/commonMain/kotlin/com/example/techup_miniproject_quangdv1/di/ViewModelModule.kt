package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.presentation.screens.imageInputScreen.ImageInputViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Koin module providing ViewModel dependencies.
 *
 * TODO: User will add repository injections to ViewModels later
 *       when implementing the actual business logic.
 */
val viewModelModule = module {

    // ── ImageInputViewModel ────────────────────────────────────
    // TODO: User will update ViewModel constructor to inject repositories later
    viewModelOf(::ImageInputViewModel)
}
