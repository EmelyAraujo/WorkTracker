package edu.ucne.worktracker.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.worktracker.data.local.WorkTrackerDb
import edu.ucne.worktracker.data.remote.MaterialApi
import edu.ucne.worktracker.data.remote.ObraApi
import edu.ucne.worktracker.data.repository.MaterialRepositoryApi
import edu.ucne.worktracker.data.repository.MaterialRepositoryImp
import edu.ucne.worktracker.data.repository.ObraRepositoryApi
import edu.ucne.worktracker.data.repository.ObraRepositoryImp
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract  fun bindMaterialRepository(impl: MaterialRepositoryImp): MaterialRepositoryApi

    @Binds
    abstract  fun bindObrasRepository(impl: ObraRepositoryImp): ObraRepositoryApi
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): WorkTrackerDb {
        return Room.databaseBuilder(
            context,
            WorkTrackerDb::class.java,
            "WorkTrackerDb.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesMaterialDao(db: WorkTrackerDb) = db.materialDao

    @Singleton
    @Provides
    fun providesObraDao(db: WorkTrackerDb) = db.obraDao

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun providesMaterialrApi(moshi: Moshi): MaterialApi {
        return Retrofit.Builder()
            .baseUrl("https://worktrackerapi1.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MaterialApi::class.java)
    }
    @Singleton
    @Provides
    fun providesObraApi(moshi: Moshi): ObraApi {
        return Retrofit.Builder()
            .baseUrl("https://worktrackerapi1.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ObraApi::class.java)
    }
}