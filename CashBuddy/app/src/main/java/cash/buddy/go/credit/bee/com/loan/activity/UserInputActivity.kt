package cash.buddy.go.credit.bee.com.loan.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import android.app.DatePickerDialog
import android.os.Handler
import android.provider.ContactsContract
import android.text.TextUtils
import android.widget.DatePicker
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityIdCardTwoInputBinding
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityUserInputBinding
import cash.buddy.go.credit.bee.com.loan.dialog.SelectDialog
import cash.buddy.go.credit.bee.com.loan.utils.SelectOnClick
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences
import java.util.*


class UserInputActivity : BaseActivity(){


    val CONTACTS_REQUEST_CODE = 0x4326

    private lateinit var binding: ActivityUserInputBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("VERIFICATION")

        // or manual change
        binding.indicator.currentStep = 2
        binding.setClick = SelectOnClick()


        binding.next.setOnClickListener {
            StorePreferences.getInstance(this).user_number = 3
            loadingProgressBar.visibility = View.VISIBLE
            binding.next.isEnabled = false
            val handler = Handler()
            handler.postDelayed({
                finish()
                startActivity(Intent(this, OcrActivity::class.java))
                overridePendingTransition(0, 0)
            }, 2000)
        }

        binding.userName.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK)
            i.addCategory(Intent.CATEGORY_DEFAULT)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i, CONTACTS_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CONTACTS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return
            }
            val contactUri = data.data
            val projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
            val cursor = contentResolver.query(
                contactUri!!, projection,
                null, null, null
            )

            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                val numberIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val number = cursor.getString(numberIndex)
                val nameIndex =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val name = cursor.getString(nameIndex)
                // Do something with the phone number
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {
                    binding.phone.setText(number)
                    binding.userName.setText(name)

                }
                cursor.close()
            }
        }

    }

}