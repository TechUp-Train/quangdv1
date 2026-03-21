package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.data.repository.GenerateRepositoryImpl
import com.example.techup_miniproject_quangdv1.data.repository.PresignRepositoryImpl
import com.example.techup_miniproject_quangdv1.domain.repository.GenerateRepository
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository
import org.koin.dsl.module

/**
 * Koin module providing repository-layer dependencies.
 *
 * Binds domain repository interfaces to their data-layer implementations.
 *
 * TODO: User will implement the actual repository method bodies later.
 *       The stubs are wired here so the DI graph is complete.
 */
val repositoryModule = module {

    // ── PresignRepository ──────────────────────────────────────
    // TODO: User will implement PresignRepositoryImpl methods later
    single<PresignRepository> {
        PresignRepositoryImpl(
            presignService = get(),
            uploadService = get()
        )
    }

    // ── GenerateRepository ─────────────────────────────────────
    // TODO: User will implement GenerateRepositoryImpl methods later
    single<GenerateRepository> {
        GenerateRepositoryImpl(
            timestampService = get(),
            generateService = get()
        )
    }
}
