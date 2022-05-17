package info.texnoman.evrtaxireal.base

import info.texnoman.evrtaxireal.utils.ErrorModel

class BaseResponse<T>(
    var success:Boolean,
    var data:T?=null,
    var status: Int? =null,
    var message:String,
    var code:Int?=null,
    var errors: ErrorModel?=null)