package cash.buddy.go.credit.bee.com.loan.dao

import androidx.room.*
import cash.buddy.go.credit.bee.com.loan.bean.BankBean

@Dao
interface BankDao {
    @Query("SELECT * FROM bankbean")
    fun getAll(): List<BankBean>
    @Insert
    fun insert(user: BankBean?)
    @Delete
    fun delete(user: BankBean?)
    @Query("DELETE FROM bankbean")
    fun deleteAll()
}