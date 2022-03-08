package cash.buddy.go.credit.bee.com.loan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cash.buddy.go.credit.bee.com.loan.bean.BankBean
import cash.buddy.go.credit.bee.com.loan.dao.BankDao

@Database(entities = [BankBean::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bankDao(): BankDao

    /**
     * 单例数据库
     */
    companion object {
        private var instance: AppDatabase? = null
        @Synchronized
        fun get(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "bank_database")
                    .build()
            }
            return instance!!
        }
    }


}