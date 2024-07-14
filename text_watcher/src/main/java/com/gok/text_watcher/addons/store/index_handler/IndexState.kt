package com.gok.text_watcher.addons.store.index_handler

class IndexState(
    private val maskWrapper: IndexWrapper.Mask,
    private val textWrapper: IndexWrapper.Text
) {
    fun canContinue() = maskWrapper.isUnitVisible() || !textWrapper.isLastIndex()

    fun getWrapper() = maskWrapper.takeIf {
        maskWrapper.isUnitStatic() || (maskWrapper.isUnitVisible() && textWrapper.isEmpty())
    } ?: textWrapper

    fun incCopy() = if (maskWrapper.isUnitStatic() || textWrapper.isLastIndex())
        IndexState(maskWrapper.incCopy(), textWrapper)
    else
        IndexState(maskWrapper.incCopy(), textWrapper.incCopy())
}