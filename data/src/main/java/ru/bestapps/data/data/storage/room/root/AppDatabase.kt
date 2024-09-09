package ru.bestapps.data.data.storage.room.root

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.bestapps.data.data.storage.room.dao.DeleteVacanciesDao
import ru.bestapps.data.data.storage.room.dao.GetOffersDao
import ru.bestapps.data.data.storage.room.dao.GetVacanciesDao
import ru.bestapps.data.data.storage.room.dao.GetVacancyDao
import ru.bestapps.data.data.storage.room.dao.SaveOffersDao
import ru.bestapps.data.data.storage.room.dao.SaveVacanciesDao
import ru.bestapps.data.data.storage.room.dao.SetFavoriteDao
import ru.bestapps.data.data.storage.room.entity.Converters
import ru.bestapps.data.data.storage.room.entity.LikedVacancy
import ru.bestapps.data.data.storage.room.entity.Offer
import ru.bestapps.data.data.storage.room.entity.Vacancy


@Database(entities = [Vacancy::class, Offer::class, LikedVacancy::class], version = 12, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppLocalDatabase : RoomDatabase() {
    abstract fun getVacanciesDao(): GetVacanciesDao
    abstract fun saveVacanciesDao(): SaveVacanciesDao
    abstract fun deleteVacanciesDao(): DeleteVacanciesDao
    abstract fun getOffersDao(): GetOffersDao
    abstract fun saveOffersDao(): SaveOffersDao
    abstract fun setFavoriteDao(): SetFavoriteDao
    abstract fun getVacancyDao(): GetVacancyDao

    companion object {
        @Volatile
        private var INSTANCE: AppLocalDatabase? = null
        fun getDatabase(context: Context): AppLocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppLocalDatabase::class.java,
                    "vacancy_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}