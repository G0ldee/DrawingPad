package com.faprojects.android.criminalintent

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import java.util.UUID

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): LiveData<List<Crime>>
    //fun getCrimes():List<Crime>

    @Query("SELECT * FROM crime WHERE id =(:id)")
    fun getCrime(id: UUID): LiveData<Crime?>
    //fun getCrime(id: UUID): Crime?
}