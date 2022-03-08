package cash.buddy.go.credit.bee.com.loan.utils;

import android.content.Context

class StorePreferences private constructor(context: Context) :
    PreferencesBase(context, STORE_REFERENCE_FILE_NAME, Context.MODE_PRIVATE) {
    var user_info: String
        get() = getStringValue(KEY_USER, "")!!
        set(gaid) {
            setStringValue(KEY_USER, gaid)
        }

    var user_xy: String
        get() = getStringValue(KEY_YX, "")!!
        set(gaid) {
            setStringValue(KEY_YX, gaid)
        }
    var user_name: String
        get() = getStringValue(KEY_USER_NAME, "")!!
        set(gaid) {
            setStringValue(KEY_USER_NAME, gaid)
        }
    var user_number: Int
        get() = getIntValue(USER_NUMBER, -1)
        set(gaid) {
            setIntValue(USER_NUMBER, gaid)
        }


    companion object {
        const val STORE_REFERENCE_FILE_NAME = "CREDIT_SP"

        @Volatile
        private var sInstance: StorePreferences? = null
        const val KEY_USER = "user_info"
        const val KEY_USER_NAME = "user_name"
        const val KEY_YX = "yes_or_no_xy"
        const val USER_NUMBER = "user_number"


        @JvmStatic
        fun getInstance(context: Context): StorePreferences {
            if (sInstance == null) {
                synchronized(StorePreferences::class.java) {
                    if (sInstance == null) {
                        sInstance = StorePreferences(context.applicationContext)
                    }
                }
            }
            return sInstance!!
        }
    }
}