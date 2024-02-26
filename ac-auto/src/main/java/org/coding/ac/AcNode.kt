package com.duitang.kt.duitang.han.collection.ac

import it.unimi.dsi.fastutil.chars.Char2ObjectMap
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap
import it.unimi.dsi.fastutil.chars.CharSet
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
import it.unimi.dsi.fastutil.objects.ObjectSet
import java.util.*

class AcNode<V>(
    private val depth: Int,
) {
    private var failure: AcNode<V>? = null
    private var emits: ObjectSet<String>? = null
    private var success: Char2ObjectMap<AcNode<V>>? = null
    fun next(
        c: Char
    ): AcNode<V>? {
        return next(c, false)
    }

    fun next(
        c: Char,
        ignoreRoot: Boolean,
    ): AcNode<V>? {
        var next = this.getChild(c)
        if (!ignoreRoot && next == null && this.depth == 0) {
            next = this
        }
        return next
    }

    fun getChild(
        c: Char
    ): AcNode<V>? {
        return this.success?.get(c)
    }

    fun addChild(c: Char, node: AcNode<V>) {
        if (this.success == null) {
            this.success = Char2ObjectOpenHashMap(8)
        }
        this.success!!.put(c, node)
    }

    fun addStatus(c: Char): AcNode<V> {
        var next = next(c, true)
        if (next ==null) {
            next = AcNode(this.depth + 1)
            this.addChild(c, next)
        }
        return next;
    }

    fun addEmits(emits: Collection<String>?) {
        if (null == emits || emits.isEmpty()) {
            return
        }
        for (emit in emits) {
            addEmits(emit)
        }
    }

    fun addEmits(keyword: String) {
        if (this.emits == null) {
            this.emits = ObjectOpenHashSet(8)
        }
        this.emits!!.add(keyword)
    }

    fun setFailure(failure: AcNode<V>): AcNode<V> {
        this.failure = failure
        return this
    }

    fun getDepth(): Int {
        return depth
    }

    fun getFailure(): AcNode<V> {
        return failure!!
    }

    fun getEmits(): ObjectSet<String>? {
        return emits
    }

    fun setEmits(emits: ObjectSet<String>?): AcNode<V> {
        this.emits = emits
        return this
    }

    fun successKeys(): CharSet {
        if (this.success != null) {
            return success!!.keys
        }
        return CharSet.of()
    }

    fun successValues(): Collection<AcNode<V>> {
        if (this.success != null) {
            return success!!.values
        }
        return Collections.emptyList()
    }
}