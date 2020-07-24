package com.example.mycalculator


//import android.hardware.biometrics.BiometricManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.text.LoginFilter
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.math.log


class MainActivity : AppCompatActivity() {

//    private val Tag = MainActivity::getLocalClassName.toString()
//
//    private lateinit var biometricPrompt: BiometricPrompt
//    private lateinit var promptInfo: BiometricPrompt.PromptInfo
//
//    private lateinit var biometricManager: androidx.biometric.BiometricManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvZero.setOnClickListener { appendOnExpression("0",true) }
        tvOne.setOnClickListener { appendOnExpression("1",true) }
        tvTwo.setOnClickListener { appendOnExpression("2",true) }
        tvThree.setOnClickListener { appendOnExpression("3",true) }
        tvFour.setOnClickListener { appendOnExpression("4",true) }
        tvFive.setOnClickListener { appendOnExpression("5",true) }
        tvSix.setOnClickListener { appendOnExpression("6",true) }
        tvSeven.setOnClickListener { appendOnExpression("7",true) }
        tvEight.setOnClickListener { appendOnExpression("8",true) }
        tvNine.setOnClickListener { appendOnExpression("9",true) }

        tvAdd.setOnClickListener { appendOnExpression("+",false) }
        tvSub.setOnClickListener { appendOnExpression("-",false) }
        tvMul.setOnClickListener { appendOnExpression("*",false) }
        tvDiv.setOnClickListener { appendOnExpression("/",false) }
        tvDot.setOnClickListener { appendOnExpression(".",false) }
        tvOpenPara.setOnClickListener { appendOnExpression("(",false) }
        tvClosePara.setOnClickListener { appendOnExpression(")",false) }


        tvCe.setOnClickListener {
            tvExpression.text = " "
            tvResult.text = " "
        }

        tvDel.setOnClickListener {
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()){
                tvExpression.text = string.substring(0,string.length-1)
            }

            tvResult.text = " "
        }

        tvEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longresult = result.toLong()
                if(result == longresult.toDouble()){
                    tvResult.text = longresult.toString()
                }
                else{
                    tvResult.text = result.toString()
                }

            }
            catch (e:Exception){
                Log.d("Exception", "message : "+e.message)
            }
        }


//        biometricManager = BiometricManager.from(this)
//        val executor = ContextCompat.getMainExecutor(this)
//        checkBiometricStatus(biometricManager)
//
//
//        biometricPrompt = BiometricPrompt(this, executor,
//            object : BiometricPrompt.AuthenticationCallback() {
//                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                    super.onAuthenticationError(errorCode, errString)
//                    showToast("Authentication error: $errString")
//                }
//
//                override fun onAuthenticationSucceeded(
//                    result: BiometricPrompt.AuthenticationResult) {
//                    super.onAuthenticationSucceeded(result)
//                    showToast("Authentication succeeded!")
//                }
//
//                override fun onAuthenticationFailed() {
//                    super.onAuthenticationFailed()
//                    showToast("Authentication failed")
//                }
//            })
//
//
//        promptInfo = BiometricPrompt.PromptInfo.Builder()
//            .setTitle("Biometric login for my app")
//            .setSubtitle("Log in using your biometric credential")
//            .setNegativeButtonText("Use email for login")
//            .build()
//
//        login.setOnClickListner{
//            biometricPrompt.authenticate(promptInfo)
//        }
//
//        val biometricLoginButton =
//            findViewById<Button>(R.id.biometric_login)
//        biometricLoginButton.setOnClickListener {
//            biometricPrompt.authenticate(promptInfo)
//        }

    }

    fun appendOnExpression(string: String, clear: Boolean){


        if(tvResult.text.isNotEmpty()){
            tvExpression.text = " "
        }

        if(clear){
            tvResult.text = ""
            tvExpression.append(string)
        }
        else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }

    }


//    private fun showToast(message: String){
//        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
//    }
//
//
//    private fun checkBiometricStatus(biometricManager: BiometricManager){
//        when (biometricManager.canAuthenticate()) {
//            BiometricManager.BIOMETRIC_SUCCESS ->
//                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
//            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
//                Log.e("MY_APP_TAG", "No biometric features available on this device.")
//            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
//                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
//            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
//                Log.e(
//                    "MY_APP_TAG", "The user hasn't associated " +
//                            "any biometric credentials with their account."
//                )
//        }
//    }
}