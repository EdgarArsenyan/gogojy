package com.example.data.data.util

class GetCorrectValue {
    companion object{
        fun checkValue(value: Int) : String{
            return if(value/1000 < 1)  "$$value"
            else if(value%1000 > 100) "$${value/1000},${value%1000}"
            else "$${value/1000},0${value%1000}"
        }
    }
}