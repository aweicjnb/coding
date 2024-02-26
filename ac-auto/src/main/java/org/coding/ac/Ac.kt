package com.duitang.kt.duitang.han.collection.ac

import com.duitang.han.collection.EmitVal
import com.duitang.han.collection.Trie
import joptsimple.internal.Strings
import java.util.LinkedList
import java.util.function.Consumer

class Ac<V>(keywords: Collection<String>) : Trie<V> {
    private var root: AcNode<V>
    private var failureStatesConstructed = false

    init {
        this.root = AcNode(0)
        this.addAllKeyword(keywords)
        this.buildFailure()
    }

    private fun addKeyword(keyword: String) {
        if (Strings.isNullOrEmpty(keyword)) {
            return
        }
        var currentState = this.root
        val length = keyword.length
        for (i in keyword.indices step 1) {
            currentState = currentState.addStatus(keyword.get(i))
        }
        currentState.addEmits(keyword)
    }

    private fun addAllKeyword(keywordSet: Collection<String>) {
        for (keyword in keywordSet) {
            addKeyword(keyword)
        }
    }

    private fun buildFailure() {
        val queue = LinkedList<AcNode<V>>()
        for (depthOneState in root.successValues()) {
            depthOneState.setFailure(this.root)
            queue.add(depthOneState)
        }
        this.failureStatesConstructed = true

        while (!queue.isEmpty()) {
            val current = queue.remove()
            for (c in current.successKeys()) {
                val target = current.next(c)!!
                queue.add(target)
                var traceFailure =  current.getFailure()
                while (traceFailure.next(c) == null) {
                    traceFailure = traceFailure.getFailure()
                }
                val newFailure = traceFailure.next(c)!!
                target.setFailure(newFailure)
                target.addEmits(newFailure.getEmits())
            }
        }
    }

    fun parseText(text: String): Collection<EmitVal<String>> {
        var position: Int = 0
        var current = this.root
        val collectedEmitVals = ArrayList<EmitVal<String>>()
        for (i in text.indices step 1) {
            current = getNext(current, text.get(i))
            storeEmits(position, current, collectedEmitVals)
            position++
        }

        return collectedEmitVals
    }


    private fun storeEmits(
        position: Int,
        current: AcNode<V>,
        collectedEmitVals: MutableList<EmitVal<String>>
    ) {
        var emits = current.getEmits()
        if (emits != null && !emits.isEmpty()) {
            for (emit in emits) {
                collectedEmitVals.add(EmitVal<String>(position - emit.length + 1, position, emit))
            }
        }

    }


    private fun getNext(current: AcNode<V>, c: Char):AcNode<V>  {
        var currentNode: AcNode<V>
        var newCurrent = current.next(c)
        while (newCurrent == null) {
            currentNode = current.getFailure()
            newCurrent = currentNode.next(c)
        }
        return newCurrent
    }

    override fun put(key: String, value: V) {
        throw UnsupportedOperationException()
    }

    override fun get(key: String): V {
        throw UnsupportedOperationException()
    }

    override fun contains(key: String): Boolean {
        throw UnsupportedOperationException()
    }

    override fun parseText(key: String, hit: Consumer<EmitVal<V>>) {
        var position = 0
        var current = this.root
        for (i in 0 until key.length step 1) {
            current = getNext(current, key.get(i))
            val emits = current.getEmits()
            if (emits != null && !emits.isEmpty()) {
                for (emit in emits) {
                    val start = position - emit.length + 1
                    hit.accept(EmitVal(start, position + 1, null))
                }
            }
            ++position
        }
    }


}