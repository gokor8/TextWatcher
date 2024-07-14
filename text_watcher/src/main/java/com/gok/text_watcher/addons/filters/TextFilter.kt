package com.gok.text_watcher.addons.filters

interface TextFilter {

    fun filter(text: String): String


    class Base(private val limitation: Int) : TextFilter {

        override fun filter(text: String) = text.takeIf { it.length > limitation }?.run {
            substring(0, limitation)
        } ?: text
    }

    class Unlimited : TextFilter {
        override fun filter(text: String) = text
    }
}