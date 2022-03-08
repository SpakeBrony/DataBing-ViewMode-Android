package cash.buddy.go.credit.bee.com.loan.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityMainBinding
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences
import cash.buddy.go.credit.bee.com.loan.viewmodel.MainModel


class MainActivity : BaseActivity() {


    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainModel by lazy {
        MainModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("")
        binding.viewModel = viewModel

        viewModel.userPhone.value = StorePreferences.getInstance(this).user_info

        viewModel.userPhone.observe(this) {
            binding.userPhone.text = "+91 $it"
        }

        binding.next.setOnClickListener {
//            Log.e("user_number",StorePreferences.getInstance(this).user_number.toString())
            when {
                StorePreferences.getInstance(this).user_number < 2 -> startActivity(
                    Intent(
                        this,
                        AgreeActivity::class.java
                    )
                )
                StorePreferences.getInstance(this).user_number == 2 -> startActivity(
                    Intent(
                        this,
                        UserInputActivity::class.java
                    )
                )
                StorePreferences.getInstance(this).user_number == 3 -> startActivity(
                    Intent(
                        this,
                        OcrActivity::class.java
                    )
                )
                StorePreferences.getInstance(this).user_number == 4 -> startActivity(
                    Intent(
                        this,
                        BankActivity::class.java
                    )
                )
                StorePreferences.getInstance(this).user_number == 5 -> startActivity(
                    Intent(
                        this,
                        EndActivity::class.java
                    )
                )
            }
        }

        binding.out.setOnClickListener {
            StorePreferences.getInstance(this).user_number = -1
            StorePreferences.getInstance(this).user_info = ""
            StorePreferences.getInstance(this).user_name = ""
            viewModel.remove {
                startActivity(Intent(this, StartActivity::class.java))
                finish()
            }
        }
        binding.toEmail.setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))
        }
        binding.userBank.setOnClickListener {
            if (StorePreferences.getInstance(this).user_number != 5)
                Toast.makeText(
                    this,
                    "Please fill in the basic information first!",
                    Toast.LENGTH_LONG
                ).show()
            else startActivity(Intent(this, UserBankActivity::class.java))
        }

    }
}