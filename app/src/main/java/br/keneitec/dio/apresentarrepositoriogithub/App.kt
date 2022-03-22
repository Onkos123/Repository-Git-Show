package br.keneitec.dio.apresentarrepositoriogithub

import android.app.Application
import br.keneitec.dio.apresentarrepositoriogithub.data.di.DataModule
import br.keneitec.dio.apresentarrepositoriogithub.domain.di.DomainModule
import br.keneitec.dio.apresentarrepositoriogithub.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}