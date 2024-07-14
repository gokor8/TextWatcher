package com.gok.text_watcher.addons.filters

abstract class Limiter(private val textFilter: TextFilter) : TextFilter {

    override fun filter(text: String) = textFilter.filter(text)

    open fun subOffset(offset: Int): Int = offset

    class Unlimited : Limiter(TextFilter.NoLimit())

    class Limited(private val limit: Int) : Limiter(TextFilter.Base(limit)) {
        override fun subOffset(offset: Int) = offset.takeIf { it <= limit } ?: limit
    }
}