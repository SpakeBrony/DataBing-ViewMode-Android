package cash.buddy.go.credit.bee.com.loan.utils;

import android.content.Context
import android.content.SharedPreferences

abstract class PreferencesBase protected constructor(context: Context, name: String?, mode: Int) {
    protected var mContext: Context
    protected var mSharedPreferences: SharedPreferences?
    private var key: String? = null
    private var value: String? = null
    fun edit(): SharedPreferences.Editor {
        return mSharedPreferences!!.edit()
    }

    fun getIntValue(key: String?): Int {
        return mSharedPreferences!!.getInt(key, 0)
    }

    fun getIntValue(key: String?, defValue: Int): Int {
        return mSharedPreferences!!.getInt(key, defValue)
    }

    fun setIntValue(key: String?, value: Int) {
        if (mSharedPreferences != null) {
            val mEditor = mSharedPreferences!!.edit()
            mEditor.putInt(key, value)
            mEditor.commit()
        }
    }

    fun getStringValue(key: String?): String? {
        return mSharedPreferences!!.getString(key, "")
    }

    fun getStringValue(key: String?, defValue: String?): String? {
        return mSharedPreferences!!.getString(key, defValue)
    }

    fun setStringValue(key: String?, value: String?) {
        this.key = key
        this.value = value
        if (mSharedPreferences != null) {
            val mEditor = mSharedPreferences!!.edit()
            mEditor.putString(key, value)
            mEditor.commit()
        }
    }

    fun getLongValue(key: String?): Long {
        return mSharedPreferences!!.getLong(key, 0)
    }

    fun getLongValue(key: String?, defValue: Long): Long {
        return mSharedPreferences!!.getLong(key, defValue)
    }

    fun setLongValue(key: String?, value: Long) {
        if (mSharedPreferences != null) {
            val mEditor = mSharedPreferences!!.edit()
            mEditor.putLong(key, value)
            mEditor.commit()
        }
    }

    fun getBooleanValue(key: String?): Boolean {
        return mSharedPreferences!!.getBoolean(key, false)
    }

    fun getBooleanValue(key: String?, defValue: Boolean): Boolean {
        return mSharedPreferences!!.getBoolean(key, defValue)
    }

    fun setBooleanValue(key: String?, value: Boolean) {
        if (mSharedPreferences != null) {
            val mEditor = mSharedPreferences!!.edit()
            mEditor.putBoolean(key, value)
            mEditor.commit()
        }
    }

    fun remove(key: String?) {
        if (mSharedPreferences != null) {
            val mEditor = mSharedPreferences!!.edit()
            mEditor.remove(key)
            mEditor.commit()
        }
    }

    fun clearSharedPreferences() {
        if (mSharedPreferences != null) {
            val mEditor = mSharedPreferences!!.edit()
            mEditor.clear()
            mEditor.commit()
        }
    }

    val all: Map<String, *>?
        get() = if (mSharedPreferences != null) {
            mSharedPreferences!!.all
        } else null

    init {
        mContext = context.applicationContext
        mSharedPreferences = mContext.getSharedPreferences(name, mode)
    }
}