package inference

import parser.Abstraction
import parser.Application
import parser.Term
import parser.Variable
import kotlin.reflect.KClass

class Inference(private val variables: Set<Variable>) {
    private val types = mutableMapOf<Term, Tau>()
    private val typesFromSystem = mutableMapOf<Tau, Tau>()
    private val system = mutableListOf<Pair<Tau, Tau>>()
    private val termToRule = mapOf<KClass<*>, Int>(
        Pair(Variable::class, 1),
        Pair(Application::class, 2),
        Pair(Abstraction::class, 3)
    )
    private var counter = 0

    private fun freeName() = "t'" + counter++

    private fun infer(expression: Term): Tau = when (expression) {
        is Variable -> {
            val result = if (types.containsKey(expression)) types[expression] else Type(
                Variable(freeName(), expression.alpha, expression.free)
            )
            types[expression] = result!!
            result
        }
        is Abstraction -> {
            val t = Type(Variable(freeName(), expression.x.alpha, expression.x.free))
            types[expression.x] = t
            val impl = Impl(t, infer(expression.e))
            types.putIfAbsent(expression, impl)
            impl
        }
        else -> {
            val application = expression as Application
            val pi = Type(Variable(freeName(), 0, true))
            val p = infer(application.l)
            val r = infer(application.r)
            val impl = Impl(r, pi)
            system.add(Pair(p, impl))
            types[application] = pi
            types.putIfAbsent(application.l, p)
            types.putIfAbsent(application.r, r)
            pi
        }
    }

    private fun contains(t: Tau, v: Type): Boolean = when (t) {
        is Type -> t == v
        is Impl -> contains(t.l, v) || contains(t.r, v)
    }

    private fun bad(): Boolean {
        return system.any { e ->
            e.first is Type && e.second is Impl && contains(
                e.second, e.first as Type
            )
        }
    }

    private fun substitute(impl: Impl, k: Tau, v: Tau): Boolean {
        return when (impl.l) {
            is Type -> {
                if (impl.l == k) {
                    impl.l = v
                    true
                } else false
            }
            is Impl -> {
                substitute(impl.l as Impl, k, v)
            }
        } || when (impl.r) {
            is Type -> {
                if (impl.r == k) {
                    impl.r = v
                    true
                } else false
            }
            is Impl -> {
                substitute(impl.r as Impl, k, v)
            }
        }
    }

    private fun substitute(k: Type, v: Tau): Boolean {
        var result = false
        val toAdd = mutableListOf<Pair<Tau, Tau>>()
        val toRemove = mutableListOf<Pair<Tau, Tau>>()
        system.forEach {
            if (!(it.first == k && it.second == v)) {
                when (it.first) {
                    is Type -> {
                        if (it.first == k) {
                            toRemove.add(it)
                            toAdd.add(Pair(v, it.second))
                            result = true
                        }
                    }
                    is Impl -> {
                        if (substitute(it.first as Impl, k, v)) {
                            result = true
                        }
                    }
                }
                when (it.second) {
                    is Type -> {
                        if (it.second == k) {
                            toRemove.add(it)
                            toAdd.add(Pair(it.first, v))
                            result = true
                        }
                    }
                    is Impl -> {
                        if (substitute(it.second as Impl, k, v)) {
                            result = true
                        }
                    }
                }
            }
        }
        for (p in toAdd) {
            system.add(p)
        }
        for (p in toRemove) {
            system.remove(p)
        }
        return result
    }

    private fun s(impl: Impl, k: Tau, v: Tau) {
        when (impl.l) {
            is Type -> if (impl.l == k) impl.l = v
            is Impl -> substitute(impl.l as Impl, k, v)
        }
        when (impl.r) {
            is Type -> if (impl.r == k) impl.r = v
            is Impl -> substitute(impl.r as Impl, k, v)
        }
    }

    private fun solve(): Boolean {
        val toAdd = mutableListOf<Pair<Tau, Tau>>()
        while (true) {
            var cnt = 0
            var typeCnt = 0
            if (bad()) return false
            toAdd.forEach { k -> system.add(k) }
            toAdd.clear()
            val entries = system.toList()
            loop@ for (it in entries) {
                if (it.first != it.second) {
                    when (it.first) {
                        is Type -> {
                            typeCnt++
                            val success = substitute(it.first as Type, it.second)
                            if (success) {
                                cnt++
                                break@loop
                            }
                        }
                        is Impl -> {
                            when (it.second) {
                                is Type -> {
                                    system.remove(it)
                                    toAdd.add(Pair(it.second, it.first))
                                }
                                is Impl -> {
                                    val l = (it.first as Impl).l
                                    val r = (it.first as Impl).r
                                    val a = (it.second as Impl).l
                                    val b = (it.second as Impl).r
                                    toAdd.add(Pair(l, a))
                                    toAdd.add(Pair(r, b))
                                    system.remove(it)
                                }
                            }
                        }
                    }
                } else system.remove(it)
            }
            if (cnt == 0 && typeCnt == system.size + toAdd.size) return true
        }
    }

    private fun printAnswer(expression: Term, context: Set<Variable>, depth: Int) {
        val ruleNumber = termToRule[expression::class]
        val e = expression.toString()
        val t = types[expression].toString()
        val typesStr = context.joinToString(transform = {
            "$it : ${types[it].toString()}"
        }, postfix = if (context.isEmpty()) "" else " ", prefix = "*   ".repeat(depth))
        println("$typesStr|- $e : $t [rule #$ruleNumber]")
        when (expression) {
            is Abstraction -> printAnswer(expression.e, context.plus(expression.x), depth + 1)
            is Application -> {
                printAnswer(expression.l, context, depth + 1)
                printAnswer(expression.r, context, depth + 1)
            }
        }
    }

    fun inference(expression: Term) {
        infer(expression)
        val f = solve()
        if (f) {
            for (p in system) {
                typesFromSystem[p.first] = p.second
            }
            for (k in types.keys) {
                val t = types[k]
                if (typesFromSystem.containsKey(t)) {
                    types[k] = typesFromSystem[t]!!
                }
            }
            for (k in system) {
                for (j in types) {
                    when (j.value) {
                        is Type -> if (j.value == k.first) types[j.key] = k.second
                        is Impl -> s(j.value as Impl, k.first, k.second)
                    }
                }
            }
            printAnswer(expression, variables.filter { v -> v.free }.toSet(), 0)
        } else {
            println("Expression has no type")
        }
    }
}