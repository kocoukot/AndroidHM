package com.example.androidhomework.threading.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract
import com.example.androidhomework.threading.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<Movie>)

    @Query("SELECT * FROM ${MovieContract.TABLE_NAME} WHERE ${MovieContract.Columns.MOVIE_TITLE} LIKE :movieTitle AND ${MovieContract.Columns.MOVIE_TYPE} = :movieType")
    fun activeMovies(movieTitle: String, movieType: String): Flow<List<Movie>>
}
//
//