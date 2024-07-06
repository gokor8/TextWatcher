package ru.gok.textwatcher.addons.filters

interface TextFilter {

    fun filter(text: String): String


    class Base(private val limitation: Int) : TextFilter {

        override fun filter(text: String) = text.takeIf { it.length > limitation }?.run {
            substring(0, limitation)
        } ?: text
    }

    class NoLimit : TextFilter {
        override fun filter(text: String) = text
    }
}