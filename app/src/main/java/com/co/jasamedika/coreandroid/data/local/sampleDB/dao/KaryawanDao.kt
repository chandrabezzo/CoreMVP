package com.co.jasamedika.coreandroid.data.local.sampleDB.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.co.jasamedika.coreandroid.data.model.general.Karyawan
import com.co.jasamedika.coreandroid.util.constanta.AppConstans
import io.reactivex.Flowable

@Dao
interface KaryawanDao {
    @Query("SELECT * FROM " + AppConstans.KARYAWAN)
    fun getAll(): Flowable<List<Karyawan>>

    @Query("SELECT * FROM " + AppConstans.KARYAWAN
            + " LIMIT 1")
    fun get(): Flowable<Karyawan>

    @Query("SELECT * FROM " + AppConstans.KARYAWAN
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<Karyawan>

    @Query("SELECT * FROM " + AppConstans.KARYAWAN
            + " WHERE id=:id")
    fun get(id: Int): Flowable<Karyawan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Karyawan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : List<Karyawan>)

    @Query("DELETE FROM " + AppConstans.KARYAWAN)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.KARYAWAN
            + " WHERE id=:id")
    fun delete(id: Int)

    @Query("SELECT COUNT(*) FROM " + AppConstans.KARYAWAN)
    fun count(): Int
}