package cash.buddy.go.credit.bee.com.loan.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityLoginBinding
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences
import cash.buddy.go.credit.bee.com.loan.viewmodel.LoginModel


class LoginActivity : BaseActivity() {


    private val viewModel: LoginModel by lazy {
        LoginModel(this)
    }


    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        initLoadIng("Support Loan")
        binding.codeInput.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //本地是编写用isErrorEnabled判断是否可以点击.但是有bug，从而改为6
                viewModel.loginButtonFlag.value =
                    (!binding.phoneInput.isErrorEnabled) && (binding.codeInput.editText!!.text.length >= 6)

                Log.e("当前登录按钮", binding.codeInput.editText!!.text.length.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        viewModel.loginButtonFlag.observe(this) {
            binding.login.isEnabled = it
        }

        binding.login.setOnClickListener {
            viewModel.loginButtonFlag.value = false
           loadingProgressBar.visibility = View.VISIBLE
            val handler = Handler()
            handler.postDelayed({
                StorePreferences.getInstance(this).user_info = binding.phoneInput.editText!!.text.toString()
                finish()
                startActivity(Intent(this, MainActivity::class.java))
                overridePendingTransition(0, 0)
            }, 2000)
        }

    }
}