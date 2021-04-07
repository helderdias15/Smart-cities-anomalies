package com.example.smartcitiesanomalies.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.smartcitiesanomalies.dao.NotasDao
import com.example.smartcitiesanomalies.entidades.Notas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.security.AccessControlContext


@Database(entities = arrayOf(Notas::class), version = 2, exportSchema = false)
public abstract class NotasDB :RoomDatabase(){
    abstract fun notasDao(): NotasDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var notasDao = database.notasDao()

                    notasDao.deleteAll()

                    var notas = Notas( 1 , "Nota1", "DescricaO1")
                    notasDao.insert(notas)
                    notas = Notas( 2 , "Nota2", "DescricaO2")
                    notasDao.insert(notas)

                }
            }
        }
    }





    companion object{
        @Volatile
        private var INSTANCE: NotasDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotasDB{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotasDB::class.java,
                    "tabela_notas"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                    INSTANCE = instance
                    return instance
            }
        }
    }
}