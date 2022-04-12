package com.example.historyvn

import com.example.historyvn.models.UserInfoModel
import java.lang.RuntimeException

object User {
    private var _info: UserInfoModel? = null
    var info: UserInfoModel
        get() = _info ?: throw RuntimeException()
        set(value) {
            _info = value
        }
}