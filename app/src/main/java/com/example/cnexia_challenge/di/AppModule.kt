package com.example.cnexia_challenge.di

import android.content.Context
import androidx.room.Room
import com.example.cnexia_challenge.database.LocalDatabase
import com.example.cnexia_challenge.database.OnCreateCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideLocalDatabase(
        @ApplicationContext context: Context,
        onCreateCallback: OnCreateCallback
    ) = Room.databaseBuilder(
        context,
        LocalDatabase::class.java, "local_database"
    ).addCallback(onCreateCallback).build()

    @Singleton
    @Provides
    fun provideCarDao(db: LocalDatabase) = db.getCarDao()
}
