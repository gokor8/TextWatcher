package com.gok.text_watcher.chain

interface Chain<T, R> {

    fun handle(data: T): R
}