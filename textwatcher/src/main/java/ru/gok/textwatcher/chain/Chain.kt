package ru.gok.textwatcher.chain

interface Chain<T, R> {

    fun handle(data: T): R
}