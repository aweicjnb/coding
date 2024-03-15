package org.coding.ac

import java.util.function.Consumer

interface Trie<V> {
    fun put(key: String, value: V)

    fun get(key: String): V

    fun contains(key: String): Boolean

    fun parseText(key: String, hit: Consumer<EmitVal<V>>)
}