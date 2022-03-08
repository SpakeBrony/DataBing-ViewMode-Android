package cash.buddy.go.credit.bee.com.loan.bean

import androidx.annotation.NonNull
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
class BankBean(
    ifscCode: String,
    bankAccountNumber: String,
    confirmBankAccount: String,
    upiId: String
) {


    @NonNull
    @PrimaryKey
    var id: Int?=null

    @ColumnInfo(name = "ifsc_code")
    var ifscCode: String? = ifscCode

    @ColumnInfo(name = "bank_account_number")
    var bankAccountNumber: String? = bankAccountNumber

    @ColumnInfo(name = "confirm_bank_account")
    var confirmBankAccount: String? = confirmBankAccount

    @ColumnInfo(name = "upi_id")
    var upiId: String? = upiId


    override fun toString(): String {
        return "BankBean(id=$id, ifscCode=$ifscCode, bankAccountNumber=$bankAccountNumber, confirmBankAccount=$confirmBankAccount, upiId=$upiId)"
    }


}